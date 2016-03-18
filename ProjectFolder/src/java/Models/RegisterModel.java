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
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Matt
 */
public class RegisterModel {

    //Carry out the registering of a student into a given class
    public boolean doRegister(int bookingID, String username) {
        try {

            //Connect to database
            Connector c = new Connector();
            Connection conn = c.getConnection();

            //Set up prepared statement and bind values to statement
            PreparedStatement ps = conn.prepareStatement("call  sign_in (?,?,?)");
            ps.setString(1, username);
            ps.setInt(2, bookingID);
            ps.setString(3, "");

            //Execute statement, return values to result set
            ResultSet rs = ps.executeQuery();

            //Get back the number of rows which have been changed
            int i = ps.getUpdateCount();

            //If no rows updated, return false
            if (i == 0) {
                return false;
            } else {
                //Return true if an update has been carried out
                return true;
            }
        } catch (Exception e) {
            //An exception may happen which will cause the statement to fail 
            //and return as failed 
            e.printStackTrace();
            return false;
        }

    }
}
