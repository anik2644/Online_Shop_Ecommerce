package com.example.ecommerce_lite.domain.enumns;

import lombok.Getter;

@Getter
public enum GenderEnum {

    MALE(1, 'M', "MALE", "Identifies as male."),
    FEMALE(2, 'F', "FEMALE", "Identifies as female."),
    OTHER(3, 'O', "OTHER", "Identifies as other or prefers not to specify.");

    private final int id;
    private final char code;
    private final String value;
    private final String description;

    GenderEnum(int id, char code, String value, String description) {
        this.id = id;
        this.code = code;
        this.value = value;
        this.description = description;
    }

    /** Lookup by code */
    public static GenderEnum fromCode(char code) {
        for (GenderEnum gender : values()) {
            if (gender.code == code) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Unknown Gender code: " + code);
    }

    /** Lookup by ID */
    public static GenderEnum fromId(int id) {
        for (GenderEnum gender : values()) {
            if (gender.id == id) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Unknown Gender id: " + id);
    }

    /** Lookup by Value */
    public static GenderEnum fromValue(String value) {
        for (GenderEnum gender : values()) {
            if (gender.value.equalsIgnoreCase(value)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Unknown Gender value: " + value);
    }
}
