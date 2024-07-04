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


    // Constante pour le nom de la base de données
    static String dbName = "jpa";

    // Constante pour le nom du fichier JSON
    static String fileName = "data/films.json";

    /**
     * Cette méthode est le point d'entrée du programme
     * Elle crée l'EntityManagerFactory et l'EntityManager
     * Elle appelle les méthodes pour définir l'encodage de la base de données et pour lire le fichier JSON
     *
     * @param args : les arguments de la ligne de commande
     */
    public static void main(String[] args) {

        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory(dbName); EntityManager em = factory.createEntityManager()) {
            setCharacterEncoding(em);
            parseAndPersistMovies(em);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Cette méthode permet de définir l'encodage de la base de données pour supporter les caractères spéciaux
     *
     * @param em : l'EntityManager
     */
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


    /**
     * Cette méthode permet de lire le fichier JSON et de persister les données dans la base de données
     * Elle utilise les méthodes de validation des DTOs et de mapping des DTOs aux entités
     *
     * @param em : l'EntityManager
     */
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
                    for (RealisateurDto realisateurDto : movieDto.getRealisateurs()) {
                        if (DtoValidator.isRealisateurDtoValid(realisateurDto)) {
                            Person p = Mapper.findOrCreateDirector(em, realisateurDto, persistedPersons);
                            p.addMovie(movie);
                        }
                    }
                    for (ActorDto actorDto : movieDto.getCastingPrincipal()) {
                        if (DtoValidator.isActorDtoValid(actorDto)) {
                            Person p = Mapper.findOrCreateActor(em, actorDto, persistedPersons);
                            Role r = Mapper.findOrCreateRole(em, p, movie, actorDto, persistedRoles);
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
