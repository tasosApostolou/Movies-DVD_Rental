package com.example.moviesdvdrental.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

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

    @Enumerated(EnumType.STRING)
    private Status status;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rentals rentals)) return false;

        if (Double.compare(getPrice(), rentals.getPrice()) != 0) return false;
        if (!getCustomer().equals(rentals.getCustomer())) return false;
        if (!getMovie().equals(rentals.getMovie())) return false;
        return getStatus() == rentals.getStatus();
    }

}
