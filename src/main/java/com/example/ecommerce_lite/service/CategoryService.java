package com.example.ecommerce_lite.service;

import com.example.ecommerce_lite.dto.CategoryDto;
import com.example.ecommerce_lite.dto.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    CategoryResponseDto createCategory(CategoryDto categoryDto);
    CategoryResponseDto getCategoryById(Long id);
    List<CategoryResponseDto> getAllCategories();
    CategoryResponseDto updateCategory(Long id, CategoryDto categoryDto);
    void deleteCategory(Long id);
}
