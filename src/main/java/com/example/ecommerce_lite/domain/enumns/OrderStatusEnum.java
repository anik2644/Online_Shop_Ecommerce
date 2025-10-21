package com.example.ecommerce_lite.domain.enumns;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {

    PENDING(1, 'P', "PENDING", "Order has been placed and is awaiting confirmation."),
    ACCEPTED(2, 'A', "ACCEPTED", "Seller has accepted the order for fulfillment."),
    REJECTED(3, 'R', "REJECTED", "Seller has rejected the order."),
    PROCESSING(4, 'O', "PROCESSING", "Order is being processed and verified."),
    PACKED(5, 'K', "PACKED", "Order has been packed and labeled for shipping."),
    READY_FOR_DISPATCH(6, 'D', "READY_FOR_DISPATCH", "Order is ready to be handed over to courier."),
    IN_DELIVERY(7, 'I', "IN_DELIVERY", "Order is currently out for delivery."),
    DELIVERED(8, 'L', "DELIVERED", "Order successfully delivered to customer."),
    DELIVERY_FAILED(9, 'F', "DELIVERY_FAILED", "Delivery attempt failed or customer unavailable."),
    RETURN_REQUESTED(10, 'Q', "RETURN_REQUESTED", "Customer has requested a return."),
    RETURN_APPROVED(11, 'V', "RETURN_APPROVED", "Seller approved the return request."),
    RETURN_IN_TRANSIT(12, 'T', "RETURN_IN_TRANSIT", "Returned product is being shipped back."),
    RETURNED(13, 'N', "RETURNED", "Returned product received at warehouse."),
    REFUND_PENDING(14, 'E', "REFUND_PENDING", "Refund process has been initiated and is pending."),
    REFUND_COMPLETED(15, 'C', "REFUND_COMPLETED", "Refund completed successfully."),
    CANCELLED(16, 'X', "CANCELLED", "Order was cancelled by customer or admin before dispatch.");

    private final int id;
    private final char code;
    private final String value;
    private final String description;

    OrderStatusEnum(int id, char code, String value, String description) {
        this.id = id;
        this.code = code;
        this.value = value;
        this.description = description;
    }

    /** Lookup by code */
    public static OrderStatusEnum fromCode(char code) {
        for (OrderStatusEnum status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown OrderStatus code: " + code);
    }

    /** Lookup by ID */
    public static OrderStatusEnum fromId(int id) {
        for (OrderStatusEnum status : values()) {
            if (status.id == id) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown OrderStatus id: " + id);
    }

    /** Lookup by Value */
    public static OrderStatusEnum fromValue(String value) {
        for (OrderStatusEnum status : values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown OrderStatus value: " + value);
    }
}
