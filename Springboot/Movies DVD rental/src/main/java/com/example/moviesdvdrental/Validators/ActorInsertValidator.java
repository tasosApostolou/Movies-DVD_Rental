package com.example.moviesdvdrental.Validators;

import com.example.moviesdvdrental.DTOs.actorDTO.ActorInsertDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ActorInsertValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ActorInsertDTO.class == clazz;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ActorInsertDTO actorInsertDTO = (ActorInsertDTO) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "empty");
        if (actorInsertDTO.getFirstname().length() < 3 || actorInsertDTO.getFirstname().length() > 50) {
            errors.reject("firstname", "size");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "empty");
        if (actorInsertDTO.getLastname().length() < 3 || actorInsertDTO.getLastname().length() > 50) {
            errors.reject("lastname", "size");
        }
    }
}
