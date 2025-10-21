package com.example.ecommerce_lite.service;



import com.example.ecommerce_lite.dto.requestBody.ProductStockEntryDto;
import com.example.ecommerce_lite.dto.response.ProductStockEntryResponseDto;

import java.util.List;

public interface ProductStockEntryService {

    ProductStockEntryResponseDto addStockEntry(ProductStockEntryDto dto);

    ProductStockEntryResponseDto getStockEntryById(Long id);

}
