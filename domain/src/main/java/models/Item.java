package models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class Item {

    private String productId;

    @Positive
    private int quantity;

    @Positive
    private double unitPrice;

    public Item(String productId, int quantity, double unitPrice) {
        this.productId = productId;
        this.quantity = quantity;
    }
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
