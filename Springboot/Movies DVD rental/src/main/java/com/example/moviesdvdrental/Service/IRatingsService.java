package com.example.moviesdvdrental.Service;

import com.example.moviesdvdrental.DTOs.RatingsDTO.RatingsInsertDTO;
import com.example.moviesdvdrental.Exceptions.EntityNotFoundException;
import com.example.moviesdvdrental.model.Ratings;

import java.util.List;

public interface IRatingsService {
    Ratings AddRating(RatingsInsertDTO dto) throws Exception;
    List<Ratings> getRatingsByMovieId(Long movieId) throws EntityNotFoundException;
}
