package com.example.ecommerce_lite.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Entity class representing the 'specific_product_count' table.
 *
 * This table tracks the statistics of each product including counts for orders, deliveries, returns, etc.
 *
 * It references the 'product' table via 'product_code' as the foreign key.
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "specific_product_count")
public class SpecificProductCount {

    /** Primary Key, auto-generated */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /** Product code reference — links to 'product.product_code' */
    @Column(name = "product_code", length = 100, nullable = false)
    private String productCode;

    /** Total products bought */
    @Column(name = "total_bought", columnDefinition = "INT DEFAULT 0")
    private Integer totalBought = 0;

    /** Total products pending */
    @Column(name = "total_pending", columnDefinition = "INT DEFAULT 0")
    private Integer totalPending = 0;

    /** Products whose requests were accepted but not delivered yet */
    @Column(name = "pending_request_accepted", columnDefinition = "INT DEFAULT 0")
    private Integer pendingRequestAccepted = 0;

    /** Products currently in delivery */
    @Column(name = "in_delivery", columnDefinition = "INT DEFAULT 0")
    private Integer inDelivery = 0;

    /** Successful deliveries */
    @Column(name = "delivered_success", columnDefinition = "INT DEFAULT 0")
    private Integer deliveredSuccess = 0;

    /** Failed delivery attempts */
    @Column(name = "delivery_failed", columnDefinition = "INT DEFAULT 0")
    private Integer deliveryFailed = 0;

    /** Returned items */
    @Column(name = "returned_items", columnDefinition = "INT DEFAULT 0")
    private Integer returnedItems = 0;

    /** Cancelled orders */
    @Column(name = "cancelled_orders", columnDefinition = "INT DEFAULT 0")
    private Integer cancelledOrders = 0;

    /** Automatically updated timestamp by MySQL */
    @Column(name = "last_updated", insertable = false, updatable = false)
    private LocalDateTime lastUpdated;

    // ============================================================
    // 🔹 FK Relationship to Product
    // ============================================================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_code", referencedColumnName = "product_code", insertable = false, updatable = false)
    private Product product;

}
