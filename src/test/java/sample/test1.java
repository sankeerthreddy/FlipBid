package sample;

import com.awesome.database.DbConnection;
import com.awesome.references.ConfigReferences;
import com.mysql.jdbc.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sankeerth.reddy on 05/06/15.
 */
public class test1 {

    public static void main(String[] args) throws SQLException {
        String userName = ConfigReferences.DatabaseColumns.userName;
        String password = ConfigReferences.DatabaseColumns.password;
        String dbURL = ConfigReferences.DatabaseColumns.dbURL;
        String query = "SELECT id FROM Test where id = 1";
        DbConnection dbConnection = new DbConnection(userName, password, dbURL);
        Connection connection = dbConnection.establishConnection();
        ResultSet result = dbConnection.executeQuery(connection, query);
        while (result.next()) {
            System.out.println(result.getString("id"));
        }
        dbConnection.closeConnection(connection);
    }
}
