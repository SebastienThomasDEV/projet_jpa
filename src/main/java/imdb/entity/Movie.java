package imdb.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(nullable = false)
    private String uuid;

    @Column(nullable = false)
    private String name;


    @Column(nullable = false)
    private float rating;

    @Column(length = 255)
    private String releaseDate;

    @Column(length = 8000)
    private String plot;

    @Column(length = 255)
    private String lang;

    @ManyToMany
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres;

    public Movie() {
        this.genres = new HashSet<>(); // Initialize the set
        this.screenwriters = new HashSet<>(); // Initialize the set
        this.roles = new HashSet<>(); // Initialize the set
    }


    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToMany(mappedBy = "movies")
    private Set<Person> screenwriters;

    @OneToMany(mappedBy = "movie")
    private Set<Role> roles;


    public int getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public void addGenre(Genre genre) {
        if (genre == null) {
            throw new IllegalArgumentException("Genre cannot be null");
        }
        if (!this.genres.contains(genre)) {
            this.genres.add(genre);
            genre.addMovie(this); // Ensure bidirectional relationship
        }
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<Person> getScreenwriters() {
        return screenwriters;
    }

    public void setScreenwriters(Set<Person> screenwriters) {
        this.screenwriters = screenwriters;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

}
