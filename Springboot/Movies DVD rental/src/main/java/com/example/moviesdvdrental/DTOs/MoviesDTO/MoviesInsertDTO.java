package com.example.moviesdvdrental.DTOs.MoviesDTO;

import com.example.moviesdvdrental.DTOs.BaseDTO;
import com.example.moviesdvdrental.DTOs.CategoryDTO.CategoryInsertDTO;
import com.example.moviesdvdrental.DTOs.DirectorDTO.DirectorInsertDTO;
import com.example.moviesdvdrental.DTOs.actorDTO.ActorInsertDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MoviesInsertDTO{
    public String title;
    public int year;
    public int countCopies;
    public DirectorInsertDTO director;
    public List<CategoryInsertDTO> categories;
    public List<ActorInsertDTO> actors;
}
