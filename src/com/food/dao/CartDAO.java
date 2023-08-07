package com.food.dao;

import java.util.List;

import com.food.pojo.Cart;

public interface CartDAO {
    boolean addToCart(Cart cart);

    Cart viewCartById(int cartId);
    boolean updateCart(int cartId, int quantity);
    boolean deleteCartById(int cartId);
    boolean deleteCartByEmailId(String emailId);
    List<Cart> viewCartByEmailId(String email);
    List<Cart> viewCart();


}
