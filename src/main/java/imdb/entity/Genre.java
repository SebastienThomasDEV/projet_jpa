package imdb.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "genres")
    private Set<Movie> movies;

    public Genre() {
        this.movies = new HashSet<>(); // Initialize the set
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    public void addMovie(Movie movie) {
        if (movie == null) {
            throw new IllegalArgumentException("Movie cannot be null");
        }
        if (!this.movies.contains(movie)) {
            this.movies.add(movie);
            movie.addGenre(this); // Ensure bidirectional relationship
        }
    }
}