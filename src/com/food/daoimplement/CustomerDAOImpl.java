package com.food.daoimplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @Override // Customer Update Method
    public boolean updateCustomer(Customer customer) {

        query = "update Customer set ctr_Name=?, password=?, phoneno=?, address=? where emailId =?";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, customer.getCtr_Name());
            ps.setString(2, customer.getPassword());
            ps.setString(3, customer.getPhoneno());
            ps.setString(4, customer.getAddress());
            ps.setString(5, customer.getEmailId());

            int i = ps.executeUpdate();

            if (i > 0) {
                return true;
            }

        } catch (Exception e) {
            // e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteCustomer(String emailId) {

        query = "delete from Customer where emailId =?";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, emailId);

            int i = ps.executeUpdate();
            if (i > 0) {
                return true;
            }
        } catch (Exception e) {

        }

        return false;
    }

    @Override
    public List<Customer> viewAllCustomers() {

        List<Customer> customerList = new ArrayList<>();
        query = "select ctr_Name, emailId, password, phoneno, address from Customer";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet resultSet = ps.executeQuery()) {
            // From DB to POJO and add it into list.

            Customer nCustomer = new Customer();
            while (resultSet.next()) {
            nCustomer.setCtr_Name(resultSet.getString(1));
            nCustomer.setEmailId(resultSet.getString(2));
            nCustomer.setPassword(resultSet.getString(3));
            nCustomer.setPhoneno(resultSet.getString(4));
            nCustomer.setAddress(resultSet.getString(5));

            customerList.add (nCustomer);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return customerList;
    }

}
