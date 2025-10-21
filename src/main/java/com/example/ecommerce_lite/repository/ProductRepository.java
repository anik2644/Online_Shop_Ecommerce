package com.example.ecommerce_lite.repository;

import com.example.ecommerce_lite.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // 🔍 Search products by partial name (case-insensitive)
    List<Product> findByNameContainingIgnoreCase(String name);

    // ✅ Check existence by product_code
    boolean existsByProductCode(String productCode);

    // ✅ Fetch a product by product_code (used in service layer)
    Optional<Product> findByProductCode(String productCode);
}
