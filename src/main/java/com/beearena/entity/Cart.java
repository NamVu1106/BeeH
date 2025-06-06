package com.beearena.entity;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public List<CartItem> getItems() {
        return items;
    }

    public void addItem(CartItem item) {
        for (CartItem i : items) {
            if (i.getProductId().equals(item.getProductId())) {
                i.setQuantity(i.getQuantity() + item.getQuantity());
                return;
            }
        }
        items.add(item);
    }

    public void removeItem(Long productId) {
        items.removeIf(i -> i.getProductId().equals(productId));
    }

    public void updateQuantity(Long productId, int quantity) {
        for (CartItem i : items) {
            if (i.getProductId().equals(productId)) {
                i.setQuantity(quantity);
                return;
            }
        }
    }

    public double getTotal() {
        return items.stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum();
    }
} 