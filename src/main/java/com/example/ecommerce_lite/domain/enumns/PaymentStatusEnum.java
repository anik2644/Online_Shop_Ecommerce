package com.example.ecommerce_lite.domain.enumns;

import lombok.Getter;

@Getter
public enum PaymentStatusEnum {

    UNPAID(1, 'U', "UNPAID", "Payment is pending or not yet initiated."),
    PENDING(2, 'P', "PENDING", "Payment has been initiated but not confirmed."),
    PARTIALLY_PAID(3, 'T', "PARTIALLY_PAID", "Partial payment has been received."),
    PAID(4, 'D', "PAID", "Full payment has been received and verified."),
    PAYMENT_FAILED(5, 'F', "PAYMENT_FAILED", "Payment attempt failed or was declined."),
    REFUND_REQUESTED(6, 'R', "REFUND_REQUESTED", "User has requested a refund."),
    REFUND_APPROVED(7, 'A', "REFUND_APPROVED", "Refund request has been approved."),
    REFUNDED(8, 'C', "REFUNDED", "Refund successfully processed and completed.");

    private final int id;
    private final char code;
    private final String value;
    private final String description;

    PaymentStatusEnum(int id, char code, String value, String description) {
        this.id = id;
        this.code = code;
        this.value = value;
        this.description = description;
    }

    /** Lookup by code */
    public static PaymentStatusEnum fromCode(char code) {
        for (PaymentStatusEnum status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown PaymentStatus code: " + code);
    }

    /** Lookup by ID */
    public static PaymentStatusEnum fromId(int id) {
        for (PaymentStatusEnum status : values()) {
            if (status.id == id) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown PaymentStatus id: " + id);
    }

    /** Lookup by Value */
    public static PaymentStatusEnum fromValue(String value) {
        for (PaymentStatusEnum status : values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown PaymentStatus value: " + value);
    }
}
