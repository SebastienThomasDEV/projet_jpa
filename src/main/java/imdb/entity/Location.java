package imdb.entity;


import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 255)
    private String city;

    @Column(length = 255)
    private String state;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "location")
    private Set<Movie> movies;

    @OneToMany(mappedBy = "birthPlace")
    private Set<Person> persons;

    // Getters and Setters

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public Country getCountry() {
        return country;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

}
