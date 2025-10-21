package com.example.ecommerce_lite.domain.mapper;

import com.example.ecommerce_lite.dto.response.ProductStockEntryResponseDto;
import com.example.ecommerce_lite.entity.ProductStockEntry;
import org.springframework.stereotype.Component;

/**
 * Handles entity ↔ DTO mapping for ProductStockEntry.
 * Keeps service layer clean.
 */
@Component
public class ProductStockEntryMapper {

    public ProductStockEntryResponseDto toResponse(ProductStockEntry entity) {
        if (entity == null) return null;

        ProductStockEntryResponseDto dto = new ProductStockEntryResponseDto();
        dto.setId(entity.getId());
        dto.setProductCode(entity.getProduct().getProductCode());
        dto.setQuantityAdded(entity.getQuantityAdded());
        dto.setUnitCost(entity.getUnitCost());
        dto.setSourceType(entity.getSourceType());
        dto.setRemarks(entity.getRemarks());
        dto.setAddedByUserId(entity.getAddedByUser().getUserId());
        dto.setAdjudicationStatus(entity.getAdjudicationStatus());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }
}
