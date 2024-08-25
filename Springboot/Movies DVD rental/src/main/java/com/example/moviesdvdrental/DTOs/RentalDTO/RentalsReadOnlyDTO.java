package com.example.moviesdvdrental.DTOs.RentalDTO;

import com.example.moviesdvdrental.DTOs.CustomerDTO.CustomerReadOnlyDTO;
import com.example.moviesdvdrental.DTOs.MoviesDTO.MoviesReadOnlyDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentalsReadOnlyDTO {
    public MoviesReadOnlyDTO movie;
    public CustomerReadOnlyDTO customer;
    public double price;
}
