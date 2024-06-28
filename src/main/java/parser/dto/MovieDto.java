package parser.dto;

import java.util.List;

public class MovieDto {
    private String id;
    private CountryDto pays;
    private String nom;
    private String url;
    private String rating;
    private String plot;
    private String langue;
    private LieuTournageDto lieuTournage;
    private List<RealisateurDto> realisateurs;
    private List<ActorDto> castingPrincipal;
    private String anneeSortie;
    private List<RoleDto> roles;
    private List<String> genres;

    // Default constructor
    public MovieDto() {}
    public MovieDto(
            String id,
            CountryDto pays,
            String nom,
            String url,
            String rating,
            String plot,
            String langue,
            LieuTournageDto lieuTournage,
            List<RealisateurDto> realisateurs,
            List<ActorDto> castingPrincipal,
            String anneeSortie,
            List<RoleDto> roles,
            List<String> genres
    ) {
        this.id = id;
        this.pays = pays;
        this.nom = nom;
        this.url = url;
        this.rating = rating;
        this.plot = plot;
        this.langue = langue;
        this.lieuTournage = lieuTournage;
        this.realisateurs = realisateurs;
        this.castingPrincipal = castingPrincipal;
        this.anneeSortie = anneeSortie;
        this.roles = roles;
        this.genres = genres;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CountryDto getPays() {
        return pays;
    }

    public void setPays(CountryDto pays) {
        this.pays = pays;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public LieuTournageDto getLieuTournage() {
        return lieuTournage;
    }

    public void setLieuTournage(LieuTournageDto lieuTournage) {
        this.lieuTournage = lieuTournage;
    }

    public List<RealisateurDto> getRealisateurs() {
        return realisateurs;
    }

    public void setRealisateurs(List<RealisateurDto> realisateurs) {
        this.realisateurs = realisateurs;
    }

    public List<ActorDto> getCastingPrincipal() {
        return castingPrincipal;
    }

    public void setCastingPrincipal(List<ActorDto> castingPrincipal) {
        this.castingPrincipal = castingPrincipal;
    }

    public String getAnneeSortie() {
        return anneeSortie;
    }

    public void setAnneeSortie(String anneeSortie) {
        this.anneeSortie = anneeSortie;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "MovieDto{" +
                "id='" + id + '\'' +
                ", pays=" + pays +
                ", nom='" + nom + '\'' +
                ", url='" + url + '\'' +
                ", rating='" + rating + '\'' +
                ", plot='" + plot + '\'' +
                ", langue='" + langue + '\'' +
                ", lieuTournage=" + lieuTournage +
                ", realisateurs=" + realisateurs +
                ", castingPrincipal=" + castingPrincipal +
                ", anneeSortie='" + anneeSortie + '\'' +
                ", roles=" + roles +
                ", genres=" + genres +
                '}';
    }


}
