package com.example.ecommerce_lite.controller;

import com.example.ecommerce_lite.dto.ProductDto;
import com.example.ecommerce_lite.dto.ProductResponseDto;
import com.example.ecommerce_lite.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.updateProduct(id, productDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<List<ProductResponseDto>> getProductsByName(@PathVariable String name) {
        return ResponseEntity.ok(productService.getProductsByName(name));
    }

    @GetMapping("/ids/{name}")
    public ResponseEntity<List<Long>> getProductIdsByName(@PathVariable String name) {
        return ResponseEntity.ok(productService.getProductIdsByName(name));
    }
}
