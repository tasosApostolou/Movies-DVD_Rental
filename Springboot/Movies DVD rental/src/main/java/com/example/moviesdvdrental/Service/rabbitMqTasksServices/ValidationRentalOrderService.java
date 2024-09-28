package com.example.moviesdvdrental.Service.rabbitMqTasksServices;

import com.example.moviesdvdrental.DTOs.RentalDTO.RentalsReadOnlyDTO;
import com.example.moviesdvdrental.Exceptions.EntityNotFoundException;
import com.example.moviesdvdrental.RabbitMQ.RabbitMQConfig;
import com.example.moviesdvdrental.Repositories.CustomerRepository;
import com.example.moviesdvdrental.Repositories.MoviesRepository;
import com.example.moviesdvdrental.Repositories.RentalsRepository;
import com.example.moviesdvdrental.Service.RentalsServiceImpl;
import com.example.moviesdvdrental.mapper.Mapper;
import com.example.moviesdvdrental.model.*;
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
public class ValidationRentalOrderService {
    private final RentalsRepository rentalsRepository;
    private final CustomerRepository customerRepository;
    private final MoviesRepository moviesRepository;
    private final RabbitTemplate rabbitTemplate;


    @RabbitListener(queues = RabbitMQConfig.RENTAL_QUEUE) // Consumes messages from the rentalQueue
    @Transactional
    public void ValidationRentalOrder(RentalsReadOnlyDTO rentalBookedMessage){
        try {
            rentalBookedMessage.setStatus(Status.PENDING);
            if(!validBooked(rentalBookedMessage)){
                System.out.println(validBooked(rentalBookedMessage));
                rentalBookedMessage.setStatus(Status.REJECTED);
                log.info("invalid rental");
            }
            rabbitTemplate.convertAndSend(RabbitMQConfig.RENTAL_EXCHANGE,"stocks",rentalBookedMessage);

//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.error(e.getMessage());

        }
    }
    Boolean validBooked(RentalsReadOnlyDTO rentalBookedMessage) {
        Customer customer;
        Movies movie;
        try {
            customer = customerRepository.findById(rentalBookedMessage.getCustomer().getId()).orElseThrow(() -> new EntityNotFoundException(Customer.class,rentalBookedMessage.getCustomer().getId())); // custom exception EntityNotFoundException(Class<?> entityClass, Long id)
            movie = moviesRepository.findById(rentalBookedMessage.getMovie().getId()).orElseThrow(() -> new EntityNotFoundException(Movies.class,rentalBookedMessage.getMovie().getId()));
        }catch (EntityNotFoundException e ){
            rentalBookedMessage.setStatus(Status.REJECTED);
            log.error(e.getMessage());
            return false;
        }

        return true;
    }
}
