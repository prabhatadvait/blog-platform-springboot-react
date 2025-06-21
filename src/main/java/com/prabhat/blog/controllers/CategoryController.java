package com.prabhat.blog.controllers;

import com.prabhat.blog.domain.dtos.CategoryDto;
import com.prabhat.blog.domain.dtos.CreateCategoryRequest;
import com.prabhat.blog.domain.entities.Category;
import com.prabhat.blog.mappers.CategoryMapper;
import com.prabhat.blog.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor
public class  CategoryController {

    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper; // to convert the category class to category data

    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCategories() {  // we can't return the Category 'cause it's class so we will return Dto
        List<CategoryDto> categories = categoryService.listCategories()
                .stream().map(categoryMapper::toDto)
                .toList();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(
         @Valid @RequestBody CreateCategoryRequest createCategoryRequest) {
        Category categoryToCreate = categoryMapper.toEntity(createCategoryRequest);
        Category savedCategory = categoryService.createCategory(categoryToCreate);
        return new ResponseEntity<>(
                categoryMapper.toDto(savedCategory),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // for this special response we gonna handle exception in errorController
    }
}
