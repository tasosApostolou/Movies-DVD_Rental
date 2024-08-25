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
public class Rentals {
    @Id
    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Id
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movies movie;

    private double price;

    public void addCustomer(Customer customer) {
        this.setCustomer(customer);
        customer.getRentals().add(this);
    }
    public void addMovie(Movies movie){
        this.setMovie(movie);
        movie.getRentals().add(this);
    }
    @Override
    public String toString() {
        return "Rentals{" +
                "customer=" + customer.toString() +
                ", movie=" + movie.toString() +
                ", price=" + price +
                '}';
    }
}
