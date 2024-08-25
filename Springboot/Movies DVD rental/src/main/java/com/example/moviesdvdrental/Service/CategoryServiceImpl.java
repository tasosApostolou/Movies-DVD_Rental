package com.example.moviesdvdrental.Service;

import com.example.moviesdvdrental.DTOs.CategoryDTO.CategoryInsertDTO;
import com.example.moviesdvdrental.DTOs.DirectorDTO.DirectorInsertDTO;
import com.example.moviesdvdrental.Exceptions.EntityAlreadyExistsException;
import com.example.moviesdvdrental.Exceptions.EntityNotFoundException;
import com.example.moviesdvdrental.Repositories.CategoryRepository;
import com.example.moviesdvdrental.Repositories.DirectorsRepository;
import com.example.moviesdvdrental.mapper.Mapper;
import com.example.moviesdvdrental.model.Category;
import com.example.moviesdvdrental.model.Director;
import com.example.moviesdvdrental.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {
    private final CategoryRepository categoryRepository;  //injected as autowired by annotation @RequiredArgsConstructor
    @Override
    @Transactional
    public Category insert(CategoryInsertDTO dto) throws Exception {
        Category category;
        try {
            category = Mapper.mapToCategory(dto);
            Optional<Category> categoryToCreate = categoryRepository.findDistinctFirstByCategoryName(category.getCategoryName());
            if (categoryToCreate.isPresent()) throw new EntityAlreadyExistsException(Category.class,dto.getCategoryName());
            category = categoryRepository.save(category);
            if(category.getId()==null){
                throw new Exception("Insert error");
            }
            log.info("insert succes for category with id"+ category.getId());
            return category;
        } catch (EntityAlreadyExistsException e) {
            log.error("insert error " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Category getCategoryByCategoryName(String categoryName) throws EntityNotFoundException {
        Category category;
        try {
            category = categoryRepository.findDistinctFirstByCategoryName(categoryName).orElseThrow(() -> new EntityNotFoundException(Category.class,0L));
//            if (directors.isEmpty()) throw new EntityNotFoundException(Director.class,0L);
            log.info("Category with title  "+ categoryName +" was found");
        }catch (EntityNotFoundException e){
            log.error(e.getMessage());
            throw e;
        }
        return category;    }

    @Override
    public Category getCategoryById(Long id) throws EntityNotFoundException {
        Category category;
        try {
//                category = categoryRepository.findCategoryById(id);
//                if(category==null)throw new EntityNotFoundException(Category.class,id);
            category = categoryRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(Category.class,id));
        }catch (EntityNotFoundException e){
            log.error(e.getMessage());
            throw e;
        }
        return category;
    }
}
