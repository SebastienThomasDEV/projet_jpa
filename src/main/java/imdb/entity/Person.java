package imdb.entity;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 255)
    private String uuid;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, length = 255)
    private String surname;

    @Column(length = 255)
    private String height;

    @Column(nullable = false)
    private String birthDate;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location birthPlace;

    @ManyToMany
    @JoinTable(
            name = "screenwriter_movie",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private Set<Movie> movies;

    @OneToMany(mappedBy = "actor")
    private Set<Role> roles;

    public Person() {
        this.movies = new HashSet<>(); // Initialize the set
        this.roles = new HashSet<>(); // Initialize the set
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getHeight() {
        return height;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public Location getBirthPlace() {
        return birthPlace;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setBirthPlace(Location birthPlace) {
        this.birthPlace = birthPlace;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    public void addMovie(Movie movie) {
        this.movies.add(movie);
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", height=" + height +
                ", birthDate=" + birthDate +
                ", birthPlace=" + birthPlace +
                ", movies=" + movies +
                ", roles=" + roles +
                '}';
    }

}
