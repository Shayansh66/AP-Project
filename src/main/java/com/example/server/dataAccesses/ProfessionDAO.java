package main.java.com.example.server.dataAccesses;

import main.java.com.example.server.models.Profession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProfessionDAO {
    
    private Connection theConnection;

    public ProfessionDAO() throws SQLException {
        theConnection = DatabaseConnectionManager.getTheConnection();
        createTable();
    }

    public void createTable() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("CREATE TABLE IF NOT EXISTS professions (id INT PRIMARY KEY AUTO_INCREMENT, userid INT NOT NULL, text VARCHAR(60));");
        statement.executeUpdate();
        statement = theConnection.prepareStatement("ALTER TABLE professions AUTO_INCREMENT = 44000000;");
        statement.executeUpdate();
    }

    public void saveProfession(Profession profession) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("INSERT INTO professions (userid, text) VALUES (?, ?);");
        statement.setInt(1, profession.getUserId());
        statement.setString(2, profession.getText());
        statement.executeUpdate();
    }

    public void updateProfession(Profession profession) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("UPDATE professions SET userid = ?, text = ?");
        statement.setInt(1, profession.getUserId());
        statement.setString(2, profession.getText());
        statement.executeUpdate();
    }

    public void deleteProfession(int id) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM professions WHERE id = ?;");
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public void deleteProfession(Profession profession) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM professions WHERE id = ?;");
        statement.setInt(1, profession.getId());
        statement.executeUpdate();
    }

    public void deleteProfessions() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM professions");
        statement.executeUpdate();
    }

    public Profession getProfession(int id) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM professions WHERE id = ?;");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Profession profession = new Profession();
            profession.setId(resultSet.getInt("id"));
            profession.setUserId(resultSet.getInt("userid"));
            profession.setText(resultSet.getString("text"));
            return profession;
        }

        return null;
    }

    public ArrayList<Profession> getProfessionsByUserId(int userid) throws SQLException {
        ArrayList<Profession> list = new ArrayList< >();
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM professions WHERE userid = ?;");
        statement.setInt(1, userid);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Profession profession = new Profession();
            profession.setId(resultSet.getInt("id"));
            profession.setUserId(resultSet.getInt("userid"));
            profession.setText(resultSet.getString("text"));
            list.add(profession);
        }

        return list;
    }

    public ArrayList<Profession> getProfessions() throws SQLException {
        ArrayList<Profession> list = new ArrayList< >();
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM professions;");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Profession profession = new Profession();
            profession.setId(resultSet.getInt("id"));
            profession.setUserId(resultSet.getInt("userid"));
            profession.setText(resultSet.getString("text"));
            list.add(profession);
        }

        return list;
    }
    
}
