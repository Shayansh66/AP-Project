package test.java.com.example.server.dataAccesses;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import main.java.com.example.server.dataAccesses.UserDAO;
import main.java.com.example.server.models.User;

import java.sql.SQLException;


public class UserDAOTest {

    @Test
    @Before
    public void insertSomeRecords() throws SQLException {
        UserDAO userDAO = new UserDAO();
        userDAO.deleteUsers();
        assertEquals(0, userDAO.getUsers().size());
        
        User tempUserObjec = new User(20000000, "Abcd@gmail.com", "myPass".hashCode(), "Ali", "Ahmadi", null, "Nothing to say. Every thing is allright:)", "Germany", "Berlin", "Looking for new Job");
        userDAO.saveUser(tempUserObjec);
        assertEquals(1, userDAO.getUsers().size());

        tempUserObjec = new User(20000001, "google@aut.ac.ir", "1234".hashCode(), "Barbod", "Ramezani", "Hamedani", "I am learning programming", "USA", "NewYork", "I do srvice for people");
        userDAO.saveUser(tempUserObjec);
        assertEquals(2, userDAO.getUsers().size());
        
        assertNull(userDAO.getUser("-1", "1234".hashCode()));
        assertNull(userDAO.getUserById("2000"));
        assertNull(userDAO.getUser("20000001", "256".hashCode()));
        assertEquals(tempUserObjec.toString(), userDAO.getUser("google@aut.ac.ir", "1234".hashCode()).toString());
        assertEquals(tempUserObjec.toString(), userDAO.getUserById("20000001").toString());
    }
    
    @Test
    public void saveAndReadAUserRecord() throws SQLException {
        User theTestUserObject = new User(20000002, "eidenkhormizi@yahoo.com", "1234".hashCode(), "Add-non", "Heidari", "Khormizi", "CE@AUT", "Tehran", "Iran", "None");

        UserDAO userDAO = new UserDAO();

        userDAO.saveUser(theTestUserObject);
        assertEquals(theTestUserObject.toString(), userDAO.getUserById("20000002").toString());
        
    }

    @Test
    @After
    public void clearUsersTable() throws SQLException {
        UserDAO userDAO = new UserDAO();
        userDAO.deleteUsers();

        assertEquals(0, userDAO.getUsers().size());
    }
    
}
