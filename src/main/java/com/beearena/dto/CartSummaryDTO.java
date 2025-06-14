package com.beearena.dto;
import lombok.Data;
import java.util.List;

@Data
public class CartSummaryDTO {
    private List<CartItemDTO> items;
    private int subtotal;
    private int discountAmount;
    private int total;
    private String discountMsg;
} 