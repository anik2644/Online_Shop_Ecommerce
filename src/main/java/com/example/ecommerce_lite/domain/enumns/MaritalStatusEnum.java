package com.example.ecommerce_lite.domain.enumns;

import lombok.Getter;

@Getter
public enum MaritalStatusEnum {

    SINGLE(1, 'S', "SINGLE", "Not currently married or in a legal partnership."),
    MARRIED(2, 'M', "MARRIED", "Legally married or in a registered partnership."),
    DIVORCED(3, 'D', "DIVORCED", "Marriage has been legally dissolved."),
    WIDOWED(4, 'W', "WIDOWED", "Spouse has passed away."),
    SEPARATED(5, 'P', "SEPARATED", "Legally or informally separated but not divorced.");

    private final int id;
    private final char code;
    private final String value;
    private final String description;

    MaritalStatusEnum(int id, char code, String value, String description) {
        this.id = id;
        this.code = code;
        this.value = value;
        this.description = description;
    }

    /** Lookup by code */
    public static MaritalStatusEnum fromCode(char code) {
        for (MaritalStatusEnum status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown MaritalStatus code: " + code);
    }

    /** Lookup by ID */
    public static MaritalStatusEnum fromId(int id) {
        for (MaritalStatusEnum status : values()) {
            if (status.id == id) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown MaritalStatus id: " + id);
    }

    /** Lookup by Value */
    public static MaritalStatusEnum fromValue(String value) {
        for (MaritalStatusEnum status : values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown MaritalStatus value: " + value);
    }
}
