package com.example.moviesdvdrental.Repositories;

import com.example.moviesdvdrental.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    Actor findActorById(Long id);
    List<Actor> findActorByLastnameStartingWith(String lastname);

}
