package com.example.ecommerce_lite.service;

import com.example.ecommerce_lite.entity.SpecificProductCount;
import com.example.ecommerce_lite.repository.SpecificProductCountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpecificProductCountService {

    private final SpecificProductCountRepository specificProductCountRepository;

    @Autowired
    public SpecificProductCountService(SpecificProductCountRepository specificProductCountRepository) {
        this.specificProductCountRepository = specificProductCountRepository;
    }

    /**
     * Add a new SpecificProductCount.
     */
    public SpecificProductCount addSpecificProductCount(SpecificProductCount newEntry) {
        return specificProductCountRepository.save(newEntry);
    }

    /**
     * Get SpecificProductCount by product code.
     */
    public SpecificProductCount getSpecificProductCountByProductCode(String productCode) {
        return specificProductCountRepository.findByProductCode(productCode)
                .orElseThrow(() -> new IllegalArgumentException("Product count not found for product code: " + productCode));
    }

    /**
     * Update SpecificProductCount by its ID.
     */
    public SpecificProductCount updateSpecificProductCount(Long id, SpecificProductCount updatedEntry) {
        SpecificProductCount existingEntry = specificProductCountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product count not found with ID: " + id));

        // Update fields
        existingEntry.setTotalBought(updatedEntry.getTotalBought());
        existingEntry.setTotalPending(updatedEntry.getTotalPending());
        existingEntry.setPendingRequestAccepted(updatedEntry.getPendingRequestAccepted());
        existingEntry.setInDelivery(updatedEntry.getInDelivery());
        existingEntry.setDeliveredSuccess(updatedEntry.getDeliveredSuccess());
        existingEntry.setDeliveryFailed(updatedEntry.getDeliveryFailed());
        existingEntry.setReturnedItems(updatedEntry.getReturnedItems());
        existingEntry.setCancelledOrders(updatedEntry.getCancelledOrders());

        // Save the updated entry
        return specificProductCountRepository.save(existingEntry);
    }

}
