package com.food.pojo;

import java.time.LocalDateTime;

public class OrderFood {
    
    private String emailId;
    private int cartId;
    private int foodId;
    private String foodName;
    private int quantity;
    private double price;
    private double totalPrice;
    private int orderId;
    private LocalDateTime orderdate;
    private double totalBill;
    private String orderStatus;
    public OrderFood() {
    }
    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    public int getCartId() {
        return cartId;
    }
    public void setCartId(int cartId) {
        this.cartId = cartId;
    }
    public int getFoodId() {
        return foodId;
    }
    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }
    public String getFoodName() {
        return foodName;
    }
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public LocalDateTime getOrderdate() {
        return orderdate;
    }
    public void setOrderdate(LocalDateTime timestamp) {
        this.orderdate = timestamp;
    }
    public double getTotalBill() {
        return totalBill;
    }
    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }
    public String getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    @Override
    public String toString() {
        return "OrderFood [emailId=" + emailId + ", orderId=" + orderId + ", orderdate=" + orderdate + ", totalBill="
                + totalBill + ", orderStatus=" + orderStatus + "]";
    }
    public  OrderFood(String emailId, int orderId, LocalDateTime orderdate, double totalBill, String orderStatus) {
        this.emailId = emailId;
        this.orderId = orderId;
        this.orderdate = orderdate;
        this.totalBill = totalBill;
        this.orderStatus = orderStatus;
    }

    
}
