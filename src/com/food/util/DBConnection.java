package com.food.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    public static Connection getConnection(){

        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project1?user=virus&password=Heyviru$29");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}