package parser.dto;

public class CountryDto  {
    private String nom;
    private String url;

    // Default constructor
    public CountryDto() {}
    public CountryDto(
            String nom,
            String url
    ) {
        this.nom = nom;
        this.url = url;
    }

    public String getNom() {
        return nom;
    }

    public String getUrl() {
        return url;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "CountryDto{" +
                "nom='" + nom + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
