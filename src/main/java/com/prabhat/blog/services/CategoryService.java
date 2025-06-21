package com.prabhat.blog.services;

import com.prabhat.blog.domain.entities.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface CategoryService {
    List<Category> listCategories();
    Category createCategory(Category category);
    void deleteCategory(UUID id);
    //below is required for the postsServiceImpl
    Category getCategoryById(UUID id);
}
