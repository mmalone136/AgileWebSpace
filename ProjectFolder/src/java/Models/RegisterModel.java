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

    public boolean doRegister(int bookingID, String username) {
        try {

            
            Connector c = new Connector();
            Connection conn = c.getConnection();
            
            PreparedStatement ps = conn.prepareStatement("call  sign_in (?,?,?)");
            ps.setString(1, username);
            ps.setInt(2, bookingID);
            ps.setString(3, "");

            ResultSet rs = ps.executeQuery();

            int i = ps.getUpdateCount();

            if (i == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
