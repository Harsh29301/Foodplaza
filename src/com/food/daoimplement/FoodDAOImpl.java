package com.food.daoimplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.food.dao.FoodDAO;
import com.food.pojo.Food;
import com.food.util.DBConnection;

public class FoodDAOImpl implements FoodDAO {

    String query;

    @Override
    public boolean addFood(Food food) {

        query = "insert into food_items (FoodName, Category, Description, FoodType, Price, Image) values (?,?,?,?,?,?)";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, food.getFoodName());
            ps.setString(2, food.getCategory());
            ps.setString(3, food.getDescription());
            ps.setString(4, food.getFoodType());
            ps.setDouble(5, food.getPrice());
            ps.setString(6, food.getImage());

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

        query = "delete from food_items where FoodId = ?";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, foodId);

            int i = ps.executeUpdate();
            if (i > 0) {
                return true;
            }
        } catch (Exception e) {
            
        }
        return false;
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
        List<Food> foodList = new ArrayList<>();
        query = "select FoodId, FoodName, Category, Description, FoodType, Price, Image from food_items";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet resultSet = ps.executeQuery()) {

            while (resultSet.next()) {
                Food nFood = new Food();
                nFood.setFoodId(resultSet.getInt(1));
                nFood.setFoodName(resultSet.getString(2));
                nFood.setCategory(resultSet.getString(3));
                nFood.setDescription(resultSet.getString(4));
                nFood.setFoodType(resultSet.getString(5));
                nFood.setPrice(resultSet.getDouble(6));
                nFood.setImage(resultSet.getString(7));

                foodList.add(nFood);
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        return foodList;
    }

    @Override
    public List<Food> viewFoodsbyFoodName(String foodName) {
        query = "SELECT * FROM food_items WHERE FoodName LIKE ?";
        ResultSet resultSet = null;
        List<Food> foodList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, "%" + foodName + "%");
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Food nFood = new Food();
                nFood.setFoodId(resultSet.getInt("FoodId"));
                nFood.setFoodName(resultSet.getString("FoodName"));
                nFood.setCategory(resultSet.getString("Category"));
                nFood.setDescription(resultSet.getString("Description"));
                nFood.setFoodType(resultSet.getString("FoodType"));
                nFood.setPrice(resultSet.getDouble("Price"));
                nFood.setImage(resultSet.getString("Image"));

                foodList.add(nFood);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        return foodList;
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
