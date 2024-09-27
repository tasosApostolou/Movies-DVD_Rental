package com.example.moviesdvdrental.DTOs.CategoryDTO;

import com.example.moviesdvdrental.DTOs.BaseDTO;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CategoryReadOnlyDTO extends BaseDTO {
    public String categoryName;

    public CategoryReadOnlyDTO(Long id,String categoryName) {
        this.setId(id);
        this.categoryName = categoryName;
    }
}
