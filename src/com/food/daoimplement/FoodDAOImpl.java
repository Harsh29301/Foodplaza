package com.food.daoimplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

            int i = ps.executeUpdate();

            if (i > 0) {
                return true;
            }

        } catch (Exception e) {

        }
        return false;
    }

    @Override
    public boolean updateFood(Food food) {
        query = "update food_items set FoodName=?, Category=?, Description=?, FoodType=?, Price=? where FoodId = ?";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, food.getFoodName());
            ps.setString(2, food.getCategory());
            ps.setString(3, food.getDescription());
            ps.setString(4, food.getFoodType());
            ps.setDouble(5, food.getPrice());
            ps.setInt(6, food.getFoodId());

            int i = ps.executeUpdate();

            if (i > 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteFood(int foodId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteFood'");
    }

    @Override
    public Food viewFoodbyFoodId(int foodId) {

        query = "select FoodName, Category, Description, FoodType, Price from food_items where FoodId = ?";
        ResultSet resultSet = null;
        Food food = null;

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, foodId);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                food = new Food();
                food.setFoodId(foodId);
                food.setFoodName(resultSet.getString(1));
                food.setCategory(resultSet.getString(2));
                food.setDescription(resultSet.getString(3));
                food.setFoodType(resultSet.getString(4));
                food.setPrice(resultSet.getDouble(5));
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {

                }
            }
        }
        return food;
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
