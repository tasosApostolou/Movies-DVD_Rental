package com.example.moviesdvdrental.RestController;

import com.example.moviesdvdrental.DTOs.CategoryDTO.CategoryInsertDTO;
import com.example.moviesdvdrental.DTOs.CategoryDTO.CategoryReadOnlyDTO;
import com.example.moviesdvdrental.Exceptions.EntityNotFoundException;
import com.example.moviesdvdrental.Service.ICategoryService;
import com.example.moviesdvdrental.Validators.CategoryInsertValidator;
import com.example.moviesdvdrental.mapper.Mapper;
import com.example.moviesdvdrental.model.Category;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryRestController {
    private final ICategoryService categoryService;
    private final CategoryInsertValidator categoryInsertValidator;
    @Operation(summary = "Get a Category by title")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Category not found",
                    content = @Content)})
    @GetMapping("/{categoryName}")
    public ResponseEntity<CategoryReadOnlyDTO> getCategoryByName(@PathVariable("categoryName") String categoryName) {
        Category category;

        try {
            category = categoryService.getCategoryByCategoryName(categoryName);
            CategoryReadOnlyDTO dto = Mapper.mapToReadOnlyDTO(category);
            return ResponseEntity.ok(dto);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "Get a Category by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Category not found",
                    content = @Content)})
    @GetMapping("/{categoryID}")
    public ResponseEntity<CategoryReadOnlyDTO> getCategory(@PathVariable("categoryID") Long id) {
        Category category;

        try {
            category = categoryService.getCategoryById(id);
            CategoryReadOnlyDTO dto = Mapper.mapToReadOnlyDTO(category);
            return ResponseEntity.ok(dto);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    //
    @Operation(summary = "Add a Category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input was supplied",
                    content = @Content),
            @ApiResponse(responseCode = "503", description = "Service Unavailable",
                    content = @Content)})
    @PostMapping("/add")
    public ResponseEntity<CategoryReadOnlyDTO> addCategory(@Valid @RequestBody CategoryInsertDTO dto, BindingResult bindingResult) {
        categoryInsertValidator.validate(dto, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            Category category = categoryService.insert(dto);
            CategoryReadOnlyDTO categoryReadOnlyDTO = Mapper.mapToReadOnlyDTO(category);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(categoryReadOnlyDTO.getId())
                    .toUri();
            return ResponseEntity.created(location).body(categoryReadOnlyDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
