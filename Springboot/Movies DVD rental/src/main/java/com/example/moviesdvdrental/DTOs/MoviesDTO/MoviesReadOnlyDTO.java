package com.example.moviesdvdrental.DTOs.MoviesDTO;

import com.example.moviesdvdrental.DTOs.BaseDTO;
import com.example.moviesdvdrental.DTOs.CategoryDTO.CategoryReadOnlyDTO;
import com.example.moviesdvdrental.DTOs.DirectorDTO.DirectorReadOnlyDTO;
import com.example.moviesdvdrental.DTOs.actorDTO.ActorReadOnlyDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.sql.ast.tree.predicate.PredicateCollector;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MoviesReadOnlyDTO extends BaseDTO {
    public String title;
    public int year;
    public int countCopies;
    public DirectorReadOnlyDTO director;
    public List<ActorReadOnlyDTO> actors =new ArrayList<>();
    public List<CategoryReadOnlyDTO> categories = new ArrayList<>();

    public MoviesReadOnlyDTO(Long id,String title, int year, int countCopies, DirectorReadOnlyDTO director) {
        this.setId(id);
        this.title = title;
        this.year = year;
        this.countCopies = countCopies;
        this.director = director;
    }



}
