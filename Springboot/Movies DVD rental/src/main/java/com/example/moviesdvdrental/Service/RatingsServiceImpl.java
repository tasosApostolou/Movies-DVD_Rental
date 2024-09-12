package com.example.moviesdvdrental.Service;

import com.example.moviesdvdrental.DTOs.RatingsDTO.RatingsInsertDTO;
import com.example.moviesdvdrental.Exceptions.EntityNotFoundException;
import com.example.moviesdvdrental.Repositories.CustomerRepository;
import com.example.moviesdvdrental.Repositories.MoviesRepository;
import com.example.moviesdvdrental.Repositories.RatingsRepository;
import com.example.moviesdvdrental.model.Customer;
import com.example.moviesdvdrental.model.Movies;
import com.example.moviesdvdrental.model.Ratings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class RatingsServiceImpl implements IRatingsService {
    private final RatingsRepository ratingsRepository;
    private final MoviesRepository moviesRepository;
    private final CustomerRepository customerRepository;
    @Override
    @Transactional
    public Ratings AddRating( RatingsInsertDTO dto) throws Exception {
        Ratings rating = new Ratings();
        Movies movie;
        Customer customer;
        try{
            movie = moviesRepository.findById(dto.getMovieId()).orElseThrow(() -> new EntityNotFoundException(Movies.class,dto.getMovieId()));
            customer = customerRepository.findById(dto.getCustomerID()).orElseThrow(() -> new EntityNotFoundException(Customer.class,dto.getCustomerID()));
            rating.addMovie(movie);
            rating.addCustomer(customer);
            rating.setRating(dto.getRating());
            rating = ratingsRepository.save(rating);
            log.info("New rating for movie with id"+ dto.getMovieId());
        }catch (EntityNotFoundException e){
            log.error(e.getMessage());
            throw e;
        }
        return rating;
    }

    @Override
    public List<Ratings> getRatingsByMovieId(Long movieId) throws EntityNotFoundException {
        List<Ratings> ratings = new ArrayList<>();
        Movies movie;
        try {
            movie = moviesRepository.findById(movieId).orElseThrow(() -> new EntityNotFoundException(Movies.class,movieId));
//            System.out.println(movie.getId());
            ratings = movie.getAllRatings().stream().toList();
            log.info("ratings of movie with id "+ movieId  + " were found");
        }catch (EntityNotFoundException e){
            log.error(e.getMessage());
            throw e;
        }
        return ratings;
        // να φτιαξω και για μεσο ορο
    }
}
