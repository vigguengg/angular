package com.vignesh.angular.services.category;

import com.vignesh.angular.dto.CategoryDto;
import com.vignesh.angular.entities.Category;
import com.vignesh.angular.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public Category addCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        if(categoryDto.getId() != null)
        {
            category.setId(categoryDto.getId());
        }
        return categoryRepository.save(category);

    }

    @Override
    public void deleteCategory(Long Id) {
        Category category = new Category();
        category.setId(Id);
        categoryRepository.deleteById(Id);
    }

    @Override
    public Optional<Category> findById(Long Id) {
        return categoryRepository.findById(Id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
