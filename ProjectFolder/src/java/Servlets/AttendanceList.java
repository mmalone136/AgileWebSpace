/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Models.AttendanceListModel;
import Models.StaffClassModel;
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
public class AttendanceList extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Get http request params
        String theID = request.getParameter("AttList_ID");

        ArrayList<String[]> theList;
        String[] temp;

        //Create new model and call function to get list of students back from database
        AttendanceListModel alm = new AttendanceListModel();
        theList = alm.getAttendanceList(theID);

        //Set up xml writer for response
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

        //If list is not empty
        if (theList != null) {
            writer.append("<Register>");
            for (int x = 0; x < theList.size(); x++) {
                temp = theList.get(x);
                
                
                //Set xml tags for specific student
                String header = ("<Student_" + x + ">");
                String endTag = ("</Student_" + x + ">");

                //Append all data to xml writer for the current student
                writer.append(header);
                writer.append("<firstname>").append(temp[0]).append("</firstname>");
                writer.append("<surname>").append(temp[1]).append("</surname>");
                writer.append("<student_id>").append(temp[2]).append("</student_id>");
                writer.append("<exception>").append(temp[3]).append("</exception>");
                writer.append("<present>").append(temp[4]).append("</present>");
                //Close xml tags
                writer.append(endTag);
            }

            //Close xml tags
            writer.append("</Register>");
            //Set response to have writer
            response.getWriter();

        } else {
            //No data to return as an error occurred and/or empty list was returned from data base
            writer.append("<Register>");
            writer.append("<student_id>No Registered</student_id>");
            writer.append("</Register>");
        }
    }

}
