package com.example.ecommerce_lite.service;

import com.example.ecommerce_lite.dto.ProductDto;
import com.example.ecommerce_lite.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {
    ProductResponseDto createProduct(ProductDto productDto);
    ProductResponseDto getProductById(Long id);
    List<ProductResponseDto> getAllProducts();
    ProductResponseDto updateProduct(Long id, ProductDto productDto);
    void deleteProduct(Long id);

    List<ProductResponseDto> getProductsByName(String name);

    List<Long> getProductIdsByName(String name);
}
