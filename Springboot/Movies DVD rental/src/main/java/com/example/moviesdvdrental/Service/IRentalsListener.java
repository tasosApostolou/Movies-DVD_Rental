package com.example.moviesdvdrental.Service;

import com.example.moviesdvdrental.DTOs.RentalDTO.RentalsInsertDTO;
import com.example.moviesdvdrental.DTOs.RentalDTO.RentalsReadOnlyDTO;
import com.example.moviesdvdrental.model.Rentals;

import java.io.IOException;

public interface IRentalsListener {
    void ValidationRentalOrder(RentalsReadOnlyDTO rentalMessage) throws InterruptedException, IOException;
    void checkAvailability(RentalsReadOnlyDTO rentalMessage) throws InterruptedException, IOException;

}
