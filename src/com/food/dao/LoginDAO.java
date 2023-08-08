package com.food.dao;


public interface LoginDAO {
    
    public boolean validateCustomer(String emailId, String password);
    public boolean validateAdmin(String emailId, String password);
}
