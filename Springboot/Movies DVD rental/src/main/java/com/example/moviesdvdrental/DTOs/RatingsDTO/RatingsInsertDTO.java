package com.example.moviesdvdrental.DTOs.RatingsDTO;

import com.example.moviesdvdrental.DTOs.MoviesDTO.MoviesInsertDTO;
import com.example.moviesdvdrental.DTOs.MoviesDTO.MoviesReadOnlyDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RatingsInsertDTO {
//    public MoviesReadOnlyDTO movie;
    public Long movieId;
    public Long customerID;
    public int rating;
}
