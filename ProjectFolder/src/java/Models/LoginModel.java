/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Classes.Connector;
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
    public String[] generalLogin(String username, String password) {
        try {

            String[] theList = new String[8];

            //String theDriver = "com.mysql.jdbc.Driver";
            //Class driver_class = Class.forName(theDriver);
            //Driver driver = (Driver) driver_class.newInstance();
            //DriverManager.registerDriver(driver);
            //Connection conn = DriverManager.getConnection("jdbc:mysql://silva.computing.dundee.ac.uk:3306/15agileteam2db?" + "user=15agileteam2&password=349.at2.psswd");
            
            Connector c = new Connector();
            Connection conn = c.getConnection();
            
            
            PreparedStatement ps = conn.prepareStatement("call login_new (?,?)");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            String userType;
            if (rs.next()) {
                String returnedFirstname = rs.getString("firstname");
                String returnedSurname = rs.getString("surname");
                try{
                userType = rs.getString("staff");
                }catch (Exception e){
                userType = rs.getString("student");
                
                }

                theList[0] = username;
                theList[1] = password;
                theList[2] = returnedFirstname;
                theList[3] = returnedSurname;
                

                if (userType.equals("staff")) {
                    String staffID = rs.getString("idStaff");
                    theList[4] = staffID;
                    theList[5] = "Staff";
                    theList[6] = rs.getString("idJob");
                    theList[7] = rs.getString("accessLevel");
                    
                } else {
                    String studentID = rs.getString("idStudent");
                    theList[4] = studentID;
                    theList[5] = "Student";
                }
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
