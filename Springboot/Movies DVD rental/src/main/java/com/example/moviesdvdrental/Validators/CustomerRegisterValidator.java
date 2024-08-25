package com.example.moviesdvdrental.Validators;

import com.example.moviesdvdrental.DTOs.CustomerDTO.CustomerRegisterDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CustomerRegisterValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return CustomerRegisterDTO.class == clazz;
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerRegisterDTO registerCustomerDTO = (CustomerRegisterDTO) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"username","empty");
        if(registerCustomerDTO.getUsername().length()< 3 || registerCustomerDTO.getUsername().length() > 50){
            errors.reject("username", "size");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","empty");
        if(registerCustomerDTO.getPassword().length()< 3 || registerCustomerDTO.getPassword().length() > 300){
            errors.reject("password", "size");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"firstname","empty");
        if(registerCustomerDTO.getFirstname().length()< 3 || registerCustomerDTO.getFirstname().length() > 50){
            errors.reject("firstname", "size");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"lastname","empty");
        if(registerCustomerDTO.getLastname().length()< 3 || registerCustomerDTO.getLastname().length() > 50){
            errors.reject("lastname", "size");
        }

    }
}
