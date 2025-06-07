package models;


import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public class Order {

    private UUID orderId;

    @NotNull
    private Customer customer;

    @NotNull
    @Size(min = 1)
    private List<Item> items;

    @NotNull
    private ZonedDateTime orderDate;

    @Lob
    private String xmlPayload;

    private double totalAmount;

    public Order(){}

    public Order(UUID orderId, Customer customer, List<Item> items, ZonedDateTime orderDate, String xmlPayload) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = items;
        this.orderDate = orderDate;
        this.xmlPayload = xmlPayload;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public ZonedDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(ZonedDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getXmlPayload() {
        return xmlPayload;
    }

    public void setXmlPayload(String xmlPayload) {
        this.xmlPayload = xmlPayload;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalAmount() {
        return getItems().stream()
                .mapToDouble(i -> i.getQuantity() * i.getUnitPrice())
                .sum();
    }

}
