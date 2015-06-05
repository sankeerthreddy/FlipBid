package com.awesome.database;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author rohitj
 *
 */
public class DbConnection {
    private String username;
    private String password;
    private String url;
    private String className;
    public DbConnection(String username, String password, String url){
        this.username = username;
        this.password = password;
        this.url = url;
        this.className = "com.mysql.jdbc.Driver";
    }

    public  Connection establishConnection(){

        Connection conn = null;
        try
        {
            Class.forName(className);
            System.out.println("Connecting to database...");
            System.out.println("Username is " + username);
            System.out.println("Password is " + password);
            conn = (Connection) DriverManager.getConnection(url, username, password);
            System.out.println ("Database connection established");
        }
        catch (Exception e)
        {
            System.out.println (e.toString());
            System.err.println ("Cannot connect to database server");
        }

        return conn;
    }

    public ResultSet executeQuery(Connection connection, String query) throws SQLException{
        Statement stmt = null;
        ResultSet rs = null;
        stmt = (Statement) connection.createStatement();
        rs = (ResultSet) stmt.executeQuery(query);
        return rs;
    }



    public void closeConnection (Connection connection) throws SQLException {
        if (connection != null)
        {
            connection.close ();
            System.out.println ("Database connection terminated");

        }
    }

    public void executeUpdate(Connection connection, String query) throws SQLException{
        PreparedStatement ptmt = null;
        String queryString  = query;
        ptmt = connection.prepareStatement(queryString);
        ptmt.executeUpdate();
        System.out.println("Table is updated using prepared statement.");
    }

}



