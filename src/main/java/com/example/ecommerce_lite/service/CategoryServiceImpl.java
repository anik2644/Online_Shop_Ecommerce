package com.example.ecommerce_lite.service;

import com.example.ecommerce_lite.dto.CategoryDto;
import com.example.ecommerce_lite.dto.CategoryResponseDto;
import com.example.ecommerce_lite.entity.Category;
import com.example.ecommerce_lite.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponseDto createCategory(CategoryDto categoryDto) {
        Category category = toEntity(categoryDto);
        category = categoryRepository.save(category);
        return toDto(category);
    }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        return toDto(category);
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDto updateCategory(Long id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        updateEntity(category, categoryDto);
        category = categoryRepository.save(category);
        return toDto(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    private CategoryResponseDto toDto(Category category) {
        return new CategoryResponseDto(category.getId(), category.getCategoryCode(), category.getName(), category.getDescription(), category.getImageUrl(), category.getSlugId(), category.getActiveness());
    }

    private Category toEntity(CategoryDto categoryDto) {
        Category category = new Category();
        updateEntity(category, categoryDto);
        return category;
    }

    private void updateEntity(Category category, CategoryDto categoryDto) {
        category.setCategoryCode(categoryDto.getCategoryCode());
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setImageUrl(categoryDto.getImageUrl());
        category.setSlugId(categoryDto.getSlugId());
        category.setActiveness(categoryDto.getActiveness());
    }
}
