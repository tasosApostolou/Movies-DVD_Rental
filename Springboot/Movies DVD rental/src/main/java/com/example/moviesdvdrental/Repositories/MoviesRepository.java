package com.example.moviesdvdrental.Repositories;

import com.example.moviesdvdrental.model.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MoviesRepository extends JpaRepository<Movies,Long> {
    Movies findMoviesById(Long id);

    List<Movies> findMoviesByTitleStartingWith(String title);

    @Query("SELECT m FROM Movies m JOIN m.categories c WHERE c.id = :categoryId")
    List<Movies> findMoviesByCategoryId(Long categoryId);

    Movies findByDirector_FirstnameAndDirector_Lastname(String firsname, String Lastname);

    List<Movies> findByCountCopiesGreaterThan(int availableCopies);

    List<Movies> findByTitleStartingWithAndCountCopiesGreaterThan(String title,int availableCopies);
//    @Query("SELECT m FROM Movies m JOIN m.categories c WHERE c.categoryName = :categoryName")
//    List<Movies> findMoviesByCategoryName(Long categoryName);

}
