package com.food.pojo;

public class Food {

    private int foodId;
    private String foodName;
    private String category;
    private String description;
    private double price;
    private String foodType;

    public Food() {
    }

    public Food(String foodName, String category, String description, double price, String foodType) {
        this.foodName = foodName;
        this.category = category;
        this.description = description;
        this.price = price;
        this.foodType = foodType;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public int getFoodId() {
        return foodId;
    }

    @Override
    public String toString() {
        return "Food [foodId=" + foodId + ", foodName=" + foodName + ", category=" + category + ", description="
                + description + ", price=" + price + ", foodType=" + foodType + "]";
    }

}
