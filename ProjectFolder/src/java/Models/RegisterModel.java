/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Connection;
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
public class RegisterModel {
    public boolean doRegister(int bookingID, String username) {
		try {
			// TODO: Gain username && password from somewhere
			//username = "000001";
			//password = "turkeyBaconSandwich";
			//ArrayList<String> theStrings = new ArrayList<String>();
                        //String [] theList = new String[4];
			
			
			String theDriver = "com.mysql.jdbc.Driver";
			Class driver_class = Class.forName(theDriver);
			Driver driver = (Driver) driver_class.newInstance();
			DriverManager.registerDriver(driver);
			Connection conn = DriverManager.getConnection("jdbc:mysql://silva.computing.dundee.ac.uk:3306/15agileteam2db?" + "user=15agileteam2&password=349.at2.psswd");
			PreparedStatement ps = conn.prepareStatement("call  sign_in (?,?,?)");
			ps.setString(1, username);
                        ps.setInt(2, bookingID);
			ps.setString(3, "");
                        
                        
                        ResultSet rs = ps.executeQuery();
                        
                        
			//ResultSet rs;
                        //ps.executeUpdate();
                        //int i = ps.getUpdateCount();
                        
                        return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}


	}
}
