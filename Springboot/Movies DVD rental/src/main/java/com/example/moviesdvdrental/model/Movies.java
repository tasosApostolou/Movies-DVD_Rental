package com.example.moviesdvdrental.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor

public class Movies extends AbstractEntity {
//    @Id
//    private Long id; this is extended by AbstractEntity
    @Column
    private String title;
    @Column
    private int year;
    @Column
    private int countCopies;
    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;

    @OneToMany(mappedBy = "movie",cascade = CascadeType.REMOVE)
    private Set<Rentals> rentals = new HashSet<>();
    @OneToMany(mappedBy = "movie",cascade = CascadeType.REMOVE)
    private Set<Ratings> ratings = new HashSet<>();

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "movies_actors",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id",nullable = false),
            inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "id",nullable = false)
    )
    private Set<Actor> actors = new HashSet<>();

    @ManyToMany(mappedBy = "movies")
    private Set<Category> categories = new HashSet<>();
    public Movies(Long id, String title, int year) {
        this.setId(id);
        this.title = title;
        this.year = year;
    }

    public Movies(String title, int year, int countCopies, Director director) {
        this.title = title;
        this.year = year;
        this.countCopies = countCopies;
        this.director = director;
    }

    // public api methods
    public void addDirector(Director director){
        this.setDirector(director);
        director.getMovies().add(this);

    }

    public void addCategory(Category category){
        this.categories.add(category);
        category.getMovies().add(this);
    }

    public void addActor(Actor actor){
        this.actors.add(actor);
        actor.getMovies().add(this);
    }
    public Set<Actor> getAllActors(){return Collections.unmodifiableSet(actors);
    }
    public Set<Rentals> getAllRentals(){return Collections.unmodifiableSet(rentals);}

    public Set<Ratings> getAllRatings(){return Collections.unmodifiableSet(ratings);}
    public  List<Category> getAllCategories(){return Collections.unmodifiableSet(categories).stream().toList();}

    @Override
    public String toString() {
        return "Movies{" +
                "id='" + this.getId() + '\'' +
                "title='" + title + '\'' +
                ", year=" + year +
                ", countCopies=" + countCopies +
                ", director=" + director +
                '}';
    }
}
