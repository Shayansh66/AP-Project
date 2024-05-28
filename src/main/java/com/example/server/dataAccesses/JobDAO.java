package main.java.com.example.server.dataAccesses;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JobDAO {
    
    private Connection theConnection;
    
    public JobDAO() throws SQLException {
        theConnection = DatabaseConnectionManager.getTheConnection();
        
    }

    public void creatTable() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("CREATE TABLE IF NOT EXISTS jobs (id INT PRIMARY KEY AUTO_INCREMENT, userid INT NOT NULL, title VARCHAR(40), companyname VARCHAR(40), workplace VARCHAR(40), isworking BOOLEAN, worktype VARCHAR(40), jobtype VARCHAR(40), startdate TIMESTAMP, enddate TIMESTAMP, description VARCHAR(1000), notifychanges BOOLEAN, CONSTRIANT fk_userid FORIEGN KEY (userid) REFRENCES user(id));");
        statement.executeUpdate();
        statement = theConnection.prepareStatement("ALTER TALBE jobs AUTO_INCREMENT = 40000000;");
        statement.executeUpdate();
    }
    
}
