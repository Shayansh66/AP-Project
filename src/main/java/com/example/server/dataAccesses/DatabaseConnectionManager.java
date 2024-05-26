package main.java.com.example.server.dataAccesses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnectionManager {

    private static Connection theConnection;

    private static String url = "jdbc:mysql://Localhost:3306/APproject";
    private static String user = "root";
    private static String password = "3Dkl$MZ3b";
    

    public static Connection getTheConnection() throws SQLException {
        if (theConnection == null || theConnection.isClosed()) {
            theConnection = DriverManager.getConnection(url, user, password);
        }
        return theConnection;
    }
    
}
