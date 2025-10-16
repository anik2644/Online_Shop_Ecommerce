package com.example.ecommerce_lite.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
    private Long id;
    private String productCode;
    private String name;
    private Long categoryId;
    private String description;
    private String mongoRefId;
    private String metaDescription;
    private String slugId;
    private int activeness;
}
