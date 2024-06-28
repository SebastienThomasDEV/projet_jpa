package parser.dto;

public class RoleDto {
    private String characterName;

    private ActorDto acteur;

    // Default constructor
    public RoleDto() {}
    public RoleDto(String characterName, ActorDto acteur) {
        this.characterName = characterName;
        this.acteur = acteur;
    }

    public String getCharacterName() {
        return characterName;
    }

    public ActorDto getActeur() {
        return acteur;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public void setActeur(ActorDto acteur) {
        this.acteur = acteur;
    }


    @Override
    public String toString() {
        return "RoleDto{" +
                "characterName='" + characterName + '\'' +
                ", acteur=" + acteur +
                '}';
    }
}
