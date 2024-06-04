package main.java.com.example.server.dataAccesses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;


public class SkillDAO {
    
    private Connection theConnection;

    public SkillDAO() throws SQLException {
        theConnection = DatabaseConnectionManager.getTheConnection();
        createTable();
    }
    
    public void createTable() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("CREATE TABLE IF NOT EXISTS skills (id INT PRIMARY KEY AUTO_INCREMENT, explaination VARCHAR(40), objectid INT NOT NULL, objecttype VARCHAR(40);)");
        statement.executeUpdate();
        statement = theConnection.prepareStatement("ALTER TABLE skills AUTO_INCREMENT = 49000000;");
        statement.executeUpdate();
    }

    public void saveSkill(String explaination, int objectid, String objecttype) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("INSERT INTO skills (explaination, objectid, objectype) VALUES (?, ?, ?);");
        statement.setString(1, explaination);
        statement.setInt(2, objectid);
        statement.setString(3, objecttype);
        statement.executeUpdate();
    }

    public void updateSkill(String explaination, int objectid, String objecttype, int id) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("UPDATE skills SET explaination = ?, objectid = ?, objectype = ? WHERE id = ?;");
        statement.setString(1, explaination);
        statement.setInt(2, objectid);
        statement.setString(3, objecttype);
        statement.setInt(4, id);
        statement.executeUpdate();
    }

    public void deleteSkill(int id) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM skills WHERE id = ?;");
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public void deleteSkills() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM skills;");
        statement.executeUpdate();
    }

    public String getSkill(int id) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM skills WHERE id = ?;");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getString("explaination");
        }
        return null;
    }

    public ArrayList<String> getSkillsByObjectId(int objectid) throws SQLException {
        ArrayList<String> list = new ArrayList< >();
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM skills WHERE objectid = ?;");
        statement.setInt(1, objectid);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            list.add(resultSet.getString("explaination"));
        }
        return list;
    }

    public ArrayList<String> getSkills() throws SQLException {
        ArrayList<String> list = new ArrayList< >();
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM skills;");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            list.add(resultSet.getString("explaination"));
        }
        return list;
    }
    
}
