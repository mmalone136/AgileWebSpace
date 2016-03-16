/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

/**
 *
 * @author Matt
 */
public class Connector {

    public Connection getConnection() {
        try {
            String theDriver = "com.mysql.jdbc.Driver";
            Class driver_class = Class.forName(theDriver);
            Driver driver = (Driver) driver_class.newInstance();
            DriverManager.registerDriver(driver);
            Connection conn = DriverManager.getConnection("jdbc:mysql://silva.computing.dundee.ac.uk:3306/15agileteam2db?" + "user=15agileteam2&password=349.at2.psswd");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
