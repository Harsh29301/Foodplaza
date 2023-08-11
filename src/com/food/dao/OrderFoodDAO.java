package com.food.dao;

import java.util.List;

import com.food.pojo.OrderFood;

public interface OrderFoodDAO {

    boolean placeOrder(OrderFood order);

    boolean cancelOrder(int orderId);

    OrderFood viewOrderByOrderId(int orderId);

    List<OrderFood> viewOrderDetailsByOrderId(int orderId);

    List<OrderFood> viewMyOrderHistory(String emailId);

    // Admin Operations
    boolean updateOrderStatus(String orderStatus, int orderId);

    List<OrderFood> viewAllOrders();
}
