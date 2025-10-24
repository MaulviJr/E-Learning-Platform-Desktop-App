/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecms.dbms.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 *


/**
 *
 * @author abdul
 */
public class DatabaseConnection {
     private static final String URL = "jdbc:mysql://localhost:3306/ecms"; // your DB name
    private static final String USER = "root"; // your MySQL username
    private static final String PASSWORD = "admin123"; // the password you set in configurator

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to MySQL database!");
            return conn;
        } catch (SQLException e) {
            System.out.println("❌ Database connection failed!");
//            e.printStackTrace();
            return null;
        }
    }
}
