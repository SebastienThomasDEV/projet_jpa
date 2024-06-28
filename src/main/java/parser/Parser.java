package parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import imdb.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import parser.dto.*;
import parser.utils.DtoValidator;
import parser.utils.Mapper;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Parser {

    static String dbName = "jpa";
    static String fileName = "data/films.json";

    public static void main(String[] args) {
        EntityManagerFactory factory = null;
        EntityManager em = null;

        try {
            factory = Persistence.createEntityManagerFactory(dbName);
            em = factory.createEntityManager();
            setCharacterEncoding(em);
            parseAndPersistMovies(em);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            if (factory != null) {
                factory.close();
            }
        }
    }

    private static void setCharacterEncoding(EntityManager em) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.createNativeQuery("ALTER DATABASE " + dbName + " CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }


    private static void parseAndPersistMovies(EntityManager em) {
        EntityTransaction transaction = em.getTransaction();
        try {
            ObjectMapper mapper = new ObjectMapper();
            CollectionType javaType = mapper.getTypeFactory().constructCollectionType(List.class, MovieDto.class);
            File file = new File(fileName);
            List<MovieDto> moviesDataList = mapper.readValue(file, javaType);
            transaction.begin();
            Set<Genre> persistedGenres = new HashSet<>();
            Set<Country> persistedCountries = new HashSet<>();
            Set<Location> persistedLocations = new HashSet<>();
            Set<Movie> persistedMovies = new HashSet<>();
            Set<Role> persistedRoles = new HashSet<>();
            Set<Person> persistedPersons = new HashSet<>();

            for (MovieDto movieDto : moviesDataList) {
                if (DtoValidator.isMovieDtoValid(movieDto)) {
                    Movie movie = Mapper.findOrCreateMovie(em, movieDto, persistedMovies);
                    if (DtoValidator.isLieuTournageDtoValid(movieDto.getLieuTournage())) {
                        Location l = Mapper.findOrCreateLocation(em, movieDto.getLieuTournage(), persistedLocations);
                        if (DtoValidator.isCountryDtoValid(movieDto.getPays())) {
                            Country c = Mapper.findOrCreateCountry(em, movieDto.getPays(), persistedCountries);
                            l.setCountry(c);
                            movie.setLocation(l);
                        }
                    }
                    if (DtoValidator.isGenreDtoValid(movieDto.getGenres())) {
                        for (String genreName : movieDto.getGenres()) {
                            Genre g = Mapper.findOrCreateGenre(em, genreName, persistedGenres);
                            movie.getGenres().add(g);
                        }
                    }
                    for (RoleDto roleDto : movieDto.getRoles()) {
                        if (DtoValidator.isRoleDtoValid(roleDto) && DtoValidator.isActorDtoValid(roleDto.getActeur())) {
                            Role r = Mapper.findOrCreateActorRole(em, roleDto.getActeur(), persistedRoles);
                            movie.getRoles().add(r);
                        }
                    }
                    em.persist(movie);
                }
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }


}
