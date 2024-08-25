package com.example.moviesdvdrental.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Director extends AbstractEntity{
    @Column
    private String firstname;
    @Column
    private String lastname;

    @OneToMany(mappedBy = "director", cascade = CascadeType.ALL)
    @Getter(AccessLevel.PUBLIC)
    private Set<Movies> movies = new HashSet<>();

    public Director(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Director director)) return false;

        if (!getFirstname().equals(director.getFirstname())) return false;
        if (!getLastname().equals(director.getLastname())) return false;
        return getMovies() != null ? getMovies().equals(director.getMovies()) : director.getMovies() == null;
    }

    @Override
    public int hashCode() {
        int result = getFirstname().hashCode();
        result = 31 * result + getLastname().hashCode();
        return result;
    }

    public Set<Movies> getAllMovies(){
        return Collections.unmodifiableSet(movies);
    }

    public void addMovie(Movies movie){
        this.movies.add(movie);
        movie.setDirector(this);
    }

    @Override
    public String toString() {
        return "Director{" +
                "id='" + this.getId() + '\'' +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
