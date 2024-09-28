package com.example.moviesdvdrental.Service.rabbitMqTasksServices;

import com.example.moviesdvdrental.DTOs.RentalDTO.RentalsReadOnlyDTO;
import com.example.moviesdvdrental.Exceptions.EntityNotFoundException;
import com.example.moviesdvdrental.RabbitMQ.RabbitMQConfig;
import com.example.moviesdvdrental.Repositories.CustomerRepository;
import com.example.moviesdvdrental.Repositories.MoviesRepository;
import com.example.moviesdvdrental.Repositories.RentalsRepository;
import com.example.moviesdvdrental.model.Customer;
import com.example.moviesdvdrental.model.Movies;
import com.example.moviesdvdrental.model.Rentals;
import com.example.moviesdvdrental.model.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryService {
    private final RentalsRepository rentalsRepository;
    private final CustomerRepository customerRepository;
    private final MoviesRepository moviesRepository;
    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitMQConfig.STOCKS_QUEUE) // Consumes messages from the inventoryQueue
    @Transactional
    public void checkAvailability(RentalsReadOnlyDTO rentalMessage) {
        Rentals finalRental;
        Movies movie;
        Customer customer;
        try {
            log.info("status at inventory consuming: "+ rentalMessage.getStatus());
            customer = customerRepository.findById(rentalMessage.getCustomer().getId()).orElseThrow(() -> new EntityNotFoundException(Customer.class,rentalMessage.getCustomer().getId())); // custom exception EntityNotFoundException(Class<?> entityClass, Long id)
            movie = moviesRepository.findById(rentalMessage.getMovie().getId()).orElseThrow(() -> new EntityNotFoundException(Movies.class,rentalMessage.getMovie().getId()));

//            rentalMessage.setStatus(Status.REJECTED);// if there is no available stocks or if message is invald
            if(movie.getIsActive() && rentalMessage.getStatus() == Status.PENDING) {
                rentalMessage.setStatus(Status.APPROVED);
                movie.setCountCopies(movie.getCountCopies() - 1); // reduce a stock from availability
            }else {
                rentalMessage.setStatus(Status.REJECTED);// if there is no available stocks or if message is invald
            }

            if (movie.getCountCopies()==0){
//                rentalMessage.setStatus(Status.REJECTED);
                movie.setIsActive(false);
            }
            // update the  rental after validation recognized by hibernate by id
            finalRental = new Rentals(customer,movie,rentalMessage.getPrice(),rentalMessage.getStatus());
            rentalsRepository.saveAndFlush(finalRental);


        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
