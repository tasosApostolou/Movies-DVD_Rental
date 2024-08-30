package com.example.moviesdvdrental.Validators;

import com.example.moviesdvdrental.DTOs.EmployeeDTO.EmployeeRegisterDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class EmployeeRegisterValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return EmployeeRegisterDTO.class == clazz;
    }

    @Override
    public void validate(Object target, Errors errors) {
        EmployeeRegisterDTO registeremployeeDTO = (EmployeeRegisterDTO) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"username","empty");
        if(registeremployeeDTO.getUsername().length()< 3 || registeremployeeDTO.getUsername().length() > 50){
            errors.reject("username", "size");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","empty");
        if(registeremployeeDTO.getPassword().length()< 3 || registeremployeeDTO.getPassword().length() > 300){
            errors.reject("password", "size");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"firstname","empty");
        if(registeremployeeDTO.getFirstname().length()< 3 || registeremployeeDTO.getFirstname().length() > 50){
            errors.reject("firstname", "size");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"lastname","empty");
        if(registeremployeeDTO.getLastname().length()< 3 || registeremployeeDTO.getLastname().length() > 50){
            errors.reject("lastname", "size");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"ssn","empty");
        if(registeremployeeDTO.getLastname().length()< 5 || registeremployeeDTO.getLastname().length() > 50){
            errors.reject("ssn", "size");
        }

    }
}
