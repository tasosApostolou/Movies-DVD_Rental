package com.example.moviesdvdrental.DTOs.MoviesDTO;

import com.example.moviesdvdrental.DTOs.BaseDTO;
import com.example.moviesdvdrental.DTOs.DirectorDTO.DirectorReadOnlyDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MoviesReadOnlyDTO extends BaseDTO {
    public String title;
    public int year;
    public int countCopies;
    public DirectorReadOnlyDTO director;

    public MoviesReadOnlyDTO(Long id,String title, int year, int countCopies, DirectorReadOnlyDTO director) {
        this.setId(id);
        this.title = title;
        this.year = year;
        this.countCopies = countCopies;
        this.director = director;
    }


}
