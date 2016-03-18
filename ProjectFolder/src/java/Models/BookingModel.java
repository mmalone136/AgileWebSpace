/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Classes.Connector;
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
public class BookingModel {

    //Function to create a new booking with data passed in, no longer used in current version of system
    public int doBooking(int lec, int loc, Date theTime, String sid, int att_list) {
        try {

            //DONT THINK WE USE THIS NOW
            String theDriver = "com.mysql.jdbc.Driver";
            Class driver_class = Class.forName(theDriver);
            Driver driver = (Driver) driver_class.newInstance();
            DriverManager.registerDriver(driver);
            Connection conn = DriverManager.getConnection("jdbc:mysql://silva.computing.dundee.ac.uk:3306/15agileteam2db?" + "user=15agileteam2&password=349.at2.psswd");
            //PreparedStatement ps = conn.prepareStatement("Select * from user where username=? and password=?");

            PreparedStatement ps;

            ps = conn.prepareStatement("insert into booking (Lecture_idLecture, Locations_idLocations, startTime, Staff_idStaff, AttendanceList_idAttendanceList) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            java.sql.Timestamp sDate = new java.sql.Timestamp(theTime.getTime());

            ps.setInt(1, lec);
            ps.setInt(2, loc);
            ps.setTimestamp(3, sDate);
            ps.setString(4, sid);
            ps.setInt(5, att_list);
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();

            int generatedKey = 0;
            rs.next();

            generatedKey = rs.getInt(1);

            return generatedKey;

        } catch (Exception e) {
            e.printStackTrace();
            // null;
        }

        return -1;
    }

    //Update end time of class to show it has been completed
    public void endClass(int booking, Date theTime) {
        try {
            //Connect to database
            Connector c = new Connector();
            Connection conn = c.getConnection();

            //Prepare and bind values to statement
            PreparedStatement ps;
            ps = conn.prepareStatement("call end_class(?,?)");
            java.sql.Timestamp sDate = new java.sql.Timestamp(theTime.getTime());
            ps.setInt(1, booking);
            ps.setTimestamp(2, sDate);

            //Execute statement
            ps.execute();

        } catch (Exception e) {
            //Catch any errors/exceptions which may occur
            e.printStackTrace();
        }

    }

    //Return list of students who are to attend a specific booking
    public ArrayList<String[]> getToAttend(String bookingID) {
        ArrayList<String[]> theList = new ArrayList<String[]>();
        try {

            //Connect to database
            Connector c = new Connector();
            Connection conn = c.getConnection();

            //Prepare and bind values to statement
            PreparedStatement ps;
            ps = conn.prepareStatement("Call to_attend_booking(?)");
            ps.setInt(1, Integer.valueOf(bookingID));

            //Execute statement and return values
            ResultSet rs = ps.executeQuery();

            //Loop through results set
            while (rs.next()) {
                String[] temp = new String[5];

                //Get back values from query
                String firstname = rs.getString("firstname");
                String surname = rs.getString("surname");
                String studentID = rs.getString("idStudent");
                String username = rs.getString("User_username");

                temp[0] = firstname;
                temp[1] = surname;
                temp[2] = studentID;
                temp[3] = username;

                //Add Student to ArrayList
                theList.add(temp);
            }
            //Return list
            return theList;
        } catch (Exception e) {
            //Catch exception, return null
            e.printStackTrace();
            return null;
        }
    }

}
