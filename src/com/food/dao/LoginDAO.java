package com.food.dao;

import com.food.pojo.Customer;

public interface LoginDAO {
    
    public boolean validateCustomer(String emailId, String password);
    public boolean validateAdmin(String emailId, String password);
}
