package com.example.ecommerce_lite.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDto {
    private Long id;
    private String categoryCode;
    private String name;
    private String description;
    private String imageUrl;
    private String slugId;
    private int activeness;
}
