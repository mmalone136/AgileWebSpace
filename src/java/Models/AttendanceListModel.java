/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Connection;
import java.util.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Matt
 */
public class AttendanceListModel {
    
    // Perform login
	public int createList() {
            try {
                // TODO: Gain username && password from somewhere
                //username = "000001";
                //password = "turkeyBaconSandwich";
                //ArrayList<String> theStrings = new ArrayList<String>();
                // [] theList = new String[4];


                String theDriver = "com.mysql.jdbc.Driver";
                Class driver_class = Class.forName(theDriver);
                Driver driver = (Driver) driver_class.newInstance();
                DriverManager.registerDriver(driver);
                Connection conn = DriverManager.getConnection("jdbc:mysql://silva.computing.dundee.ac.uk:3306/15agileteam2db?" + "user=15agileteam2&password=349.at2.psswd");
                //PreparedStatement ps = conn.prepareStatement("Select * from user where username=? and password=?");

                PreparedStatement ps;

                ps = conn.prepareStatement("insert into attendancelist () VALUES ()", Statement.RETURN_GENERATED_KEYS);

                ps.executeUpdate();
                
                ResultSet rs = ps.getGeneratedKeys();
                
                int generatedKey = 0;
                rs.next();
                    
                generatedKey = rs.getInt(1);
                

                //ps.executeUpdate();

                return generatedKey;


            } catch (Exception e) {
                    e.printStackTrace();
                    return -1;
                    // null;
            }
        
        }









}
