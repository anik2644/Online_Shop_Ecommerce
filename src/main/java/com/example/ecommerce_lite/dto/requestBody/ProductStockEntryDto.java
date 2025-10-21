package com.example.ecommerce_lite.dto.requestBody;
import com.example.ecommerce_lite.domain.enumns.ProductSourceTypeEnum;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * DTO for creating or updating Product Stock Entries.
 *
 * ✅ Includes:
 *  - Full validation via javax/jakarta validation
 *  - @Valid for nested usage in controller
 *  - Safe enum mapping and entity conversion
 */
@Data
@ToString
@NoArgsConstructor
public class ProductStockEntryDto {

    /** Product unique code (FK reference to product.product_code) */
    @NotBlank(message = "Product code is required.")
    @Size(max = 100, message = "Product code cannot exceed 100 characters.")
    private String productCode;

    /** Quantity of stock being added */
    @NotNull(message = "Quantity added is required.")
    @Positive(message = "Quantity must be greater than zero.")
    private Integer quantityAdded;

    /** Unit cost of the product */
    @NotNull(message = "Unit cost is required.")
    @DecimalMin(value = "0.0", inclusive = true, message = "Unit cost cannot be negative.")
    private BigDecimal unitCost;

    /**
     * Source type for this stock entry.
     * Matches ProductSourceTypeEnum:
     *   VENDOR("V", "Vendor"),
     *   MANUFACTURER("M", "Manufacturer"),
     *   OTHER("O", "Other")
     */
    @NotNull(message = "Source type is required.")
    private ProductSourceTypeEnum sourceType;

    /** Optional remarks about the stock entry */
    @Size(max = 255, message = "Remarks cannot exceed 255 characters.")
    private String remarks;

}
