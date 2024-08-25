package com.example.moviesdvdrental.DTOs.RatingsDTO;

import com.example.moviesdvdrental.DTOs.MoviesDTO.MoviesInsertDTO;
import com.example.moviesdvdrental.DTOs.MoviesDTO.MoviesReadOnlyDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingsInsertDTO {
//    public MoviesReadOnlyDTO movie;
    public Long movieId;
    public Long customerID;
    public int rating;
}
