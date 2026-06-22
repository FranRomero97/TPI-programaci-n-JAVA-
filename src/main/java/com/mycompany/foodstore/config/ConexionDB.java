package com.mycompany.foodstore.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/foodstore?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD ="3006";
    
    public static Connection getConnection()throws SQLException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");  
        } catch (ClassNotFoundException e){
            System.out.println("Error: No se encontro el driver de MySQL.");
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

