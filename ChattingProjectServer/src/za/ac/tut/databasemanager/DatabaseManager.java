/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.databasemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

/**
 *
 * @author bafedile
 */
public class DatabaseManager {
    private Connection connection;
    
    public DatabaseManager(String url,String username,String password)throws SQLException{
        connection = DriverManager.getConnection(url,username,password);
    }
    
    
    public ResultSet getData(String query)throws SQLException{
        PreparedStatement ptmt = connection.prepareStatement(query);
        ResultSet results = ptmt.executeQuery();
        return results;
    }
    
    public void addUser(String query)throws SQLException{
        PreparedStatement ptmt = connection.prepareStatement(query);
        ptmt.executeUpdate();
    }
    
    
}