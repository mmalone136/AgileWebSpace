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
import java.util.List;

/**
 *
 * @author Matt
 */
public class StaffClassModel {

    public ArrayList<String[]> getClasss(String staff_ID) {
        try {

            ArrayList<String[]> theList = new ArrayList<String[]>();

            

            //String theDriver = "com.mysql.jdbc.Driver";
            //Class driver_class = Class.forName(theDriver);
            //Driver driver = (Driver) driver_class.newInstance();
            //DriverManager.registerDriver(driver);
            //Connection conn = DriverManager.getConnection("jdbc:mysql://silva.computing.dundee.ac.uk:3306/15agileteam2db?" + "user=15agileteam2&password=349.at2.psswd");
            
            Connector c = new Connector();
            Connection conn = c.getConnection();
            
            
            PreparedStatement ps = conn.prepareStatement("call todays_classes (?)");
            ps.setString(1, staff_ID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String[] temp = new String[12];
                
                temp[0] = rs.getString("idBooking");
                temp[1] = rs.getString("Lecture_idLecture");
                temp[2] = rs.getString("startTime");
                temp[3] = rs.getString("endTime");
                temp[4] = rs.getString("Locations_idLocations");
                temp[5] = rs.getString("Staff_idStaff");
                temp[6] = rs.getString("AttendanceList_idAttendanceList");
                temp[7] = rs.getString("idModule");
                temp[8] = rs.getString("name");
                temp[9] = rs.getString("type");
                temp[10] = rs.getString("roomNumber");
                temp[11] = rs.getString("building");

                theList.add(temp);
            }
            return theList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    
    
    
    public ArrayList<String[]> getAllModules(String staff_ID) {
        try {

            ArrayList<String[]> theList = new ArrayList<String[]>();

            

            //String theDriver = "com.mysql.jdbc.Driver";
            //Class driver_class = Class.forName(theDriver);
            //Driver driver = (Driver) driver_class.newInstance();
            //DriverManager.registerDriver(driver);
            //Connection conn = DriverManager.getConnection("jdbc:mysql://silva.computing.dundee.ac.uk:3306/15agileteam2db?" + "user=15agileteam2&password=349.at2.psswd");
            
            Connector c = new Connector();
            Connection conn = c.getConnection();
            
            
            PreparedStatement ps = conn.prepareStatement("call get_modules_for_staff(?)");
            ps.setString(1, staff_ID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String[] temp = new String[12];
                
                temp[0] = rs.getString("idModule");
                temp[1] = rs.getString("name");
                temp[2] = rs.getString("coordinator");


                theList.add(temp);
            }
            return theList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
