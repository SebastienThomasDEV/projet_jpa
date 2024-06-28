package parser.dto;


public class LieuTournageDto {
    private String ville;
    private String etatDept;
    private String pays;

    // Default constructor
    public LieuTournageDto() {}
    public LieuTournageDto(String ville, String etatDept, String pays) {
        this.ville = ville;
        this.etatDept = etatDept;
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public String getEtatDept() {
        return etatDept;
    }

    public String getPays() {
        return pays;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setEtatDept(String etatDept) {
        this.etatDept = etatDept;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    @Override
    public String toString() {
        return "LieuTournageDto{" +
                "ville='" + ville + '\'' +
                ", etatDept='" + etatDept + '\'' +
                ", pays='" + pays + '\'' +
                '}';
    }
}