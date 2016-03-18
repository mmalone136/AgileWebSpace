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

    //Function to get classes for a specific staff member
    public ArrayList<String[]> getClasss(String staff_ID) {
        try {

            ArrayList<String[]> theList = new ArrayList<String[]>();

            //Connect to database
            Connector c = new Connector();
            Connection conn = c.getConnection();

            //Prepare and bind values to statement
            PreparedStatement ps = conn.prepareStatement("call todays_classes (?)");
            ps.setString(1, staff_ID);

            //Execute and return values to result set
            ResultSet rs = ps.executeQuery();

            //Loop through result set
            while (rs.next()) {
                String[] temp = new String[12];

                //Get returned values
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

                //Add class (lecture) to list
                theList.add(temp);
            }
            //Return list
            return theList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    //Function to get all modules for a specific staff member --- May now be unused
    public ArrayList<String[]> getAllModules(String staff_ID) {
        try {
            ArrayList<String[]> theList = new ArrayList<String[]>();

            //Connect to database
            Connector c = new Connector();
            Connection conn = c.getConnection();

            //Prepare and bind values to statement
            PreparedStatement ps = conn.prepareStatement("call get_modules_for_staff(?)");
            ps.setString(1, staff_ID);

            //Execute query and return values
            ResultSet rs = ps.executeQuery();

            //Loop through result set
            while (rs.next()) {
                String[] temp = new String[3];

                //Get current return values from result set
                temp[0] = rs.getString("idModule");
                temp[1] = rs.getString("name");
                temp[2] = rs.getString("coordinator");

                //Add current module details to list
                theList.add(temp);
            }
            //Return list
            return theList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    //Updated function to get all modules for a specific taking into account their access level
    public ArrayList<String[]> getModulesWithAccess(String access_level, String staff_id) {
        try {

            ArrayList<String[]> theList = new ArrayList<String[]>();

            //Connect to database
            Connector c = new Connector();
            Connection conn = c.getConnection();

            //Prepare and bind values to statement
            PreparedStatement ps = conn.prepareStatement("call get_modules");
            //ps.setString(1, staff_ID);
            ResultSet rs = ps.executeQuery();

            //Loop through results set
            while (rs.next()) {
                String[] temp = new String[5];

                //Get returned values
                temp[0] = rs.getString("idModule");
                temp[1] = rs.getString("name");
                temp[2] = rs.getString("coordinator");
                temp[3] = rs.getString("firstname");
                temp[4] = rs.getString("surname");

                //Check If the staff member is the coordinator
                boolean isLecturer = staff_id.equals(temp[2]);

                //Check if the staff member is dean or head of L&T
                boolean bossPeople = access_level.equals("1");

                //Check if the staff member is head of postgrad 
                //& if module is postgrad module
                boolean postGrad = false;
                String tempPG = temp[0];
                int i = Integer.parseInt(tempPG.substring(2, 3));
                if (access_level.equals("3") && i > 4) {
                    postGrad = true;
                }

                //Check if the staff member is head of undergrad 
                //Check if head of undergrad & is undergrad module
                boolean underGrad = false;
                String tempUG = temp[0];
                int j = Integer.parseInt(tempUG.substring(2, 3));
                if (access_level.equals("2") && j < 5) {
                    underGrad = true;
                }

                //If the passed in access level is that for the specific 
                //year tutor for the module being checked
                boolean yearTutor = false;
                String tempYear = temp[0];
                int acc_lev = Integer.parseInt(access_level);
                if (acc_lev >= 4 && acc_lev <= 7) {
                    acc_lev = acc_lev - 3;
                    int k = Integer.parseInt(tempYear.substring(2, 3));
                    if (k == acc_lev) {
                        yearTutor = true;
                    } else {

                    }
                } else {
                    yearTutor = false;
                }

                //If any of these are true, add to the list 
                if (isLecturer || bossPeople || postGrad || underGrad || yearTutor) {
                    theList.add(temp);
                }
            }
            //Return the completed list
            return theList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
