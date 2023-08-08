package com.food.daoimplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.food.dao.LoginDAO;
import com.food.pojo.Customer;
import com.food.util.DBConnection;

public class LoginDAOImpl implements LoginDAO {
    String query;

    @Override
    public boolean validateCustomer(String emailId, String password) {
        query = "select * from Customer where emailId = ? and  password = ?";

        ResultSet rs = null;
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, emailId);
            ps.setString(2, password);

            Customer customer = null;
            rs = ps.executeQuery();
            while (rs.next()) {
                customer = new Customer();
                customer.setCtr_Name(rs.getString(1));
                customer.setEmailId(rs.getString(2));
                customer.setPassword(rs.getString(3));
                customer.setPhoneno(rs.getString(4));
                customer.setAddress(rs.getString(5));
            }

            if(customer != null){
                return true;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return false;

    }

    @Override
    public boolean validateAdmin(String emailId, String password) {

        query = "select * from Admin where emailId = ? and password = ?";
        ResultSet rs = null;

        try (Connection connection = DBConnection.getConnection();
            PreparedStatement ps =  connection.prepareStatement(query)) {
            
                ps.setString(1, emailId);
                ps.setString(2, password);
                rs = ps.executeQuery();

                if(rs.next()){
                    return true;
                }
                
        } catch (Exception e) {
            // TODO: handle exception
        }
        return false;
    }

}
