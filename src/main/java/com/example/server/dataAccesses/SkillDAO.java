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
        PreparedStatement statement = theConnection.prepareStatement("CREATE TABLE IF NOT EXISTS skills (id INT PRIMARY KEY AUTO_INCREMENT, explaination VARCHAR(40), userid INT NOT NULL;)");
        statement.executeUpdate();
        statement = theConnection.prepareStatement("ALTER TABLE skills AUTO_INCREMENT = 49000000;");
        statement.executeUpdate();
    }

    public void saveSkill(String explaination, int userid) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("INSERT INTO skills (explaination, userid) VALUES (?, ?);");
        statement.setString(1, explaination);
        statement.setInt(2, userid);
        statement.executeUpdate();
    }

    public void updateSkill(int id, String explaination, int userid) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("UPDATE skills SET explaination = ?, userid = ? WHERE id = ?;");
        statement.setString(1, explaination);
        statement.setInt(2, userid);
        statement.setInt(3, id);
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

    public ArrayList<String> getSkillsByuserid(int userid) throws SQLException {
        ArrayList<String> list = new ArrayList< >();
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM skills WHERE userid = ?;");
        statement.setInt(1, userid);
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
