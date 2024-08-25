package com.example.moviesdvdrental.DTOs.RentalDTO;

import com.example.moviesdvdrental.DTOs.MoviesDTO.MoviesReadOnlyDTO;
import com.example.moviesdvdrental.model.Customer;
import com.example.moviesdvdrental.model.Movies;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class RentalsInsertDTO {
    private Long movieId;
    private Long customerId;
    public double price;

}
