package imdb.entity;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
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

    @Column(precision = 15, scale = 2)
    private BigDecimal height;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location birthPlace;

    @ManyToMany
    @JoinTable(
            name = "person_movie",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private Set<Movie> movies;

    @OneToMany(mappedBy = "actor")
    private Set<Role> roles;

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

    public BigDecimal getHeight() {
        return height;
    }

    public Date getBirthDate() {
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

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setBirthPlace(Location birthPlace) {
        this.birthPlace = birthPlace;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
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
