package com.beearena.dto;
import lombok.Data;

@Data
public class CartItemDTO {
    private Integer productId;
    private String version;
    private String color;
    private Integer quantity;
} 