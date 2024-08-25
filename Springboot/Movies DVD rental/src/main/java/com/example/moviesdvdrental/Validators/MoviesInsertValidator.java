package com.example.moviesdvdrental.Validators;

import com.example.moviesdvdrental.DTOs.DirectorDTO.DirectorInsertDTO;
import com.example.moviesdvdrental.DTOs.MoviesDTO.MoviesInsertDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class MoviesInsertValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return MoviesInsertDTO.class == clazz;
    }

    @Override
    public void validate(Object target, Errors errors) {
        MoviesInsertDTO moviesInsertDTO = (MoviesInsertDTO) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "empty");
        if (moviesInsertDTO.getTitle().length() < 3 || moviesInsertDTO.getTitle().length() > 50) {
            errors.reject("firstname", "size");
        }
    }
}
