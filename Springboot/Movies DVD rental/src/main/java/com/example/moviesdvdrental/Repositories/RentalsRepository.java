package com.example.moviesdvdrental.Repositories;

import com.example.moviesdvdrental.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
public interface RentalsRepository extends JpaRepository<Rentals, CustomerMovieID> {
    @Override
    List<Rentals> findAll();

//    @Override
//    @Modifying
//    @Transactional // Explicit transaction management
//    <S extends Rentals> S save(S entity);

    Optional<Rentals> findByCustomerAndMovie(Customer customer, Movies movie);
    Rentals findRentalsByCustomer_IdAndMovie_Id(Long customerId,Long movieId);
    List<Rentals> findRentalsByMovie(Movies movies);
    List<Rentals> findRentalsByCustomer(Customer customer);
    List<Rentals> findByMovie_Title(String title);
    List<Rentals> findByMovie_Id(Long id);

}
