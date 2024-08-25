package com.example.moviesdvdrental.Validators;

import com.example.moviesdvdrental.DTOs.CustomerDTO.CustomerUpdateDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CustomerUpdateValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return CustomerUpdateDTO.class == clazz;
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerUpdateDTO personUpdateDTO = (CustomerUpdateDTO) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"firstname","empty");
        if(personUpdateDTO.getFirstname().length()< 3 || personUpdateDTO.getFirstname().length() > 50){
            errors.reject("firstname", "size");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"lastname","empty");
        if(personUpdateDTO.getLastname().length()< 3 || personUpdateDTO.getLastname().length() > 50){
            errors.reject("password", "size");
        }
    }
}
