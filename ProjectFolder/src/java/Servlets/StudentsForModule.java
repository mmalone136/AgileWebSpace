/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Models.ModuleModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Matt
 */
public class StudentsForModule extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //get parameter from http request
        String module_id = request.getParameter("module_id");

        //Variables needed
        ArrayList<String[]> theList;
        String[] temp;

        //Create model and call function to get the students of that module
        ModuleModel mm = new ModuleModel();
        theList = mm.getStudentsForModule(module_id);

        //Set up xml format
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

        //Check returned list is not empty
        if (theList != null) {
            writer.append("<Students>");
            for (int x = 0; x < theList.size(); x++) {
                temp = theList.get(x);

                //Set up each sublevel tags
                String header = ("<TheStudent_" + x + ">");
                String endTag = ("</TheStudent_" + x + ">");

                writer.append(header);
                //Add all of the students data to file
                writer.append("<firstname>").append(temp[0]).append("</firstname>");
                writer.append("<surname>").append(temp[1]).append("</surname>");
                writer.append("<student_id>").append(temp[2]).append("</student_id>");

                writer.append(endTag);

            }
            //Close xml tags and set writer to http response
            writer.append("</Students>");

            response.getWriter();

        } else {
            //Set xml to return this if returned list is empty
            writer.append("<Modules>");
            writer.append("<name>No Modules</name>");
            writer.append("</Modules>");
        }
    }

}
