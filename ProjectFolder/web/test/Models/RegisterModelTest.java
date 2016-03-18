/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Classes.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Matt
 */
public class RegisterModelTest {
    
    public RegisterModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        try{
            Connector c = new Connector();
            Connection conn = c.getConnection();
            
            PreparedStatement ps = conn.prepareStatement("delete from attendee where AttendanceList_idAttendanceList = ? and Student_idStudent = ?");
            ps.setInt(1, 935757);
            ps.setString(2,"080003474");

            ps.execute();
        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getCause());
        }
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of doRegister method, of class RegisterModel.
     */
    @Test
    public void testValid() {
        
        //This will only work the first time, 
        //all after this the data will already have been added  
        //This will therefore return false
        
        //Set up method has sorted this issue, running each time will remove this id from the database
        
        
        // delete from attendee where AttendanceList_idAttendanceList = '935757' and Student_idStudent = "080003474"
        
        System.out.println("doRegister");
        int bookingID = 147;
        String username = "080003474";
        RegisterModel instance = new RegisterModel();
        boolean expResult = true;
        boolean result = instance.doRegister(bookingID, username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    /**
     * Test of doRegister method, of class RegisterModel.
     */
    @Test
    public void testInvalid() {

        
        System.out.println("doRegister");
        int bookingID = 147;
        String username = "Bob12345";
        RegisterModel instance = new RegisterModel();
        boolean expResult = false;
        boolean result = instance.doRegister(bookingID, username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    /**
     * Test of doRegister method, of class RegisterModel.
     */
    @Test
    public void testAlreadyRegistered() {
                
        //This will get a duplicate entry exception as it has already been entered
        
        System.out.println("doRegister");
        int bookingID = 147;
        String username = "120005432";
        RegisterModel instance = new RegisterModel();
        boolean expResult = false;
        boolean result = instance.doRegister(bookingID, username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
