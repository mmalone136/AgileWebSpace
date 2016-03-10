/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Models.LoginModel;
import Models.RegisterModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Matt
 */
public class Register extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int bookingID = Integer.parseInt(request.getParameter("booking_id"));
        String username = request.getParameter("username");

        //String [] theList;
        HttpSession session = request.getSession();

        RegisterModel rm = new RegisterModel();
        boolean success = rm.doRegister(bookingID, username);
        String str = String.valueOf(success);

        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

        writer.append("<register>");

        writer.append("<user>");
        writer.append("<username>").append(username).append("</username>");
        writer.append("<success>").append(str).append("</success>");

        writer.append("</user>");

        writer.append("</register>");

		  //request.setAttribute("list", theList);
        //response.getWriter();
                  //System.out.println("PLACEHOLDER");
    }
}
