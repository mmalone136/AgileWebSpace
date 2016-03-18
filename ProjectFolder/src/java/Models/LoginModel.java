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

            //Connect to database
            Connector c = new Connector();
            Connection conn = c.getConnection();

            //Prepare and bind values to statement
            PreparedStatement ps = conn.prepareStatement("call login_new (?,?)");
            ps.setString(1, username);
            ps.setString(2, password);
            //Execute statement and return values
            ResultSet rs = ps.executeQuery();

            String userType;
            //Check if anything in result set, logged in success will be in first of 1 position
            if (rs.next()) {
                //Get data values back from query
                String returnedFirstname = rs.getString("firstname");
                String returnedSurname = rs.getString("surname");

                //Distinguish between staff or student
                try {
                    userType = rs.getString("staff");
                } catch (Exception e) {
                    userType = rs.getString("student");
                }

                //Read values into array
                theList[0] = username;
                theList[1] = password;
                theList[2] = returnedFirstname;
                theList[3] = returnedSurname;

                //If is a staff
                if (userType.equals("staff")) {
                    //Get relevant detail for staff
                    String staffID = rs.getString("idStaff");
                    theList[4] = staffID;
                    theList[5] = "Staff";
                    theList[6] = rs.getString("idJob");
                    theList[7] = rs.getString("accessLevel");

                } else {
                    //If student
                    //Get relevant details for students
                    String studentID = rs.getString("idStudent");
                    theList[4] = studentID;
                    theList[5] = "Student";
                }

                //Return the list of user data 
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
