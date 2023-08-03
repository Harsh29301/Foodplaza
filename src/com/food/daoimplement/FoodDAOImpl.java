package com.food.daoimplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.food.dao.FoodDAO;
import com.food.pojo.Food;
import com.food.util.DBConnection;

public class FoodDAOImpl implements FoodDAO {

    String query;

    @Override
    public boolean addFood(Food food) {

        query = "insert into food_items (FoodName, Category, Description, FoodType, Price) values (?,?,?,?,?)";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

                    ps.setString(1, food.getFoodName());
                    ps.setString(2, food.getCategory());
                    ps.setString(3, food.getDescription());
                    ps.setString(4, food.getFoodType());
                    ps.setDouble(5, food.getPrice());

                    int i= ps.executeUpdate();

                    if(i >0){
                        return true;
                    }

        } catch (Exception e) {
           
        }
        return false;
    }

    @Override
    public boolean updateFood(Food food) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateFood'");
    }

    @Override
    public boolean deleteFood(int foodId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteFood'");
    }

    @Override
    public Food viewFoodbyFoodId(int foodId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewFoodbyFoodId'");
    }

    @Override
    public List<Food> viewAllFood() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewAllFood'");
    }

    @Override
    public List<Food> viewFoodsbyFoodName(String foodName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewFoodsbyFoodName'");
    }

    @Override
    public List<Food> viewFoodsbyFoodType(String foodType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewFoodsbyFoodType'");
    }

    @Override
    public List<Food> viewFoodbyFoodCategory(String category) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewFoodbyFoodCategory'");
    }

}
