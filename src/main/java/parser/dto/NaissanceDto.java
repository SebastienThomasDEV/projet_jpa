package parser.dto;

public class NaissanceDto {
    private String dateNaissance;
    private String lieuNaissance;

    // Default constructor
    public NaissanceDto() {}

    public NaissanceDto(
            String dateNaissance,
            String lieuNaissance
    ) {
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    @Override
    public String toString() {
        return "NaissanceDto{" +
                "dateNaissance='" + dateNaissance + '\'' +
                ", lieuNaissance='" + lieuNaissance + '\'' +
                '}';
    }
}