package com.example.ecommerce_lite.service;

import com.example.ecommerce_lite.dto.ProductDto;
import com.example.ecommerce_lite.dto.ProductResponseDto;
import com.example.ecommerce_lite.entity.Category;
import com.example.ecommerce_lite.entity.Product;
import com.example.ecommerce_lite.repository.CategoryRepository;
import com.example.ecommerce_lite.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductResponseDto createProduct(ProductDto productDto) {
        Product product = toEntity(productDto);
        product = productRepository.save(product);
        return toDto(product);
    }

    @Override
    public ProductResponseDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return toDto(product);
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        updateEntity(product, productDto);
        product = productRepository.save(product);
        return toDto(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponseDto> getProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<Long> getProductIdsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name).stream().map(Product::getId).collect(Collectors.toList());
    }

    private ProductResponseDto toDto(Product product) {
        return new ProductResponseDto(product.getId(), product.getProductCode(), product.getName(), product.getCategory() != null ? product.getCategory().getId() : null, product.getDescription(), product.getMongoRefId(), product.getMetaDescription(), product.getSlugId(), product.getActiveness());
    }

    private Product toEntity(ProductDto productDto) {
        Product product = new Product();
        updateEntity(product, productDto);
        return product;
    }

    private void updateEntity(Product product, ProductDto productDto) {
        product.setProductCode(productDto.getProductCode());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setMongoRefId(productDto.getMongoRefId());
        product.setMetaDescription(productDto.getMetaDescription());
        product.setSlugId(productDto.getSlugId());
        product.setActiveness(productDto.getActiveness());

        if (productDto.getCategoryId() != null) {
            Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategory(category);
        }
    }
}
