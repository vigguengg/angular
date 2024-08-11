package com.vignesh.angular.controller;

import com.vignesh.angular.dto.CategoryDto;
import com.vignesh.angular.entities.Category;
import com.vignesh.angular.repositories.CategoryRepository;
import com.vignesh.angular.services.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("add")
    public ResponseEntity<Category> createCategory(@RequestBody  CategoryDto categoryDto){
        System.out.println("inside category add" + categoryDto.getName());
        Category response = categoryService.addCategory(categoryDto);

        ResponseEntity<Category> responseEntity = ResponseEntity.ok(response);
        responseEntity.getHeaders().add("Access-Control-Allow-Origin","*");
        return responseEntity;
    }

    @GetMapping(path = "get")
    public ResponseEntity<List<Category>> getAllCategories(){
        System.out.println("inside get list");
        List<Category> categories = categoryService.getAllCategories();
        System.out.println(categories.size());
        for(Category cate : categories) {
            System.out.println(cate.getName());
        }
        //responseList.getHeaders().add("Access-Control-Allow-Origin","*");
        return ResponseEntity.ok(categories);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        Optional<Category> existingCategory = this.categoryService.findById(id);
        if(existingCategory.isPresent()){
            this.categoryService.deleteCategory(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
