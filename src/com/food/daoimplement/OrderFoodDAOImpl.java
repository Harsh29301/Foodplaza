package com.food.daoimplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.food.dao.OrderFoodDAO;
import com.food.pojo.OrderFood;
import com.food.util.DBConnection;

public class OrderFoodDAOImpl implements OrderFoodDAO {

    String query;

    @Override
    public boolean placeOrder(OrderFood order) {

        query = "insert into OrderFood (emailId,orderDate, totalBill, orderStatus) values (?,?,?,?)";

        LocalDateTime orderDate = LocalDateTime.now(); // Currnet Date and Time
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        double totalBill = calculateBill(order.getEmailId());
        ResultSet resultSet = null;
        int orderId = 0;

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

                cartBackUp(orderId, order.getEmailId());
                new CartDAOImpl().clearCart(order.getEmailId());
                return true;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return false;
    }

    private void cartBackUp(int orderId, String emailId) {
    }

    private double calculateBill(String emailId) {
        return 0;
    }

    @Override
    public boolean cancelOrder(int orderId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancelOrder'");
    }

    @Override
    public List<OrderFood> viewOrderDetailsByOrderId(int orderId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewOrderDetailsByOrderId'");
    }

    @Override
    public List<OrderFood> viewMyOrderHistory(String emailId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewMyOrderHistory'");
    }

    @Override
    public boolean updateOrderStatus(String orderStatus, int orderId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateOrderStatus'");
    }

    @Override
    public List<OrderFood> viewAllOrders() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewAllOrders'");
    }

    @Override
    public OrderFood viewOrderByOrderId(int orderId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewOrderByOrderId'");
    }

}
