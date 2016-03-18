/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Classes.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Matt
 */
public class ModuleModel {

    ////Function to get all Students for the specified module
    public ArrayList<String[]> getStudentsForModule(String module_id) {
        try {

            ArrayList<String[]> theList = new ArrayList<String[]>();

            //Connect to database
            Connector c = new Connector();
            Connection conn = c.getConnection();

            //Prepare and bind values to statement
            PreparedStatement ps = conn.prepareStatement("call get_students_on_module(?)");
            ps.setString(1, module_id);

            //Execute statement and return values
            ResultSet rs = ps.executeQuery();

            //Loop through results set
            while (rs.next()) {
                String[] temp = new String[3];

                //Get data back from result set
                temp[0] = rs.getString("firstname");
                temp[1] = rs.getString("surname");
                temp[2] = rs.getString("idStudent");

                //Add to list
                theList.add(temp);
            }
            //Return list of Student
            return theList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    ////Function to get all bookings for the specified module
    public ArrayList<String[]> getBookingsForModule(String module_id) {
        try {

            ArrayList<String[]> theList = new ArrayList<String[]>();

            //Connect to database
            Connector c = new Connector();
            Connection conn = c.getConnection();

            //Prepare and bind values to statement
            PreparedStatement ps = conn.prepareStatement("call bookings_for_module(?)");
            ps.setString(1, module_id);
            //Execute statement and return values
            ResultSet rs = ps.executeQuery();

            //Loop through results set
            while (rs.next()) {
                String[] temp = new String[13];

                //Get data back from result set
                temp[0] = rs.getString("idBooking");
                temp[1] = rs.getString("Lecture_idLecture");
                temp[2] = rs.getString("startTime");
                temp[3] = rs.getString("endTime");
                temp[4] = rs.getString("Locations_idLocations");
                temp[5] = rs.getString("Staff_idStaff");
                temp[6] = rs.getString("AttendanceList_idAttendanceList");
                temp[7] = rs.getString("duration");
                temp[8] = rs.getString("name");
                temp[9] = rs.getString("type");
                temp[10] = rs.getString("roomNumber");
                temp[11] = rs.getString("building");
                temp[12] = module_id;

                //Add to list
                theList.add(temp);
            }
            //Return list of bookings
            return theList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
