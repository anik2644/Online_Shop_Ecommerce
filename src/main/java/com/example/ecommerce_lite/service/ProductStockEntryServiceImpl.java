package com.example.ecommerce_lite.service;

import com.example.ecommerce_lite.domain.mapper.ProductStockEntryMapper;
import com.example.ecommerce_lite.domain.validation.ProductStockEntryValidator;
import com.example.ecommerce_lite.dto.requestBody.ProductStockEntryDto;
import com.example.ecommerce_lite.dto.response.ProductStockEntryResponseDto;
import com.example.ecommerce_lite.entity.Product;
import com.example.ecommerce_lite.entity.ProductStockEntry;
import com.example.ecommerce_lite.entity.SpecificProductCount;
import com.example.ecommerce_lite.entity.User;
import com.example.ecommerce_lite.repository.ProductRepository;
import com.example.ecommerce_lite.repository.ProductStockEntryRepository;
import com.example.ecommerce_lite.repository.SpecificProductCountRepository;
import com.example.ecommerce_lite.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductStockEntryServiceImpl implements ProductStockEntryService {

    private final ProductStockEntryRepository stockEntryRepository;
    private final ProductStockEntryValidator validator;
    private final ProductStockEntryMapper mapper;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final SpecificProductCountRepository specificProductCountRepository;

    /**
     * Method to add a new product stock entry.
     *
     * @param dto the ProductStockEntryDto containing details of the stock entry
     * @return ProductStockEntryResponseDto with the saved product stock entry details
     */
    @Override
    public ProductStockEntryResponseDto addStockEntry(ProductStockEntryDto dto) {
        // ✅ Step 1: Validate input DTO
        validator.validate(dto);

        // ✅ Step 2: Fetch the Product entity based on product code (foreign key)
        Product product = productRepository.findByProductCode(dto.getProductCode())
                .orElseThrow(() -> new RuntimeException(
                        "Product with code '" + dto.getProductCode() + "' does not exist."));

        // ✅ Step 3: Fetch the AddedByUser entity (foreign key)
        // In production, the user should be fetched from the logged-in user context
        User user = userRepository.findByUserId("vendor_owner_002")
                .orElseThrow(() -> new RuntimeException("User 'vendor_owner_002' not found."));

        // ✅ Step 4: Map DTO to ProductStockEntry entity
        ProductStockEntry entry = new ProductStockEntry();
        entry.setProduct(product);
        entry.setQuantityAdded(dto.getQuantityAdded());
        entry.setUnitCost(dto.getUnitCost());
        entry.setSourceType(dto.getSourceType().getCode());  // Assuming ProductSourceTypeEnum is used here
        entry.setRemarks(dto.getRemarks());
        entry.setAddedByUser(user);
        entry.setAdjudicationStatus("U"); // Default: Unreviewed

        // ✅ Step 5: Save the ProductStockEntry entity to the database
        ProductStockEntry savedEntry = stockEntryRepository.save(entry);

        // ✅ Step 6: Update product count stats in the 'specific_product_count' table
        updateProductCount(dto.getProductCode(), dto.getQuantityAdded());

        // ✅ Step 7: Map the saved ProductStockEntry entity to a Response DTO and return it
        return mapper.toResponse(savedEntry);
    }

    /**
     * Method to get a product stock entry by its ID.
     *
     * @param id the ID of the ProductStockEntry
     * @return ProductStockEntryResponseDto containing the details of the product stock entry
     */
    @Override
    public ProductStockEntryResponseDto getStockEntryById(Long id) {
        ProductStockEntry entry = stockEntryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Stock entry not found with ID: " + id));
        return mapper.toResponse(entry);
    }

    /**
     * Helper method to update the product count stats in 'specific_product_count' table.
     * This updates statistics such as total bought and total pending quantities.
     *
     * @param productCode the product code for which the count needs to be updated
     * @param quantityAdded the quantity of stock added
     */
    private void updateProductCount(String productCode, Integer quantityAdded) {
        // Fetch the SpecificProductCount entry for the given product code
        SpecificProductCount productCount = specificProductCountRepository.findByProductCode(productCode)
                .orElseGet(() -> {
                    // If not found, create a new SpecificProductCount with default values
                    SpecificProductCount newProductCount = new SpecificProductCount();
                    newProductCount.setProductCode(productCode);
                    newProductCount.setTotalBought(0);
                    newProductCount.setTotalPending(0);
                    newProductCount.setPendingRequestAccepted(0);
                    newProductCount.setInDelivery(0);
                    newProductCount.setDeliveredSuccess(0);
                    newProductCount.setDeliveryFailed(0);
                    newProductCount.setReturnedItems(0);
                    newProductCount.setCancelledOrders(0);
                    // Returning the newly created product count
                    return newProductCount;
                });

        // Update the relevant fields in the SpecificProductCount entity
        productCount.setTotalBought(productCount.getTotalBought() + quantityAdded);

        // Save the updated SpecificProductCount entity (new or existing)
        specificProductCountRepository.save(productCount);
    }

}
