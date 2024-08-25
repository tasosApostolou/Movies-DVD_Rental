package com.example.moviesdvdrental.Service;

import com.example.moviesdvdrental.DTOs.CategoryDTO.CategoryInsertDTO;
import com.example.moviesdvdrental.DTOs.DirectorDTO.DirectorInsertDTO;
import com.example.moviesdvdrental.Exceptions.EntityAlreadyExistsException;
import com.example.moviesdvdrental.Exceptions.EntityNotFoundException;
import com.example.moviesdvdrental.model.Category;
import com.example.moviesdvdrental.model.Director;

import java.util.List;

public interface ICategoryService {
    Category insert(CategoryInsertDTO dto) throws Exception;
    Category getCategoryByCategoryName(String categoryName) throws EntityNotFoundException;
    Category getCategoryById(Long id) throws EntityNotFoundException;
}
