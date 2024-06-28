package parser.dto.abstraction;

import parser.dto.NaissanceDto;

public abstract class AbstractPerson {
    private String id;
    private String identite;
    private String url;
    private NaissanceDto naissance;

    // Default constructor
    public AbstractPerson() {}
    public AbstractPerson(
            String id,
            String identite,
            String url,
            NaissanceDto naissance
    ) {
        this.id = id;
        this.identite = identite;
        this.url = url;
        this.naissance = naissance;
    }
    public String getId() {
        return id;
    }

    public String getIdentite() {
        return identite;
    }

    public String getUrl() {
        return url;
    }

    public NaissanceDto getNaissance() {
        return naissance;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIdentite(String identite) {
        this.identite = identite;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setNaissance(NaissanceDto naissance) {
        this.naissance = naissance;
    }
}
