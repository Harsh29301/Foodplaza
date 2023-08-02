package com.food.dao;

import java.util.List;

import com.food.pojo.Customer;

public interface CustomerDAO {
 
    boolean addCustomer(Customer customer);

    Customer viewCustomerByEmailid(String emailId);

    boolean updateCustomer(Customer customer);

    boolean deleteCustomer(String emailId);

    List<Customer> viewAllCustomers();
}
