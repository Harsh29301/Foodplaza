package com.food.daoimplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.food.dao.CustomerDAO;
import com.food.pojo.Customer;
import com.food.util.DBConnection;

public class CustomerDAOImpl implements CustomerDAO {
    String query;

    // Add Customer Implementation
    @Override
    public boolean addCustomer(Customer customer) {

        query = "insert into Customer (ctr_Name, emailId, password, phoneno, address) values (?,?,?,?,?)";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, customer.getCtr_Name());
            ps.setString(2, customer.getEmailId());
            ps.setString(3, customer.getPassword());
            ps.setString(4, customer.getPhoneno());
            ps.setString(5, customer.getAddress());

            int i = ps.executeUpdate();

            if (i > 0) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

    // View Customer by Email Id Method Implementation.
    @Override
    public Customer viewCustomerByEmailid(String emailId) {

        query = "select ctr_Name, emailId, password, phoneno, address from Customer where emailId=?";
        ResultSet resultSet = null;
        Customer customer = null;
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, emailId);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                customer = new Customer();
                customer.setCtr_Name(resultSet.getString(1));
                customer.setEmailId(resultSet.getString(2));
                customer.setPassword(resultSet.getString(3));
                customer.setPhoneno(resultSet.getString(4));
                customer.setAddress(resultSet.getString(5));
            }

        } catch (Exception e) {

        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                }
            }
        }

        return customer;
    }

}
