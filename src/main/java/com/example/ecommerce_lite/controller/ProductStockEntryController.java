package com.example.ecommerce_lite.controller;


import com.example.ecommerce_lite.dto.requestBody.ProductStockEntryDto;

import com.example.ecommerce_lite.dto.response.ProductStockEntryResponseDto;
import com.example.ecommerce_lite.service.ProductStockEntryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-entries")
public class ProductStockEntryController {

    private final ProductStockEntryService stockEntryService;

    public ProductStockEntryController(ProductStockEntryService stockEntryService) {
        this.stockEntryService = stockEntryService;
    }

    // 🔹 Add new stock for a product
    @PostMapping
    public ResponseEntity<ProductStockEntryResponseDto> addStockEntry(@RequestBody ProductStockEntryDto dto) {
        return new ResponseEntity<>(stockEntryService.addStockEntry(dto), HttpStatus.CREATED);
    }

    // 🔹 Get stock entry by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductStockEntryResponseDto> getStockEntryById(@PathVariable Long id) {
        return ResponseEntity.ok(stockEntryService.getStockEntryById(id));
    }


}
