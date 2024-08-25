package com.example.moviesdvdrental.Service;

import com.example.moviesdvdrental.DTOs.DirectorDTO.DirectorInsertDTO;
import com.example.moviesdvdrental.DTOs.MoviesDTO.MoviesInsertDTO;
import com.example.moviesdvdrental.Exceptions.EntityNotFoundException;
import com.example.moviesdvdrental.model.Director;
import com.example.moviesdvdrental.model.Movies;

import java.util.List;

public interface IMoviesService {
    Movies insert(MoviesInsertDTO dto) throws Exception;
    List<Movies> getMoviesByTitle(String title) throws EntityNotFoundException;
    Movies getMovieById(Long id) throws EntityNotFoundException;
}
