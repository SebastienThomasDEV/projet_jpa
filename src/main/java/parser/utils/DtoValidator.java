package parser.utils;

import imdb.entity.Person;
import parser.dto.*;

import java.util.List;

public abstract class DtoValidator {

    public static boolean isStringValid(String str) {
        return str != null && !str.isEmpty();
    }

    public static boolean isMovieDtoValid(MovieDto movieDto) {
        return movieDto != null
                && movieDto.getId() != null
                && movieDto.getAnneeSortie() != null
                && movieDto.getLieuTournage() != null
                && movieDto.getGenres() != null
                && movieDto.getPays() != null
                && movieDto.getLangue() != null
                && movieDto.getRating() != null
                && movieDto.getPlot() != null
                && movieDto.getNom() != null
                && movieDto.getRoles() != null
                && movieDto.getRealisateurs() != null
                && movieDto.getCastingPrincipal() != null;

    }

    public static boolean isCountryDtoValid(CountryDto countryDto) {
        return countryDto != null
                && countryDto.getNom() != null
                && countryDto.getUrl() != null;
    }

    public static boolean isLieuTournageDtoValid(LieuTournageDto locationDto) {
        return locationDto != null
                && locationDto.getVille() != null
                && locationDto.getEtatDept() != null
                && locationDto.getPays() != null;
    }

    public static boolean isGenreDtoValid(List<String> genres) {
        return genres != null
                && !genres.isEmpty()
                && genres.stream().allMatch(DtoValidator::isStringValid);
    }

    public static boolean isRealisateurDtoValid(RealisateurDto person) {
        return person != null
                && person.getId() != null
                && person.getNaissance() != null
                && person.getIdentite() != null
                && person.getUrl() != null;
    }


    public static boolean isRoleDtoValid(RoleDto roleDto) {
        return roleDto != null
                && roleDto.getCharacterName() != null
                && roleDto.getActeur() != null;
    }

    public static boolean isActorDtoValid(ActorDto actorDto) {
        System.out.println(actorDto);
        return actorDto != null
                && actorDto.getId() != null
                && actorDto.getNaissance() != null
                && actorDto.getIdentite() != null
                && actorDto.getUrl() != null;
    }


}
