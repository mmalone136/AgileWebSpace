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
import java.util.ArrayList;

/**
 *
 * @author Matt
 */
public class LoginModel {
    
	// Perform login
	public String[] doLogin(String username, String password) {
		try {
			// TODO: Gain username && password from somewhere
			//username = "000001";
			//password = "turkeyBaconSandwich";
			ArrayList<String> theStrings = new ArrayList<String>();
                        String [] theList = new String[4];
			
			
			String theDriver = "com.mysql.jdbc.Driver";
			Class driver_class = Class.forName(theDriver);
			Driver driver = (Driver) driver_class.newInstance();
			DriverManager.registerDriver(driver);
			Connection conn = DriverManager.getConnection("jdbc:mysql://silva.computing.dundee.ac.uk:3306/15agileteam2db?" + "user=15agileteam2&password=349.at2.psswd");
			PreparedStatement ps = conn.prepareStatement("Select * from user where username=? and password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String returnedUser = rs.getString("username");
				String returnedPwd = rs.getString("password");
				String returnedFirstname = rs.getString("firstname");
				String returnedSurname = rs.getString("surname");
				
                                theList[0] = returnedUser;
                                theList[1] = returnedPwd;
                                theList[2] = returnedFirstname;
                                theList[3] = returnedSurname;
                                
                           	return theList;
			} else {
				return null;
			}


		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}


	}
}
