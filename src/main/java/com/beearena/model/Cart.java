package com.beearena.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
public class Cart {
    private List<CartItem> items = new ArrayList<>();
    public List<CartItem> getItems() { return items; }
    public void setItems(List<CartItem> items) { this.items = items; }
    public void addItem(CartItem item) {
        for (CartItem ci : items) {
            if (ci.getProductId().equals(item.getProductId())) {
                ci.setQuantity(ci.getQuantity() + item.getQuantity());
                return;
            }
        }
        items.add(item);
    }
    public void updateItem(Integer productId, int quantity) {
        for (CartItem ci : items) {
            if (ci.getProductId().equals(productId)) {
                ci.setQuantity(quantity);
                return;
            }
        }
    }
    public void removeItem(Integer productId) {
        items.removeIf(ci -> ci.getProductId().equals(productId));
    }
    public void clear() { items.clear(); }
    public BigDecimal getTotal() {
        return items.stream().map(CartItem::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
} 