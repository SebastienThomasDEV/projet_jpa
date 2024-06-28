package parser.dto;

import parser.dto.abstraction.AbstractPerson;

public  class RealisateurDto extends AbstractPerson {
    private String id;
    private String identite;
    private String url;
    private NaissanceDto naissance;

    // Default constructor
    public RealisateurDto() {}
    public RealisateurDto(
            String id,
            String identite,
            String url,
            NaissanceDto naissance
    ) {
        super(id, identite, url, naissance);
    }

    @Override
    public String toString() {
        return "RealisateurDto{" +
                "id='" + getId() + '\'' +
                ", identite='" + getIdentite() + '\'' +
                ", naissance=" + getNaissance() +
                ", url='" + getUrl() + '\'' +
                '}';
    }


}