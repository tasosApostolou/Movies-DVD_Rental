package com.example.moviesdvdrental.Validators;

import com.example.moviesdvdrental.DTOs.DirectorDTO.DirectorInsertDTO;
import com.example.moviesdvdrental.DTOs.actorDTO.ActorInsertDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class DirectorInsertValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return DirectorInsertDTO.class == clazz;
    }

    @Override
    public void validate(Object target, Errors errors) {
        DirectorInsertDTO directorInsertDTO = (DirectorInsertDTO) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "empty");
        if (directorInsertDTO.getFirstname().length() < 3 || directorInsertDTO.getFirstname().length() > 50) {
            errors.reject("firstname", "size");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "empty");
        if (directorInsertDTO.getLastname().length() < 3 || directorInsertDTO.getLastname().length() > 50) {
            errors.reject("lastname", "size");
        }
    }
}
