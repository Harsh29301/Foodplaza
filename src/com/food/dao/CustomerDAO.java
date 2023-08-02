package com.food.dao;

import com.food.pojo.Customer;

public interface CustomerDAO {
 
    boolean addCustomer(Customer customer);

    Customer viewCustomerByEmailid(String emailId);
}
