package com.example.ecommerce_lite.domain.enumns;

import lombok.Getter;

@Getter
public enum ProductSourceTypeEnum {

    DROPSHIPPING(1, 'D', "DROPSHIPPING", "Product is fulfilled by a third-party supplier via dropshipping."),
    VENDOR(2, 'V', "VENDOR", "Product is sourced directly from a registered vendor or supplier."),
    LOCAL(3, 'L', "LOCAL", "Product is produced or procured locally by the store."),
    MANUFACTURER(4, 'M', "MANUFACTURER", "Product is supplied directly from the manufacturer."),
    WHOLESALE(5, 'W', "WHOLESALE", "Product purchased in bulk from a wholesaler."),
    IMPORTED(6, 'I', "IMPORTED", "Product is imported from a foreign market or partner."),
    AFFILIATE(7, 'A', "AFFILIATE", "Product is promoted through affiliate partnership.");

    private final int id;

    private final char code;
    private final String value;
    private final String description;

    ProductSourceTypeEnum(int id, char code, String value, String description) {
        this.id = id;
        this.code = code;
        this.value = value;
        this.description = description;
    }

    /** Lookup by code */
    public static ProductSourceTypeEnum fromCode(char code) {
        for (ProductSourceTypeEnum type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown ProductSourceType code: " + code);
    }

    /** Lookup by ID */
    public static ProductSourceTypeEnum fromId(int id) {
        for (ProductSourceTypeEnum type : values()) {
            if (type.id == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown ProductSourceType id: " + id);
    }

    /** Lookup by Value */
    public static ProductSourceTypeEnum fromValue(String value) {
        for (ProductSourceTypeEnum type : values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown ProductSourceType value: " + value);
    }
}
