package com.example.ecommerce_lite.domain.enumns;

import lombok.Getter;

@Getter
    public enum AdjudicationStatusEnum {

    UNPREVIEWED(1, 'U', "UNPREVIEWED", "Item has not yet been previewed or reviewed."),
    PREVIEWED(2, 'P', "PREVIEWED", "Item has been previewed or reviewed.");

    private final int id;
    private final char code;
    private final String value;
    private final String description;

    AdjudicationStatusEnum(int id, char code, String value, String description) {
        this.id = id;
        this.code = code;
        this.value = value;
        this.description = description;
    }

    /** Lookup by code */
    public static AdjudicationStatusEnum fromCode(char code) {
        for (AdjudicationStatusEnum status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown AdjudicationStatus code: " + code);
    }

    /** Lookup by ID */
    public static AdjudicationStatusEnum fromId(int id) {
        for (AdjudicationStatusEnum status : values()) {
            if (status.id == id) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown AdjudicationStatus id: " + id);
    }

    /** Lookup by Value */
    public static AdjudicationStatusEnum fromValue(String value) {
        for (AdjudicationStatusEnum status : values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown AdjudicationStatus value: " + value);
    }
}
