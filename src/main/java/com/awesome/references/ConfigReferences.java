package com.awesome.references;

/**
 * Created by sankeerth.reddy on 05/06/15.
 */
public class ConfigReferences {

    public static class DatabaseColumns {
        public static final String id = "id";
        public static final String database = "test";
        public static final String userName = "test";
        public static final String password = "test";
        public static final String jdbc_driver = "com.mysql.jdbc.Driver";
        public static final String dbURL = "jdbc:mysql://localhost/Test";
        public static final String description = "description";
        public static final String imageURL = "image_url";
        public static final String seller = "seller";
        public static final String customer = "customer";
        public static final String endDate = "end_date";
        public static final String createdAt = "created_at";
        public static final String sellerPrice = "seller_price";
        public static final String bidPrice = "bid_price";
        public static final String productId = "product_id";

    }

    public static class SQLStatements {
        public static final String getAllProducts = "select * from Products group by " + DatabaseColumns.productId ;
        public static final String getProduct = "select * from Products where " + DatabaseColumns.productId + " = ";
    }

    public static class GeneralReferences {
        public static final String saveImageFilePath = "/Users/sankeerth.reddy/Desktop";
    }
}
