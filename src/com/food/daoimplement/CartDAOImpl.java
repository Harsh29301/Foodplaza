package com.food.daoimplement;

import java.util.List;

import com.food.dao.CartDAO;
import com.food.pojo.Cart;

public class CartDAOImpl implements CartDAO {

    @Override
    public boolean addToCart(Cart cart) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addToCart'");
    }

    @Override
    public Cart viewCartById(int cartId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewCartById'");
    }

    @Override
    public boolean updateCart(int cartId, int quantity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCart'");
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

}
