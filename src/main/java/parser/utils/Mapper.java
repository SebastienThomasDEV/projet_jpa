package parser.utils;

import imdb.entity.*;
import jakarta.persistence.EntityManager;
import parser.dto.*;
import parser.dto.abstraction.AbstractPerson;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class Mapper {
    public static Genre findOrCreateGenre(EntityManager em, String genreName, Set<Genre> persistedGenres) {
        for (Genre genre : persistedGenres) {
            if (genre.getName().equals(genreName)) {
                return genre;
            }
        }

        Genre newGenre = new Genre();
        newGenre.setName(genreName);
        em.persist(newGenre);
        persistedGenres.add(newGenre);
        return newGenre;
    }


    public static Movie findOrCreateMovie(EntityManager em, MovieDto movieDto, Set<Movie> persistedMovies) {
        String movieName = movieDto.getNom();
        for (Movie movie : persistedMovies) {
            if (movie.getName().equals(movieName)) {
                return movie;
            }
        }
        Movie newMovie = new Movie();
        newMovie.setUuid(movieDto.getId());
        newMovie.setName(movieName.trim());
        try {
            newMovie.setRating(Float.parseFloat(movieDto.getRating()));
        } catch (NumberFormatException e) {
            newMovie.setRating(-1);
        }
        newMovie.setPlot(movieDto.getPlot());
        newMovie.setLang(movieDto.getLangue());
        newMovie.setReleaseDate(movieDto.getAnneeSortie());
        em.persist(newMovie);
        persistedMovies.add(newMovie);
        return newMovie;
    }

    public static Country findOrCreateCountry(EntityManager em, CountryDto countryDto, Set<Country> persistedCountries) {
        String countryFullName = countryDto.getNom();
        for (Country country : persistedCountries) {
            if (country.getFullName().equals(countryFullName)) {
                return country;
            }
        }
        String shortName = countryDto.getUrl();
        String substring = shortName.substring(shortName.indexOf("country_of_origin=") + 18, shortName.indexOf("&ref_"));
        Country newCountry = new Country();
        newCountry.setFullName(countryFullName.trim());
        newCountry.setShortName(substring.trim());
        em.persist(newCountry);
        persistedCountries.add(newCountry);
        return newCountry;
    }


    public static Location findOrCreateLocation(EntityManager em, LieuTournageDto lieuTournageDto, Set<Location> persistedLocations) {
        String city = lieuTournageDto.getVille();
        String state = lieuTournageDto.getEtatDept();
        for (Location location : persistedLocations) {
            if (location.getCity().equals(city) && location.getState().equals(state)) {
                return location;
            }
        }
        Location newLocation = new Location();
        newLocation.setCity(city.trim());
        newLocation.setState(state.trim());
        em.persist(newLocation);
        persistedLocations.add(newLocation);
        return newLocation;
    }


    public static Person findOrCreateDirector(EntityManager em, RealisateurDto realisateurDto, Set<Person> persistedPersons) {
        String uuid = realisateurDto.getId();
        for (Person person : persistedPersons) {
            if (person.getName().equals(uuid)) {
                return person;
            }
        }
        Person newPerson = createPerson(realisateurDto);
        em.persist(newPerson);
        persistedPersons.add(newPerson);
        return newPerson;
    }




    public static Role findOrCreateActorRole(EntityManager em, ActorDto actorDto, Set<Role> persistedRoles) {
        String uuid = actorDto.getId();
        for (Role role : persistedRoles) {
            if (role.getActor().getName().equals(uuid)) {
                return role;
            }
        }
        Role newRole = new Role();
        newRole.setCharacterName(actorDto.getIdentite().trim());
        Person actor = createPerson(actorDto);
        System.out.println(actor);
        em.persist(actor);
        newRole.setActor(actor);
        persistedRoles.add(newRole);
        return newRole;
    }


    private static Person createPerson(AbstractPerson actorDto) {
        Person actor = new Person();
        try {
            String[] actorFullName = actorDto.getIdentite().split(" ");
            actor.setName(actorFullName[0].trim());
            actor.setSurname(actorFullName[1].trim());
        } catch (Exception e) {
            actor.setName(actorDto.getIdentite().trim());
            actor.setSurname("");
        }
        actor.setUuid(actorDto.getId());
        actor.setBirthDate(DateFormatter.parseDate(actorDto.getNaissance().getDateNaissance()));

        return actor;
    }





}