package com.food.pojo;

public class Food {

    private int foodId;
    private String foodName;
    private String category;
    private String description;
    private double price;
    private String foodType;
    private String image;

    public Food() {
    }

    public Food(String foodName, String category, String description, double price, String foodType, String image) {
        this.foodName = foodName;
        this.category = category;
        this.description = description;
        this.price = price;
        this.foodType = foodType;
        this.image = image;
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

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getFoodId() {
        return foodId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Food [foodId=" + foodId + ", foodName=" + foodName + ", category=" + category + ", description="
                + description + ", price=" + price + ", foodType=" + foodType + ", image=" + image + "]";
    }

    public Food(String foodName, String category, String description, double price, String foodType) {
        this.foodName = foodName;
        this.category = category;
        this.description = description;
        this.price = price;
        this.foodType = foodType;
    }

    

}
