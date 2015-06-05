package com.awesome.database;

import com.awesome.references.ConfigReferences;
import com.sun.rowset.CachedRowSetImpl;

import javax.sql.rowset.CachedRowSet;
import java.sql.*;

/**
 * Created by sankeerth.reddy on 04/06/15.
 */
public class DatabaseHelper {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = ConfigReferences.DatabaseColumns.jdbc_driver;
    static final String DB_URL = ConfigReferences.DatabaseColumns.dbURL;

    //  Database credentials
    static final String USER = ConfigReferences.DatabaseColumns.userName;
    static final String PASS = ConfigReferences.DatabaseColumns.password;

    public DatabaseHelper() {
    }

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, firstname, secondname FROM Test";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                String firstname = rs.getString("firstname");
                String secondname = rs.getString("secondname");

                //Display values
                System.out.print("id: " + id);
                System.out.print(", firstname: " + firstname);
                System.out.println(", secondname: " + secondname);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main

    public CachedRowSet executeQuery(String sqlQuery) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        CachedRowSet crs = new CachedRowSetImpl();
        try {

            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            crs.populate(rs);
            rs.close();
            stmt.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return crs;
    }
}
