package com.awesome.internal.impl;

import com.awesome.database.DbConnection;
import com.awesome.references.ConfigReferences;
import com.awesome.structures.Product;
import com.mysql.jdbc.Connection;
import com.sun.rowset.CachedRowSetImpl;

import javax.sql.rowset.CachedRowSet;
import java.io.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by sankeerth.reddy on 05/06/15.
 */
public class ProductImpl {
    private static final String userName = ConfigReferences.DatabaseColumns.userName;
    private static final String password = ConfigReferences.DatabaseColumns.password;
    private static final String url = ConfigReferences.DatabaseColumns.dbURL;
    static DbConnection dbConnection = new DbConnection(userName, password, url);

    public ProductImpl() {
    }

    ;

    private CachedRowSet executeQuery(String sql) {
        Connection connnection = dbConnection.establishConnection();
        CachedRowSet crs = null;
        try {
            crs = new CachedRowSetImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = null;
        try {
            resultSet = dbConnection.executeQuery(connnection, sql);

            crs.populate(resultSet);
            dbConnection.closeConnection(connnection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return crs;
    }

    private void executeUpdate(String sql) {
        Connection connnection = dbConnection.establishConnection();
        try {
            dbConnection.executeUpdate(connnection, sql);
            dbConnection.closeConnection(connnection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public ArrayList<Product> getAllProducts() {
        String getProductsSQL = ConfigReferences.SQLStatements.getAllProducts;
        ArrayList<Product> allProducts = new ArrayList<Product>();
        CachedRowSet resultSet = executeQuery(getProductsSQL);
        try {
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getInt(ConfigReferences.DatabaseColumns.productId));
                product.setDescription(resultSet.getString(ConfigReferences.DatabaseColumns.description));
                product.setImageURL(resultSet.getString(ConfigReferences.DatabaseColumns.imageURL));
                product.setSeller(resultSet.getString(ConfigReferences.DatabaseColumns.seller));
                product.setBidPrice(resultSet.getInt(ConfigReferences.DatabaseColumns.bidPrice));
//                product.setCustomer(resultSet.getString(ConfigReferences.DatabaseColumns.customer));
                product.setEndDate(resultSet.getDate(ConfigReferences.DatabaseColumns.endDate));
                product.setCreatedAt(resultSet.getDate(ConfigReferences.DatabaseColumns.createdAt));
                product.setSellerPrice(resultSet.getInt(ConfigReferences.DatabaseColumns.sellerPrice));
                allProducts.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allProducts;
    }

    public Product getProduct(String id) {
        Product product = new Product();
        String getProduct = ConfigReferences.SQLStatements.getProduct + id + " " + "group by " + ConfigReferences.DatabaseColumns.productId;
        CachedRowSet resultSet = executeQuery(getProduct);
        if (resultSet.size() == 1) {
            try {
                while (resultSet.next()) {
                    product.setProductId(resultSet.getInt(ConfigReferences.DatabaseColumns.productId));
                    product.setDescription(resultSet.getString(ConfigReferences.DatabaseColumns.description));
                    product.setImageURL(resultSet.getString(ConfigReferences.DatabaseColumns.imageURL));
                    product.setSeller(resultSet.getString(ConfigReferences.DatabaseColumns.seller));
                    product.setBidPrice(resultSet.getInt(ConfigReferences.DatabaseColumns.bidPrice));
//                product.setCustomer(resultSet.getString(ConfigReferences.DatabaseColumns.customer));
                    product.setEndDate(resultSet.getDate(ConfigReferences.DatabaseColumns.endDate));
                    product.setCreatedAt(resultSet.getDate(ConfigReferences.DatabaseColumns.createdAt));
                    product.setSellerPrice(resultSet.getInt(ConfigReferences.DatabaseColumns.sellerPrice));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return product;
    }

    private boolean ifCustomerProductSellerEntryExists(Product product) {
        int bidPrice = product.getBidPrice();
        Date created_at = product.getCreatedAt();
        String customer = product.getCustomer();
        String description = product.getDescription();
        Date endDate = product.getEndDate();
        String imageURL = product.getImageURL();
        int productId = product.getProductId();
        String seller = product.getSeller();
        int sellerPrice = product.getSellerPrice();

        String getProduct = ConfigReferences.SQLStatements.getProduct + productId + " && " + ConfigReferences.DatabaseColumns.seller + " = '" + seller + "' && " + ConfigReferences.DatabaseColumns.customer + " = '" + customer + "'";
        System.out.println("GetProduct sql : " + getProduct);
        CachedRowSet resultSet = executeQuery(getProduct);
        if (resultSet.size() == 0) {
            return false;
        }
        return true;
    }

    public void insertProduct(Product product) {
        if (!ifCustomerProductSellerEntryExists(product)) {
            insertNewRow(product);
        } else {
            updateRow(product);
        }
    }


    private void insertNewRow(Product product) {
        String insertQuery = "INSERT INTO Products (" + ConfigReferences.DatabaseColumns.productId + "," +
                ConfigReferences.DatabaseColumns.seller + "," + ConfigReferences.DatabaseColumns.customer + "," +
                ConfigReferences.DatabaseColumns.bidPrice + "," + ConfigReferences.DatabaseColumns.createdAt + ","+
                ConfigReferences.DatabaseColumns.description + "," + ConfigReferences.DatabaseColumns.endDate + "," +
                ConfigReferences.DatabaseColumns.imageURL + "," + ConfigReferences.DatabaseColumns.sellerPrice + ") " +
                "VALUES ('" + product.getProductId() + "','" + product.getSeller() + "','" + product.getCustomer() + "','"
                + product.getBidPrice() + "','" + product.getCreatedAt() + "','" + product.getDescription() + "','" + product.getEndDate() + "','" +
                product.getImageURL() + "','" + product.getSellerPrice() + "')";
        System.out.println("Insert Query : " + insertQuery);
        executeUpdate(insertQuery);

    }

    private void updateRow(Product product) {
        String updateQuery = "UPDATE Products SET " + ConfigReferences.DatabaseColumns.bidPrice + " = " +
                product.getBidPrice() + " where " + ConfigReferences.DatabaseColumns.productId + " = " +
                product.getProductId() + " && " + ConfigReferences.DatabaseColumns.customer + " = '" +
                product.getCustomer() + "' && " + ConfigReferences.DatabaseColumns.seller + " = '" + product.getSeller() + "'";
        System.out.println("UpdateQuery : " + updateQuery);
        executeUpdate(updateQuery);



    }

    public Product getMaxBid(String id) {
        Product product = new Product();
        String getProduct = ConfigReferences.SQLStatements.getProduct + id + " order by " + ConfigReferences.DatabaseColumns.bidPrice + " DESC limit 1";
        CachedRowSet resultSet = executeQuery(getProduct);
        System.out.println("Query : " + getProduct);
        try {
                while (resultSet.next()) {
                    product.setProductId(resultSet.getInt(ConfigReferences.DatabaseColumns.productId));
                    product.setDescription(resultSet.getString(ConfigReferences.DatabaseColumns.description));
                    product.setImageURL(resultSet.getString(ConfigReferences.DatabaseColumns.imageURL));
                    product.setSeller(resultSet.getString(ConfigReferences.DatabaseColumns.seller));
                    product.setBidPrice(resultSet.getInt(ConfigReferences.DatabaseColumns.bidPrice));
                    product.setCustomer(resultSet.getString(ConfigReferences.DatabaseColumns.customer));
                    product.setEndDate(resultSet.getDate(ConfigReferences.DatabaseColumns.endDate));
                    product.setCreatedAt(resultSet.getDate(ConfigReferences.DatabaseColumns.createdAt));
                    product.setSellerPrice(resultSet.getInt(ConfigReferences.DatabaseColumns.sellerPrice));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        return product;
    }

    public static String getRandomNumber(int digits) {
        int numDigits = digits;
        if(digits < 1) {
            numDigits = Integer.parseInt(getRandomNumber(1));
        }

        String num = String.valueOf(Math.abs((new Random()).nextLong()));

        try {
            return num.substring(0, numDigits);
        } catch (StringIndexOutOfBoundsException var4) {
            num = num + String.valueOf(Math.abs((new Random()).nextLong()));
            return num.substring(0, numDigits);
        }
    }

    public void saveFile(InputStream uploadedInputStream,
                         String serverLocation) {

        try {
            OutputStream outpuStream = new FileOutputStream(new File(serverLocation));
            int read = 0;
            byte[] bytes = new byte[1024];

            outpuStream = new FileOutputStream(new File(serverLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                outpuStream.write(bytes, 0, read);
            }
            outpuStream.flush();
            outpuStream.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }
}
