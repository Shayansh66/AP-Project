package main.java.com.example.server.dataAccesses;

import main.java.com.example.server.models.Job;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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

    public void saveJob(Job job) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("INSERT INTO jobs (userid, title, companyname, workplace, isworking, worktype, jobtype, startdate, enddate, description, notifychanges) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
        statement.setInt(1, job.getUserid());
        statement.setString(2, job.getTitle());
        statement.setString(3, job.getCompanyName());
        statement.setString(4, job.getWorkplace());
        statement.setBoolean(5, job.isIsworking());
        statement.setString(6, job.getWorkType());
        statement.setString(7, job.getJobType());
        statement.setTimestamp(8, job.getStartDate());
        statement.setTimestamp(9, job.getEnddate());
        statement.setString(10, job.getDescription());
        statement.setBoolean(11, job.isNotifyChanges());
        statement.executeUpdate();
    }

    public void updateJob(Job job) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("UPDATE jobs SET userid = ?, title = ?, companyname = ?, workplace = ?, isworking = ?, jobtype = ?, startdate = ?, enddate = ?, description = ?, notifychanges = ? WHERE id = ?;");
        statement.setInt(1, job.getUserid());
        statement.setString(2, job.getTitle());
        statement.setString(3, job.getCompanyName());
        statement.setString(4, job.getWorkplace());
        statement.setBoolean(5, job.isIsworking());
        statement.setString(6, job.getWorkType());
        statement.setString(7, job.getJobType());
        statement.setTimestamp(8, job.getStartDate());
        statement.setTimestamp(9, job.getEnddate());
        statement.setString(10, job.getDescription());
        statement.setBoolean(11, job.isNotifyChanges());
        statement.setInt(12, job.getId());
        statement.executeUpdate();
    }

    public void deleteJob(Job job) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM jobs WHERE id = ?");
        statement.setInt(1, job.getId());
        statement.executeUpdate();
    }

    public void deleteJob(String id) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM jobs WHERE id = ?");
        statement.setInt(1, Integer.parseInt(id));
        statement.executeUpdate();
    }

    public void deleteJobs() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM jobs");
        statement.executeUpdate();
    }

    public Job getJob(String id) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM jobs WHERE id = ?");
        statement.setInt(1, Integer.parseInt(id));
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Job job = new Job();
            job.setId(resultSet.getInt("id"));
            job.setUserid(resultSet.getInt("userid"));
            job.setTitle(resultSet.getString("title"));
            job.setCompanyName(resultSet.getString("companyname"));
            job.setWorkplace(resultSet.getString("workplace"));
            job.setIsworking(resultSet.getBoolean("isworking"));
            job.setWorkType(resultSet.getString("worktype"));
            job.setJobType(resultSet.getString("jobtype"));
            job.setStartDate(resultSet.getTimestamp("startdate"));
            job.setEnddate(resultSet.getTimestamp("enddate"));
            job.setDescription(resultSet.getString("description"));
            job.setNotifyChanges(resultSet.getBoolean("notifychanges"));
            return job;
        }

        return null;
    }

    public ArrayList<Job> getUserJobs(String userId) throws SQLException {
        ArrayList<Job> list = new ArrayList< >();
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM jobs WHERE userid = ?");
        statement.setInt(1, Integer.parseInt(userId));
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Job job = new Job();
            job.setId(resultSet.getInt("id"));
            job.setUserid(resultSet.getInt("userid"));
            job.setTitle(resultSet.getString("title"));
            job.setCompanyName(resultSet.getString("companyname"));
            job.setWorkplace(resultSet.getString("workplace"));
            job.setIsworking(resultSet.getBoolean("isworking"));
            job.setWorkType(resultSet.getString("worktype"));
            job.setJobType(resultSet.getString("jobtype"));
            job.setStartDate(resultSet.getTimestamp("startdate"));
            job.setEnddate(resultSet.getTimestamp("enddate"));
            job.setDescription(resultSet.getString("description"));
            job.setNotifyChanges(resultSet.getBoolean("notifychanges"));
            list.add(job);
        }
        
        return list;
    }
    
    public ArrayList<Job> getJobs() throws SQLException {
        ArrayList<Job> list = new ArrayList< >();
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM jobs");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Job job = new Job();
            job.setId(resultSet.getInt("id"));
            job.setUserid(resultSet.getInt("userid"));
            job.setTitle(resultSet.getString("title"));
            job.setCompanyName(resultSet.getString("companyname"));
            job.setWorkplace(resultSet.getString("workplace"));
            job.setIsworking(resultSet.getBoolean("isworking"));
            job.setWorkType(resultSet.getString("worktype"));
            job.setJobType(resultSet.getString("jobtype"));
            job.setStartDate(resultSet.getTimestamp("startdate"));
            job.setEnddate(resultSet.getTimestamp("enddate"));
            job.setDescription(resultSet.getString("description"));
            job.setNotifyChanges(resultSet.getBoolean("notifychanges"));
            list.add(job);
        }
        
        return list;
    }
    
}
