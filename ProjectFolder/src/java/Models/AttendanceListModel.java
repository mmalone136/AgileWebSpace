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
public class AttendanceListModel {

    // Possibly Unused function now
    public int createList() {
        try {

            Connector c = new Connector();
            Connection conn = c.getConnection();

            PreparedStatement ps;
            ps = conn.prepareStatement("insert into attendancelist () VALUES ()", Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            int generatedKey = 0;
            rs.next();
            generatedKey = rs.getInt(1);
            return generatedKey;

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
            // null;
        }

    }

    //Returns attendance for the given ID passed in
    public ArrayList<String[]> getAttendanceList(String attListID) {
        ArrayList<String[]> theList = new ArrayList<String[]>();
        try {
            //Connect to database
            Connector c = new Connector();
            Connection conn = c.getConnection();

            //Set up prepared statement and bind value to statement
            PreparedStatement ps;
            ps = conn.prepareStatement("Call view_attendance_list(?)");
            ps.setInt(1, Integer.valueOf(attListID));

            //Execute statement, return values to result set
            ResultSet rs = ps.executeQuery();

            //Loop through result set
            while (rs.next()) {
                String[] temp = new String[5];

                //Read Values from the result set
                String firstname = rs.getString("firstname");
                String surname = rs.getString("surname");
                String studentID = rs.getString("Student_idStudent");
                String theException = rs.getString("exception");
                String present = rs.getString("present");

                //Set values to an array
                temp[0] = firstname;
                temp[1] = surname;
                temp[2] = studentID;
                temp[3] = theException;
                temp[4] = present;

                //Add current response to the list
                theList.add(temp);
            }
            //Return the list of returned students
            return theList;
        } catch (Exception e) {
            //There's been an error, return null
            e.printStackTrace();
            return null;
        }
    }

}
