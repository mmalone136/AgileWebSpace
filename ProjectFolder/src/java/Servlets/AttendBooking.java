/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Models.AttendanceListModel;
import Models.BookingModel;
import java.io.IOException;
import java.io.PrintWriter;
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
public class AttendBooking extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Get Parameter
        String theID = request.getParameter("booking_id");

        ArrayList<String[]> theList;
        String[] temp;

        //Create booking model and call function to get students to attend
        BookingModel bm = new BookingModel();
        theList = bm.getToAttend(theID);

        //Set up xml for response
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

        //If the list is not empty
        if (theList != null) {
            //Add outer xml tags
            writer.append("<ToAttend>");
            for (int x = 0; x < theList.size(); x++) {
                temp = theList.get(x);

                //Set up each sublevel tags
                String header = ("<Student_" + x + ">");
                String endTag = ("</Student_" + x + ">");

                writer.append(header);
                //Add all of the students data to file
                writer.append("<firstname>").append(temp[0]).append("</firstname>");
                writer.append("<surname>").append(temp[1]).append("</surname>");
                writer.append("<student_id>").append(temp[2]).append("</student_id>");
                writer.append("<username>").append(temp[3]).append("</username>");

                //Close current student
                writer.append(endTag);

            }
            //Close xml tags and set writer to http response
            writer.append("</ToAttend>");
            request.setAttribute("list", theList);
            response.getWriter();

        } else {
            //Set xml to return no registered if returned list is empty
            writer.append("<Register>");
            writer.append("<student_id>No Registered</student_id>");
            writer.append("</Register>");
        }
    }

}
