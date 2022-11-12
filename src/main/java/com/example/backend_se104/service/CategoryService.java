package com.example.backend_se104.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    spring.Repository.CategoryRepository categoryRepository;

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    public void removeByCategoryId(String categoryId) {
        Category category = categoryRepository.findByCategoryId(categoryId);
        categoryRepository.delete(category);
    }

    public Category findByCategoryId(String categoryId) {
        return categoryRepository.findByCategoryId(categoryId);
    }

    public Page<Category> getAllCategory(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }
}
