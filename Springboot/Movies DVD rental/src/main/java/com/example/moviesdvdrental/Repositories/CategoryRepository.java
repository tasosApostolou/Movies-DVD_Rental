package com.example.moviesdvdrental.Repositories;

import com.example.moviesdvdrental.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findDistinctFirstByCategoryName(String category);
//    Category findCategoryByCategoryNameStartingWith(String category);
}
