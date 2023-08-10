package com.food.daoimplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.food.dao.CartDAO;
import com.food.pojo.Cart;
import com.food.pojo.Food;
import com.food.util.DBConnection;

public class CartDAOImpl implements CartDAO {

    String query;

    @Override
    public boolean addToCart(Cart cart) {

        boolean isFoodPresent = checkFoodInCart(cart.getFoodId(), cart.getEmailId());
        if (isFoodPresent) {

            return true;
        } else {
            query = "insert into Cart (emailId, foodId, foodName, quantity, price, totalPrice) values (?,?,?,?,?,?)";
            try (Connection connection = DBConnection.getConnection();
                    PreparedStatement ps = connection.prepareStatement(query)) {

                ps.setString(1, cart.getEmailId());
                ps.setInt(2, cart.getFoodId());
                Food viewFoodById = new FoodDAOImpl().viewFoodbyFoodId(cart.getFoodId());
                ps.setString(3, viewFoodById.getFoodName());
                ps.setInt(4, cart.getQuantity());
                ps.setDouble(5, viewFoodById.getPrice());

                double total = viewFoodById.getPrice() * cart.getQuantity();
                ps.setDouble(6, total);

                int i = ps.executeUpdate();
                if (i > 0) {
                    return true;
                }

            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        return false;
    }

    @Override
    public Cart viewCartById(int cartId) {
        Cart cart = null;
        query = " select * from Cart where cartId = ?";

        ResultSet rs = null;
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query);) {
            ps.setInt(1, cartId);
            rs = ps.executeQuery();

            while (rs.next()) {
                cart = new Cart();
                cart.setCartId(cartId);
                cart.setEmailId(rs.getString(2));
                cart.setFoodId(rs.getInt(3));
                cart.setFoodName(rs.getString(4));
                cart.setQuantity(rs.getInt(5));
                cart.setPrice(rs.getDouble(6));
                cart.setTotalPrice(rs.getDouble(7));

            }

        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        return cart;
    }

    @Override
    public boolean updateCart(int cartId, int quantity) {

        query = "update Cart set quantity = ?, totalPrice =? where cartId = ?";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

            double updatetotal = 0;
            int pq = 0;
            Cart ncart = viewCartById(cartId);
            if (ncart != null) {
                pq = ncart.getQuantity() + quantity;
                double total = ncart.getPrice();
                updatetotal = total * pq;
            } else {
                System.out.println("Invalid Cart Id.");
            }
            ps.setInt(1, pq);
            ps.setDouble(2, updatetotal);
            ps.setInt(3, cartId);

            int i = ps.executeUpdate();

            if (i > 0) {
                return true;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return false;
    }

    @Override
    public boolean deleteCartItems(int cartId) {
        query = "delete from Cart where cartId = ?";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, cartId);

            int i = ps.executeUpdate();
            if (i > 0) {
                return true;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return false;
    }

    @Override
    public List<Cart> viewCartByEmailId(String email) {
        List<Cart> carList = new ArrayList<>();
        query = " select * from Cart where emailId = ?";

        ResultSet rs = null;
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query);) {
            ps.setString(1, email);
            rs = ps.executeQuery();

            while (rs.next()) {
                Cart nCart = new Cart();
                nCart.setCartId(rs.getInt(1));
                nCart.setEmailId(rs.getString(2));
                nCart.setFoodId(rs.getInt(3));
                nCart.setFoodName(rs.getString(4));
                nCart.setQuantity(rs.getInt(5));
                nCart.setPrice(rs.getDouble(6));
                nCart.setTotalPrice(rs.getDouble(7));

                carList.add(nCart);
            }

        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        return carList;
    }

    @Override
    public List<Cart> viewCart() {

        List<Cart> carList = new ArrayList<>();
        query = " select * from Cart";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cart nCart = new Cart();
                nCart.setCartId(rs.getInt(1));
                nCart.setEmailId(rs.getString(2));
                nCart.setFoodId(rs.getInt(3));
                nCart.setFoodName(rs.getString(4));
                nCart.setQuantity(rs.getInt(5));
                nCart.setPrice(rs.getDouble(6));
                nCart.setTotalPrice(rs.getDouble(7));

                carList.add(nCart);
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        return carList;
    }

    private boolean checkFoodInCart(int foodId, String emailId) {

        query = "select cartId from Cart where foodId = ? and emailId = ?";

        ResultSet resultSet = null;
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, foodId);
            ps.setString(2, emailId);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                
                int cid = resultSet.getInt("cartId");
                
                
                return updateCart(cid, 1)   ;
            }

        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        return false;
    }

    @Override
    public boolean clearCart(String emailId) {
        query = "delete from Cart where emailId = ?";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, emailId);
            int i = ps.executeUpdate();

            if (i > 0)
                return true;
        } catch (Exception e) {
            // TODO: handle exception
        }

        return false;
    }
}
