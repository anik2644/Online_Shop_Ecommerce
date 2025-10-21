package com.example.ecommerce_lite.repository;

import com.example.ecommerce_lite.entity.ProductStockEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStockEntryRepository extends JpaRepository<ProductStockEntry, Long> {
}
