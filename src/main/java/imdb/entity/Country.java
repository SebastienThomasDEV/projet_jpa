package imdb.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Country {

    public Country() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    private String shortName;

    @Column(nullable = false, length = 255)
    private String fullName;

    @OneToMany(mappedBy = "country")
    private Set<Location> locations;

    // Getters and Setters


    public int getId() {
        return id;
    }

    public String getShortName() {
        return shortName;
    }

    public String getFullName() {
        return fullName;
    }


    public Set<Location> getLocations() {
        return locations;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }







}
