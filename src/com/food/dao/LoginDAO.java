package com.food.dao;

import com.food.pojo.Customer;

public interface LoginDAO {
    
    public boolean signUp(Customer customer);

    public boolean signIn();
}
