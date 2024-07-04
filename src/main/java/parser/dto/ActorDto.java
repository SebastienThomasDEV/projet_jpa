package parser.dto;

import parser.dto.abstraction.AbstractPerson;

public class ActorDto extends AbstractPerson {
    private String id;
    private String identite;
    private NaissanceDto naissance;
    private String url;
    private String height;

    // Default constructor
    public ActorDto() {
    }

    public ActorDto(
            String id,
            String identite,
            NaissanceDto naissance,
            String url,
            String height
    ) {
        super(id, identite, url, naissance);
        this.height = height;
    }

    public String getHeight() {
        return height;
    }

    public String setHeight(String height) {
        return height;
    }


    @Override
    public String toString() {
        return "ActorDto{" +
                "id='" + getId() + '\'' +
                ", identite='" + getIdentite() + '\'' +
                ", naissance=" + getNaissance() +
                ", url='" + getUrl() + '\'' +
                ", height='" + height + '\'' +
                '}';
    }
}
