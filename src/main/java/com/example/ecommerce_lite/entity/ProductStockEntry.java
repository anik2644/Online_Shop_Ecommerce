package com.example.ecommerce_lite.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "product_stock_entry")
@Getter
@Setter
public class ProductStockEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ FK to product(product_code)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_code", referencedColumnName = "product_code", nullable = false)
    private Product product;

    @Column(name = "quantity_added", nullable = false)
    private Integer quantityAdded;

    @Column(name = "unit_cost", precision = 10, scale = 2)
    private BigDecimal unitCost = BigDecimal.ZERO;

    @Column(name = "source_type", length = 4, nullable = false)
    private String sourceType = "V"; // ProductSourceTypeEnum.code

    @Column(length = 255)
    private String remarks;

    // ✅ Proper FK mapping to user.user_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "added_by_user_id", referencedColumnName = "user_id")
    private User addedByUser;

    @Column(name = "adjudication_status", length = 4, nullable = false)
    private String adjudicationStatus = "U"; // AdjudicationStatusEnum.code

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
