package com.example.moviesdvdrental.DTOs.MoviesDTO;

import com.example.moviesdvdrental.DTOs.BaseDTO;
import com.example.moviesdvdrental.DTOs.actorDTO.ActorReadOnlyDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class MoviesActorsDTO extends BaseDTO {
    public String title;
    public int year;
    public int countCopies;
    public List<ActorReadOnlyDTO> actors;

    public MoviesActorsDTO(String title, int year, int countCopies) {
        this.title = title;
        this.year = year;
        this.countCopies = countCopies;
    }

    public MoviesActorsDTO(String title, int year, int countCopies, List<ActorReadOnlyDTO> actors) {
        this.title = title;
        this.year = year;
        this.countCopies = countCopies;
        this.actors = actors;
    }
}
