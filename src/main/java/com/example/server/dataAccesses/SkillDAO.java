package main.java.com.example.server.dataAccesses;

import main.java.com.example.server.models.Skill;

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
        PreparedStatement statement = theConnection.prepareStatement("CREATE TABLE IF NOT EXISTS skills (id INT PRIMARY KEY AUTO_INCREMENT, explaination VARCHAR(40), userid INT NOT NULL);");
        statement.executeUpdate();
        statement = theConnection.prepareStatement("ALTER TABLE skills AUTO_INCREMENT = 49000000;");
        statement.executeUpdate();
    }

    public void saveSkill(Skill skill) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("INSERT INTO skills (explaination, userid) VALUES (?, ?);");
        statement.setString(1, skill.getExplaination());
        statement.setInt(2, skill.getUserid());
        statement.executeUpdate();
    }

    public void updateSkill(Skill skill) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("UPDATE skills SET explaination = ?, userid = ? WHERE id = ?;");
        statement.setString(1, skill.getExplaination());
        statement.setInt(2, skill.getUserid());
        statement.setInt(3, skill.getId());
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

    public Skill getSkill(int id) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM skills WHERE id = ?;");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Skill skill = new Skill();
            skill.setId(resultSet.getInt("id"));
            skill.setExplaination(resultSet.getString("explaination"));
            skill.setUserid(resultSet.getInt("userid"));
            return skill;
        }
        return null;
    }

    public ArrayList<Skill> getSkillsByuserid(int userid) throws SQLException {
        ArrayList<Skill> list = new ArrayList< >();
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM skills WHERE userid = ?;");
        statement.setInt(1, userid);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Skill skill = new Skill();
            skill.setId(resultSet.getInt("id"));
            skill.setExplaination(resultSet.getString("explaination"));
            skill.setUserid(resultSet.getInt("userid"));
            list.add(skill);
        }
        return list;
    }

    public ArrayList<Skill> getSkills() throws SQLException {
        ArrayList<Skill> list = new ArrayList< >();
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM skills;");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Skill skill = new Skill();
            skill.setId(resultSet.getInt("id"));
            skill.setExplaination(resultSet.getString("explaination"));
            skill.setUserid(resultSet.getInt("userid"));
            list.add(skill);
        }
        return list;
    }
    
}
