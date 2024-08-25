package com.example.moviesdvdrental.Repositories;

import com.example.moviesdvdrental.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalsRepository extends JpaRepository<Rentals, CustomerMovieID> {
    @Override
    List<Rentals> findAll();

    List<Rentals> findRentalsByMovie(Movies movies);
    List<Rentals> findRentalsByCustomer(Customer customer);
    List<Rentals> findByMovie_Title(String title);
    List<Rentals> findByMovie_Id(Long id);
}
