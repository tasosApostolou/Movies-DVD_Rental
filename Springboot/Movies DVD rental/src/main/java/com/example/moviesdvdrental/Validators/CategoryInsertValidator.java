package com.example.moviesdvdrental.Validators;

import com.example.moviesdvdrental.DTOs.CategoryDTO.CategoryInsertDTO;
import com.example.moviesdvdrental.DTOs.DirectorDTO.DirectorInsertDTO;
import com.example.moviesdvdrental.model.Category;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CategoryInsertValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return CategoryInsertDTO.class == clazz;
    }

    @Override
    public void validate(Object target, Errors errors) {
        CategoryInsertDTO categoryInsertDTO = (CategoryInsertDTO) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categoryName", "empty");
        if (categoryInsertDTO.getCategoryName().length() < 3 || categoryInsertDTO.getCategoryName().length() > 50) {
            errors.reject("categoryName", "size");
        }
    }
}
