package com.example.ecommerce_lite.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProductStockEntryResponseDto {

    private Long id;                     // Auto-increment PK from table
    private String productCode;          // String FK reference to product.product_code
    private Integer quantityAdded;       // Quantity added in this stock entry
    private BigDecimal unitCost;         // Per-unit cost
    private String sourceType;           // 'V', 'M', etc. (ProductSourceTypeEnum.code)
    private String remarks;              // Any comment or note
    private String addedByUserId;        // String FK to user.user_id
    private String adjudicationStatus;   // 'U' (Unreviewed), etc.
    private LocalDateTime createdAt;     // Timestamp when created
    private LocalDateTime updatedAt;     // Timestamp when updated
}
