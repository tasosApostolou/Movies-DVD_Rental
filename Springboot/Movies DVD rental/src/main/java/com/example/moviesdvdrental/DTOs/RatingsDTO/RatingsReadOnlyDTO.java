package com.example.moviesdvdrental.DTOs.RatingsDTO;

import com.example.moviesdvdrental.DTOs.CustomerDTO.CustomerReadOnlyDTO;
import com.example.moviesdvdrental.DTOs.MoviesDTO.MoviesReadOnlyDTO;
import com.example.moviesdvdrental.model.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RatingsReadOnlyDTO {
    public MoviesReadOnlyDTO movie;
    public CustomerReadOnlyDTO customer;
    public int rating;

    public RatingsReadOnlyDTO(MoviesReadOnlyDTO movie, CustomerReadOnlyDTO customer, int rating) {
        this.movie = movie;
        this.customer = customer;
        this.rating = rating;
    }

}
