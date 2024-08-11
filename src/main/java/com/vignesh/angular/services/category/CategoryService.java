package com.vignesh.angular.services.category;

import com.vignesh.angular.dto.CategoryDto;
import com.vignesh.angular.entities.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Category addCategory(CategoryDto categoryDto);
    void deleteCategory(Long Id);
    Optional<Category> findById(Long Id);

    List<Category> getAllCategories();
}
