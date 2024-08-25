package com.example.moviesdvdrental.Repositories;

import com.example.moviesdvdrental.model.*;
import com.example.moviesdvdrental.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingsRepository extends JpaRepository<Ratings, CustomerMovieID> {
    @Override
    List<Ratings> findAll();

    List<Ratings> findRatingsByMovie(Movies movies);
    List<Ratings> findRatingsByCustomer(Customer customer);
    List<Ratings> findByMovie_Title(String title);
    List<Ratings> findByMovie_Id(Long id);
}
