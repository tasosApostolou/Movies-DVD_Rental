package com.example.moviesdvdrental.Service;

import com.example.moviesdvdrental.DTOs.actorDTO.ActorInsertDTO;
import com.example.moviesdvdrental.Exceptions.EntityNotFoundException;
import com.example.moviesdvdrental.Repositories.ActorRepository;
import com.example.moviesdvdrental.mapper.Mapper;
import com.example.moviesdvdrental.model.Actor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActorServiceImpl implements IActorService {
    private final ActorRepository actorRepository; //injected as autowired by annotation @RequiredArgsConstructor
    @Override
    @Transactional
    public Actor insert(ActorInsertDTO dto) throws Exception {
        Actor actor;
        try {
            actor = actorRepository.save(Mapper.mapToActor(dto));
            if (actor.getId() == null) {
                throw new Exception("Insert error");
            }
            log.info("insert succes for author with id" + actor.getId());
            return actor;
        } catch (Exception e) {
            log.error("insert error " + e.getMessage());
            throw e;
        }
    }
        @Override
        public List<Actor> getActorsByLastname(String lastname) throws EntityNotFoundException {
            List<Actor> actors = new ArrayList<>();
            try {
                actors = actorRepository.findActorByLastnameStartingWith(lastname);
                if (actors.isEmpty()) throw new EntityNotFoundException(Actor.class,0L);
                log.info("Actors with title starting with "+ lastname +" were found");
            }catch (EntityNotFoundException e){
                log.error(e.getMessage());
                throw e;
            }
            return actors;
        }

        @Override
        public Actor getActorById(Long id) throws EntityNotFoundException {
            Actor actor;
            try {
//                actor = actorRepository.findActorById(id);
//                if(actor==null)throw new EntityNotFoundException(Actor.class,id);
                actor = actorRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(Actor.class,id));
            }catch (EntityNotFoundException e){
                log.error(e.getMessage());
                throw e;
            }
            return actor;
        }

    @Override
    public List<Actor> getAllActors() throws EntityNotFoundException {
        List<Actor> actors = new ArrayList<>();
        try {
            actors = actorRepository.findAll();
            if (actors.isEmpty()) throw new EntityNotFoundException(Actor.class,0L);
            log.info("Actors" +" were found");
        }catch (EntityNotFoundException e){
            log.error(e.getMessage());
            throw e;
        }
        return actors;
    }
}
