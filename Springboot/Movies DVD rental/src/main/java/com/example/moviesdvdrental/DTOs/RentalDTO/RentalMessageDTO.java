package com.example.moviesdvdrental.DTOs.RentalDTO;

import com.example.moviesdvdrental.DTOs.CustomerDTO.CustomerReadOnlyDTO;
import com.example.moviesdvdrental.DTOs.MoviesDTO.MoviesReadOnlyDTO;
import com.example.moviesdvdrental.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentalMessageDTO {
    public MoviesReadOnlyDTO movie;
    public CustomerReadOnlyDTO customer;
    public double price;
    //    @Enumerated(EnumType.STRING)
    public Status status;
}