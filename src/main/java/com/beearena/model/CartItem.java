package com.beearena.model;
import java.math.BigDecimal;
public class CartItem {
    private Integer productId;
    private String productName;
    private String image;
    private String category;
    private BigDecimal price;
    private int quantity;
    public CartItem() {}
    public CartItem(Integer productId, String productName, String image, String category, BigDecimal price, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.image = image;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }
    public Integer getProductId() { return productId; }
    public void setProductId(Integer productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public BigDecimal getTotal() { return price.multiply(BigDecimal.valueOf(quantity)); }
} 