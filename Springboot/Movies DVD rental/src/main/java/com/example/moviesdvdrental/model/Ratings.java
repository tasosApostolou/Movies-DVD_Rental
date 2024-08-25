package com.example.moviesdvdrental.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@IdClass(CustomerMovieID.class)
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ratings {
    @Id
    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Id
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movies movie;

    private int rating;

    public void addMovie(Movies movie){
        this.setMovie(movie);
        movie.getRatings().add(this);
    }
    public void addCustomer(Customer customer){
        this.setCustomer(customer);
        customer.getRatings().add(this);
    }
    @Override
    public String toString() {
        return "Ratings{" +
                "customer=" + customer +
                ", movie=" + movie +
                ", rating=" + rating +
                '}';
    }
}
