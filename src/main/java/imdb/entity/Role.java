
package imdb.entity;

import jakarta.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 255)
    private String characterName;

    @Column(nullable = false)
    private boolean isMainActor;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person actor;

    // Getters and Setters

    public int getId() {
        return id;
    }

    public String getCharacterName() {
        return characterName;
    }

    public boolean isMainActor() {
        return isMainActor;
    }

    public Movie getMovie() {
        return movie;
    }

    public Person getActor() {
        return actor;
    }


    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public void setMainActor(boolean mainActor) {
        isMainActor = mainActor;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setActor(Person actor) {
        this.actor = actor;
    }


}
