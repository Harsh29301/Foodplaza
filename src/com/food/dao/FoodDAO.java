package com.food.dao;

import java.util.List;

import com.food.pojo.Food;

public interface FoodDAO {

    boolean addFood(Food food);

    boolean updateFood(Food food);

    boolean deleteFood(int foodId);

    Food viewFoodbyFoodId(int foodId);

    List<Food> viewAllFood();

    List<Food> viewFoodsbyFoodName(String foodName);

    List<Food> viewFoodsbyFoodType(String foodType);

    List<Food> viewFoodbyFoodCategory(String category);

    public boolean updateImage (int foodId, String path);
}
