package com.example.moviesdvdrental.Service;

import com.example.moviesdvdrental.DTOs.actorDTO.ActorInsertDTO;
import com.example.moviesdvdrental.Exceptions.EntityNotFoundException;
import com.example.moviesdvdrental.model.Actor;

import java.util.List;

public interface IActorService {
    Actor insert(ActorInsertDTO dto) throws Exception;
    List<Actor> getActorsByLastname(String lastname) throws EntityNotFoundException;
    Actor getActorById(Long id) throws EntityNotFoundException;
    List<Actor> getAllActors() throws EntityNotFoundException;

}
