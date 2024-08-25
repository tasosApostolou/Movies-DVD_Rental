package com.example.moviesdvdrental.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Actor extends AbstractEntity{
    @Column
    private String firstname;
    @Column
    private String lastname;

    @Getter (AccessLevel.PROTECTED)
    @ManyToMany(mappedBy = "actors")
    private Set<Movies> movies = new HashSet<>();

    public Actor(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Set<Movies> getAllMovies(){return Collections.unmodifiableSet(movies);
    }
}
