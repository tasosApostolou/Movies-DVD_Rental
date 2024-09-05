package com.example.moviesdvdrental.Service;

import com.example.moviesdvdrental.DTOs.RatingsDTO.RatingsInsertDTO;
import com.example.moviesdvdrental.DTOs.RentalDTO.RentalsInsertDTO;
import com.example.moviesdvdrental.Exceptions.EntityNotFoundException;
import com.example.moviesdvdrental.Repositories.CustomerRepository;
import com.example.moviesdvdrental.Repositories.MoviesRepository;
import com.example.moviesdvdrental.Repositories.RatingsRepository;
import com.example.moviesdvdrental.Repositories.RentalsRepository;
import com.example.moviesdvdrental.model.Customer;
import com.example.moviesdvdrental.model.Movies;
import com.example.moviesdvdrental.model.Ratings;
import com.example.moviesdvdrental.model.Rentals;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class RentalsServiceImpl implements IRentalsService{
    private final RentalsRepository rentalsRepository;
    private final MoviesRepository moviesRepository;
    private final CustomerRepository customerRepository;
    @Override
    @Transactional
    public Rentals AddNewRental( RentalsInsertDTO dto) throws Exception {
        Rentals rental = new Rentals();
        Movies movie;
        Customer customer;
        try{
            movie = moviesRepository.findById(dto.getMovieId()).orElseThrow(() -> new EntityNotFoundException(Movies.class,dto.getMovieId()));
            customer = customerRepository.findById(dto.getCustomerId()).orElseThrow(() -> new EntityNotFoundException(Customer.class,dto.getCustomerId()));
            rental.addMovie(movie);
            rental.addCustomer(customer);
            rental.setPrice(dto.getPrice());
            rental = rentalsRepository.save(rental);
            log.info("New rental for movie with id"+ dto.getMovieId());
            System.out.println(rental.toString());
        }catch (EntityNotFoundException e){
            log.error(e.getMessage());
            throw e;
        }
        System.out.println(rental.toString());
        return rental;
    }

    @Override
    public List<Rentals> getRentalsByMovieId(Long movieId) throws EntityNotFoundException {
        List<Rentals> rentals = new ArrayList<>();
        Movies movie;
        try {
            movie = moviesRepository.findById(movieId).orElseThrow(() -> new EntityNotFoundException(Movies.class,movieId));
            rentals = movie.getAllRentals().stream().toList();
            log.info("rentals of movie with id "+ movieId  + " were found");
        }catch (EntityNotFoundException e){
            log.error(e.getMessage());
            throw e;
        }
        return rentals;    }

    @Override
    public List<Rentals> getRentalsByCustomerId(Long customerId) throws EntityNotFoundException {
        List<Rentals> customerRentals = new ArrayList<>();
        Customer customer;
        try {
            customer = customerRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException(Customer.class,customerId));
//            customerRentals = Collections.unmodifiableSet(customer.getRentals()).stream().toList()
            customerRentals = customer.getAllCustomerRentals().stream().toList();
            log.info("rentals of customer with id "+ customerId  + " were found");
        }catch (EntityNotFoundException e){
            log.error(e.getMessage());
            throw e;
        }
        return customerRentals;
    }
}
