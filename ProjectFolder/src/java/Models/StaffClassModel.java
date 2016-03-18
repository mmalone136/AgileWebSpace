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

            Connector c = new Connector();
            Connection conn = c.getConnection();
                        
            PreparedStatement ps = conn.prepareStatement("call get_modules_for_staff(?)");
            ps.setString(1, staff_ID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String[] temp = new String[3];
                
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

        public ArrayList<String[]> getModulesWithAccess(String access_level, String staff_id) {
        try {

            ArrayList<String[]> theList = new ArrayList<String[]>();

            Connector c = new Connector();
            Connection conn = c.getConnection();
                        
            PreparedStatement ps = conn.prepareStatement("call get_modules");
            //ps.setString(1, staff_ID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String[] temp = new String[5];
                
                temp[0] = rs.getString("idModule");
                temp[1] = rs.getString("name");
                temp[2] = rs.getString("coordinator");
                temp[3] = rs.getString("firstname");
                temp[4] = rs.getString("surname");
                
                //If the staff member is the coordinator
                boolean isLecturer = staff_id.equals(temp[2]);
                
                //Is the staff member dean or head of L&T
                boolean bossPeople = access_level.equals("1");
                
                //if head of postgrad & is postgrad module
                boolean postGrad = false;
                String tempPG = temp[0];
                int i = Integer.parseInt(tempPG.substring(2,3));          
                if(access_level.equals("3") && i>4){
                    postGrad = true;
                }
                
                
                //if head of undergrad & is undergrad module
                boolean underGrad = false;
                String tempUG = temp[0];
                int j = Integer.parseInt(tempUG.substring(2,3));          
                if(access_level.equals("2") && j<5){
                    underGrad = true;
                }

                
                //if access_level = year tutor for certain year >>> ADD
                boolean yearTutor = false;
                String tempYear = temp[0];
                int acc_lev = Integer.parseInt(access_level);
                if (acc_lev >=4 && acc_lev <= 7)
                {
                acc_lev = acc_lev - 3;
                int k = Integer.parseInt(tempYear.substring(2,3));          
                    if(k == acc_lev){
                        yearTutor = true;
                    }else{
                    
                    }
                }else{
                    yearTutor = false;
                }
                
                if(isLecturer || bossPeople || postGrad || underGrad || yearTutor)
                {
                    theList.add(temp);
                }
                }
            return theList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    
    
}
