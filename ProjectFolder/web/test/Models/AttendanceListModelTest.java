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
public class AttendanceListModelTest {
    
    public AttendanceListModelTest() {
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
     * Test of createList method, of class AttendanceListModel.
     */
    @Test
    public void testCreateList() {
        System.out.println("createList");
        AttendanceListModel instance = new AttendanceListModel();
        int expResult = -1;
        int result = instance.createList();
        
        assertNotSame("=Pass", expResult,instance.createList());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
