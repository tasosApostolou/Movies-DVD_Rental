package com.example.moviesdvdrental.Service;

import com.example.moviesdvdrental.DTOs.RatingsDTO.RatingsInsertDTO;
import com.example.moviesdvdrental.DTOs.RentalDTO.RentalsInsertDTO;
import com.example.moviesdvdrental.Exceptions.EntityNotFoundException;
import com.example.moviesdvdrental.model.Ratings;
import com.example.moviesdvdrental.model.Rentals;

import java.util.List;

public interface IRentalsService {
    Rentals AddNewRental(RentalsInsertDTO dto) throws Exception;
    List<Rentals> getRentalsByMovieId(Long movieId) throws EntityNotFoundException;
    List<Rentals> getRentalsByCustomerId(Long customerId) throws EntityNotFoundException;
}
