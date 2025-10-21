package com.example.ecommerce_lite.domain.validation;

import com.example.ecommerce_lite.dto.requestBody.ProductStockEntryDto;
import com.example.ecommerce_lite.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Performs business-level validation for ProductStockEntryDto.
 * (Field-level validation is handled via @Valid in the DTO)
 */
@Component
@RequiredArgsConstructor
public class ProductStockEntryValidator {

    private final ProductRepository productRepository;

    /**
     * Validates business rules for stock entry.
     * Throws IllegalArgumentException on invalid data.
     */
    public void validate(ProductStockEntryDto dto) {
        // 🔹 Product existence check (business-level)
        boolean productExists = productRepository.existsByProductCode(dto.getProductCode());
        if (!productExists) {
            throw new IllegalArgumentException(
                    "Product with code '" + dto.getProductCode() + "' does not exist."
            );
        }

    }
}
