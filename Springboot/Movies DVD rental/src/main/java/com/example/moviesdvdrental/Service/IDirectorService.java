package com.example.moviesdvdrental.Service;

import com.example.moviesdvdrental.DTOs.DirectorDTO.DirectorInsertDTO;
import com.example.moviesdvdrental.DTOs.actorDTO.ActorInsertDTO;
import com.example.moviesdvdrental.Exceptions.EntityNotFoundException;
import com.example.moviesdvdrental.model.Actor;
import com.example.moviesdvdrental.model.Director;

import java.util.List;

public interface IDirectorService {
    Director insert(DirectorInsertDTO dto) throws Exception;
    List<Director> getDirectorsByLastname(String lastname) throws EntityNotFoundException;
    Director getDirectorById(Long id) throws EntityNotFoundException;
    List<Director> getAllDirectors() throws Exception;
}
