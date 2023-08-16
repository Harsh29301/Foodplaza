package com.food.daoimplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.food.dao.OrderFoodDAO;
import com.food.pojo.Cart;
import com.food.pojo.OrderFood;
import com.food.util.DBConnection;

public class OrderFoodDAOImpl implements OrderFoodDAO {

    String query;

    @Override
    public boolean placeOrder(OrderFood order) {

        LocalDateTime orderDate = LocalDateTime.now(); // Currnet Date and Time
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        double totalBill = calculateBill(order.getEmailId());
        ResultSet resultSet = null;
        int orderId = 0;

        query = "insert into OrderFood (emailId,orderDate, totalBill, orderStatus) values (?,?,?,?)";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, order.getEmailId());
            ps.setTimestamp(2, Timestamp.valueOf(orderDate.format(format)));
            ps.setDouble(3, totalBill);
            ps.setString(4, order.getOrderStatus());

            int i = ps.executeUpdate();

            if (i > 0) {
                resultSet = ps.getGeneratedKeys();
                if (resultSet.next()) {
                    orderId = resultSet.getInt(1);
                }

                boolean backup = cartBackUp(orderId, order.getEmailId());
                if (backup) {
                    new CartDAOImpl().clearCart(order.getEmailId());
                    return true;
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return false;
    }

    public boolean cartBackUp(int orderId, String emailId) {

        query = "insert into OrderDetials (orderId, cartId, emailId, foodId, foodName, quantity, price, totalPrice) values (?,?,?,?,?,?,?,?)";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            List<Cart> myCart = new CartDAOImpl().viewCartByEmailId(emailId);

            for (Cart cart : myCart) {

                ps.setInt(1, orderId);
                ps.setInt(2, cart.getCartId());
                ps.setString(3, emailId);
                ps.setInt(4, cart.getFoodId());
                ps.setString(5, cart.getFoodName());
                ps.setInt(6, cart.getQuantity());
                ps.setDouble(7, cart.getPrice());
                ps.setDouble(8, cart.getTotalPrice());

                ps.addBatch();

            }

             int i[] = ps.executeBatch();
            if(i != null){
                return true;
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

        return false;
    }

    private double calculateBill(String emailId) {

        query = " select totalPrice from Cart where emailId = ?";

        double totalBill = 0;
        ResultSet rs = null;
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, emailId);

            rs = ps.executeQuery();

            while (rs.next()) {
                totalBill += rs.getDouble(1);
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        return totalBill;
    }

    @Override
    public boolean cancelOrder(int orderId) {
        query = "update OrderFood set orderStatus=? where orderId = ?";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, "Order Canceled");
            ps.setInt(2, orderId);

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
    public List<OrderFood> viewOrderDetailsByOrderId(int orderId) {

        query = " select * from OrderFood where orderId = ?";
        List<OrderFood> myOrders = new ArrayList<>(null);
        ResultSet rs = null;

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, orderId);
            rs = ps.executeQuery();

            while (rs.next()) {
                OrderFood nOrderFood = new OrderFood();
                nOrderFood.setOrderId(rs.getInt(1));
                nOrderFood.setEmailId(rs.getString(2));
                nOrderFood.setOrderdate(rs.getTimestamp(3));
                nOrderFood.setTotalBill(rs.getDouble(4));
                nOrderFood.setOrderStatus(rs.getString(5));

                myOrders.add(nOrderFood);
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return myOrders;

    }

    @Override
    public List<OrderFood> viewMyOrderHistory(String emailId) {
        query = " select * from OrderFood where emailId = ?";
        List<OrderFood> myOrders = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, emailId);
            rs = ps.executeQuery();

            while (rs.next()) {
                OrderFood nOrderFood = new OrderFood();
                nOrderFood.setOrderId(rs.getInt(1));
                nOrderFood.setEmailId(rs.getString(2));
                nOrderFood.setOrderdate(rs.getTimestamp(3));
                nOrderFood.setTotalBill(rs.getDouble(4));
                nOrderFood.setOrderStatus(rs.getString(5));

                myOrders.add(nOrderFood);
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return myOrders;

    }

    @Override
    public boolean updateOrderStatus(String orderStatus, int orderId) {
        query = "update OrderFood set orderStatus=? where orderId = ?";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, orderStatus);
            ps.setInt(2, orderId);

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
    public List<OrderFood> viewAllOrders() {
        query = " select * from OrderFood";
        List<OrderFood> allOrders = new ArrayList<>(null);
        ResultSet rs = null;

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

            rs = ps.executeQuery();

            while (rs.next()) {
                OrderFood nOrderFood = new OrderFood();
                nOrderFood.setOrderId(rs.getInt(1));
                nOrderFood.setEmailId(rs.getString(2));
                nOrderFood.setOrderdate(rs.getTimestamp(3));
                nOrderFood.setTotalBill(rs.getDouble(4));
                nOrderFood.setOrderStatus(rs.getString(5));

                allOrders.add(nOrderFood);
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return allOrders;
    }

    @Override
    public List<OrderFood> viewOrderByOrderId(int orderId) {
        query = " select * from OrderDetials where orderId = ?";
        List<OrderFood> myOrders = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, orderId);
            rs = ps.executeQuery();

            while (rs.next()) {
                OrderFood nOrderFood = new OrderFood();
                nOrderFood.setOrderId(orderId);
                nOrderFood.setCartId(rs.getInt(2));
                nOrderFood.setEmailId(rs.getString(3));
                nOrderFood.setFoodId(rs.getInt(4));
                nOrderFood.setFoodName(rs.getString(5));
                nOrderFood.setQuantity(rs.getInt(6));
                nOrderFood.setPrice(rs.getDouble(7));
                nOrderFood.setTotalPrice(rs.getDouble(8));

                myOrders.add(nOrderFood);
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return myOrders;
    }

}
