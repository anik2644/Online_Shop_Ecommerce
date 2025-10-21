package com.example.ecommerce_lite.domain.enumns;

import lombok.Getter;

@Getter
public enum RoleEnum {

    // 🔹 System-Level Roles
    SUPER_ADMIN(1, 'S', "SUPER_ADMIN", "Has unrestricted access to all modules and administrative operations."),
    ADMIN(2, 'A', "ADMIN", "Manages users, vendors, and platform-wide configurations."),
    MANAGER(3, 'M', "MANAGER", "Oversees business operations, sales, and logistics performance."),
    STAFF(4, 'T', "STAFF", "Supports administrative and customer operations under management supervision."),

    // 🔹 Vendor Roles
    VENDOR_OWNER(5, 'V', "VENDOR_OWNER", "Owner of a vendor account with full vendor management privileges."),
    VENDOR_STAFF(6, 'W', "VENDOR_STAFF", "Vendor employee responsible for handling orders, stock, and listings."),

    // 🔹 Delivery & Logistics Roles
    DELIVERY_ADMIN(7, 'D', "DELIVERY_ADMIN", "Manages all delivery personnel, assignments, and tracking operations."),
    PICKUP_AGENT(8, 'P', "PICKUP_AGENT", "Collects packages from vendors or warehouses for sorting."),

    // 🔹 Customer Roles
    CUSTOMER(9, 'C', "CUSTOMER", "Registered customer who places and tracks orders."),
    GUEST(10, 'G', "GUEST", "Unregistered user with limited browsing privileges.");

    private final int id;
    private final char code;
    private final String value;
    private final String description;

    RoleEnum(int id, char code, String value, String description) {
        this.id = id;
        this.code = code;
        this.value = value;
        this.description = description;
    }

    /** Lookup by code */
    public static RoleEnum fromCode(char code) {
        for (RoleEnum role : values()) {
            if (role.code == code) {
                return role;
            }
        }
        throw new IllegalArgumentException("Unknown Role code: " + code);
    }

    /** Lookup by ID */
    public static RoleEnum fromId(int id) {
        for (RoleEnum role : values()) {
            if (role.id == id) {
                return role;
            }
        }
        throw new IllegalArgumentException("Unknown Role id: " + id);
    }

    /** Lookup by Value */
    public static RoleEnum fromValue(String value) {
        for (RoleEnum role : values()) {
            if (role.value.equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Unknown Role value: " + value);
    }
}
