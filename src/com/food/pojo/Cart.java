package com.food.pojo;

public class Cart {

    private int cartId;
    private String emailId;

    private int foodId;
    private String foodName;
    private int quantity;
    private double price;

    private double totalPrice;

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
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

    public Cart() {
    }

    public Cart(String emailId, int foodId, String foodName, int quantity, double price, double totalPrice) {
        this.emailId = emailId;
        this.foodId = foodId;
        this.foodName = foodName;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Cart [cartId=" + cartId + ", emailId=" + emailId + ", foodId=" + foodId + ", foodName=" + foodName
                + ", quantity=" + quantity + ", price=" + price + ", totalPrice=" + totalPrice + "]";
    }

}
