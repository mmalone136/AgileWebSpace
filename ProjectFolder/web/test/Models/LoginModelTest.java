/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

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
public class LoginModelTest {
    
    public LoginModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of doLogin method, of class LoginModel.
     */
    @Test
    public void testDoLogin() {
        System.out.println("doLogin");
        String username = "AECobley";
        String password = "Cassandraisbae";
        LoginModel instance = new LoginModel();
        String[] expResult = null;
        String[] result = instance.generalLogin(username, password);
        LoginModel loginModel = new LoginModel();
        
        assertNotNull("= Pass",loginModel.generalLogin(username,password));
        // TODO review the generated test code and remove the default call to fail.
        //fail("Login Failed");
    }
    
    
    /**
     * Test of doLogin method, of class LoginModel.
     */
    @Test
    public void testLogin() {
        System.out.println("doLogin");
        String username = "AECobley";
        String password = "Cassandra";
        LoginModel instance = new LoginModel();
        String[] expResult = null;
        String[] result = instance.generalLogin(username, password);
        LoginModel loginModel = new LoginModel();
        assertNull("= fail",loginModel.generalLogin(username,password));
        // TODO review the generated test code and remove the default call to fail.
        //fail("Login Succeeded with wrong details");
    }
    
    
}
