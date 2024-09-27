//package com.example.moviesdvdrental.Service;
//
//import com.example.moviesdvdrental.DTOs.RentalDTO.RentalsReadOnlyDTO;
//import com.example.moviesdvdrental.Exceptions.EntityNotFoundException;
//import com.example.moviesdvdrental.RabbitMQ.RabbitMQConfig;
//import com.example.moviesdvdrental.Repositories.CustomerRepository;
//import com.example.moviesdvdrental.Repositories.MoviesRepository;
//import com.example.moviesdvdrental.Repositories.RentalsRepository;
//import com.example.moviesdvdrental.mapper.Mapper;
//import com.example.moviesdvdrental.model.*;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.hibernate.proxy.EntityNotFoundDelegate;
//import org.hibernate.query.Order;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.io.IOException;
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class RentalListenerService implements IRentalsListener {
////
//
//    private final RentalsRepository rentalsRepository;
//    private final CustomerRepository customerRepository;
//    private final MoviesRepository moviesRepository;
//    private final RabbitTemplate rabbitTemplate;
//
//    @RabbitListener(queues = RabbitMQConfig.RENTAL_QUEUE) // Consumes messages from the rentalQueue
//    @Transactional
//    public void ValidationRentalOrder(RentalsReadOnlyDTO rentalMessage) throws InterruptedException, IOException {
//        Rentals rentalBooked;
//        Customer customer;
//        Movies movie;
//        try {
//            customer = customerRepository.findById(rentalMessage.getCustomer().getId()).orElseThrow(() -> new EntityNotFoundException(Customer.class,rentalMessage.getCustomer().getId())); // custom exception EntityNotFoundException(Class<?> entityClass, Long id)
//            movie = moviesRepository.findById(rentalMessage.getMovie().getId()).orElseThrow(() -> new EntityNotFoundException(Movies.class,rentalMessage.getMovie().getId()));
//            rentalBooked = rentalsRepository.findByCustomerAndMovie(customer,movie).orElseThrow(() -> new Exception("rental could not found"));
////            rentalBooked = rentalsRepository.findRentalsByCustomer_IdAndMovie_Id(customer.getId(),movie.getId()).orElseThrow(() -> new Exception("rental could not found"));
//
////            rentalBooked = rentalsRepository.findById(new CustomerMovieID(rentalMessage.getCustomer().getId(), rentalMessage.getMovie().getId())).orElseThrow(() -> new EntityNotFoundException(Rentals.class,rentalMessage.getMovie().getId()));
//            rentalBooked.setStatus(Status.PENDING);
//            if(!validPrice(rentalMessage.getPrice())){
//                rentalBooked.setStatus(Status.REJECTED);
////                log.info("invalid price");
//                throw new Exception("low price");
//            }
//            Rentals updatedRental = rentalsRepository.saveAndFlush(rentalBooked);
//            RentalsReadOnlyDTO rentalToSend = Mapper.mapToReadOnlyDTO(updatedRental);
//            rabbitTemplate.convertAndSend(RabbitMQConfig.RENTAL_EXCHANGE,"stocks",rentalToSend);
////            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @RabbitListener(queues = RabbitMQConfig.STOCKS_QUEUE) // Consumes messages from the inventoryQueue
//    @Transactional
//    public void checkAvailability(RentalsReadOnlyDTO rentalMessage) throws InterruptedException, IOException {
//        Rentals rentalBooked;
//        Movies movie;
//        Customer customer;
//        try {
////            rentalBooked = rentalsRepository.findById(new CustomerMovieID(rentalMessage.getCustomer().getId(), rentalMessage.getMovie().getId())).orElseThrow(() -> new EntityNotFoundException(Rentals.class,rentalMessage.getMovie().getId()));
//            customer = customerRepository.findById(rentalMessage.getCustomer().getId()).orElseThrow(() -> new EntityNotFoundException(Customer.class,rentalMessage.getCustomer().getId())); // custom exception EntityNotFoundException(Class<?> entityClass, Long id)
//            movie = moviesRepository.findById(rentalMessage.getMovie().getId()).orElseThrow(() -> new EntityNotFoundException(Movies.class,rentalMessage.getMovie().getId()));
//            rentalBooked = rentalsRepository.findByCustomerAndMovie(customer,movie).orElseThrow(() -> new Exception("rental could not found"));
//            rentalBooked.setStatus(Status.REJECTED); // if there is no available stocks
//            if(movie.getCountCopies()>0) {
//                rentalBooked.setStatus(Status.APPROVED);
//                rentalBooked.getMovie().setCountCopies(movie.getCountCopies() - 1); // reduce a stock from availability
//            }
//            Rentals rental = rentalsRepository.saveAndFlush(rentalBooked);
//            if (rental.getMovie().getCountCopies()==0){
//                rental.getMovie().setIsActive(false);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    Boolean validPrice(double price){
//        return price>10;
//    }
//}
