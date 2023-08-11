package com.food.daoimplement;

import java.util.List;

import com.food.dao.OrderFoodDAO;
import com.food.pojo.OrderFood;

public class OrderFoodDAOImpl implements OrderFoodDAO {

    @Override
    public boolean placeOrder(OrderFood order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'placeOrder'");
    }

    @Override
    public boolean cancelOrder(int orderId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancelOrder'");
    }

    @Override
    public OrderFood viewOrderDetailsByOrderId(int orderId) {
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
    
}
