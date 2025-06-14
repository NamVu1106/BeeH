package com.beearena.dto;
import lombok.Data;
import java.util.List;
 
@Data
public class CartRequestDTO {
    private List<CartItemDTO> items;
    private String discountCode;
} 