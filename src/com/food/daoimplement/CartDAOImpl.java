package com.food.daoimplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.food.dao.CartDAO;
import com.food.pojo.Cart;
import com.food.util.DBConnection;

public class CartDAOImpl implements CartDAO {

    String query;
    int cartId;
    String emailId;
    int foodId;
    String foodName;
    int quantity;
    double price;
    double totalPrice;

    @Override
    public boolean addToCart(Cart cart) {

        query = "inser into Cart (emailId, foodId, foodName, quantity, price, totalPrice) values (?,?,?,?,?,?)";
        try (Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {

                //boolean isFoodPresent = getFoodFromCart(cart.getFoodId());
                if(false){

                }
                else{

                }
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        return false;
    }

    @Override
    public Cart viewCartById(int cartId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewCartById'");
    }

    @Override
    public boolean updateCart(int cartId, int quantity) {

        query = "update Cart set quantity = ? where cartId = ?";
        try (Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            
                ps.setInt(1, quantity);
                ps.setInt(2, cartId);

                int  i = ps.executeUpdate();

                if( i>0){
                    return true;
                }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return false;
    }

    @Override
    public boolean deleteCartById(int cartId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCartById'");
    }

    @Override
    public boolean deleteCartByEmailId(String emailId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCartByEmailId'");
    }

    @Override
    public List<Cart> viewCartByEmailId(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewCartByEmailId'");
    }

    @Override
    public List<Cart> viewCart() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewCart'");
    }

    private boolean checkFoodFromCart(int foodId, String emailId){

        query = "select cartId,quantity from Cart where foodId = ? and emailId = ?";

        ResultSet resultSet = null;
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

                    ps.setInt(1, foodId);
                    ps.setString(2, emailId);
                     resultSet= ps.executeQuery();
                     if(resultSet.next()){
                        int cartId = resultSet.getInt("cartId");
                        int food_ID = resultSet.getInt("foodId");
                        int quantity = resultSet.getInt("quantity");

                        return true;
                     }
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        finally{
            try {
                if(resultSet != null){
                    resultSet.close();
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        return false;
    }
}
