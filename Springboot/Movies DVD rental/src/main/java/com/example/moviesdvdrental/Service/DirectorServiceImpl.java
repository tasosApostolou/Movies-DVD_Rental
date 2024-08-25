package com.example.moviesdvdrental.Service;

import com.example.moviesdvdrental.DTOs.DirectorDTO.DirectorInsertDTO;
import com.example.moviesdvdrental.DTOs.actorDTO.ActorInsertDTO;
import com.example.moviesdvdrental.Exceptions.EntityNotFoundException;
import com.example.moviesdvdrental.Repositories.DirectorsRepository;
import com.example.moviesdvdrental.mapper.Mapper;
import com.example.moviesdvdrental.model.Actor;
import com.example.moviesdvdrental.model.Director;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DirectorServiceImpl implements IDirectorService {
    private final DirectorsRepository directorsRepository;  //injected as autowired by annotation @RequiredArgsConstructor
    @Override
    @Transactional
    public Director insert(DirectorInsertDTO dto) throws Exception {
        Director director;
        try {
            director = directorsRepository.save(Mapper.mapToDirector(dto));
            if (director.getId() == null) {
                throw new Exception("Insert error");
            }
            log.info("insert succes for director with id" + director.getId());
            return director;
        } catch (Exception e) {
            log.error("insert error " + e.getMessage());
            throw e;
        }
    }
    @Override
    public List<Director> getDirectorsByLastname(String lastname) throws EntityNotFoundException {
        List<Director> directors = new ArrayList<>();
        try {
            directors = directorsRepository.findByLastnameStartingWith(lastname);
            if (directors.isEmpty()) throw new EntityNotFoundException(Director.class,0L);
            log.info("Directors with lastname starting with "+ lastname +" were found");
        }catch (EntityNotFoundException e){
            log.error(e.getMessage());
            throw e;
        }
        return directors;
    }

    @Override
    public Director getDirectorById(Long id) throws EntityNotFoundException {
        Director director;
        try {
//                director = directorRepository.findDirectorById(id);
//                if(director==null)throw new EntityNotFoundException(Director.class,id);
            director = directorsRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(Director.class,id));
        }catch (EntityNotFoundException e){
            log.error(e.getMessage());
            throw e;
        }
        return director;
    }
}
