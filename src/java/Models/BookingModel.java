/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

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
    
    // Perform login
	public int doBooking(int lec,int loc,Date theTime,String sid,int att_list) {
		try {
			
			
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



public void endClass(int booking,Date theTime)
{
    	try{
			String theDriver = "com.mysql.jdbc.Driver";
			Class driver_class = Class.forName(theDriver);
			Driver driver = (Driver) driver_class.newInstance();
			DriverManager.registerDriver(driver);
			Connection conn = DriverManager.getConnection("jdbc:mysql://silva.computing.dundee.ac.uk:3306/15agileteam2db?" + "user=15agileteam2&password=349.at2.psswd");
			//PreparedStatement ps = conn.prepareStatement("Select * from user where username=? and password=?");
			
                        PreparedStatement ps;
                        
                        ps = conn.prepareStatement("update booking set endTime = ? where idBooking = ?");
                        
                        
                        
                        
                        java.sql.Timestamp sDate = new java.sql.Timestamp(theTime.getTime());
                        
                        ps.setInt(2, booking);
			ps.setTimestamp(1, sDate);

			ps.execute();
		
} catch (Exception e) {
			e.printStackTrace();
			// null;
		}
        


}






}
