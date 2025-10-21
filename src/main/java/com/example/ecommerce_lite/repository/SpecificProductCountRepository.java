package com.example.ecommerce_lite.repository;

import com.example.ecommerce_lite.entity.SpecificProductCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecificProductCountRepository extends JpaRepository<SpecificProductCount, Long> {

    /**
     * Find SpecificProductCount by product code.
     * @param productCode the product code
     * @return Optional<SpecificProductCount>
     */
    Optional<SpecificProductCount> findByProductCode(String productCode);

    // No need for 'findByProductCodeAndUpdate'
    // Add any custom query methods if necessary
}
