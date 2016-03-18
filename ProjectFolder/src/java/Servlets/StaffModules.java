/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

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
public class StaffModules extends HttpServlet {

    //This may not be used in current version of system
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Get http request parameters
        String staff_id = request.getParameter("staff_id");

        ArrayList<String[]> theList;
        String[] temp;

        //Create new staff class model and call function to return list of modules
        StaffClassModel scm = new StaffClassModel();
        theList = scm.getAllModules(staff_id);

        //Set xml response
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

        //If list not empty
        if (theList != null) {
            writer.append("<Modules>");
            for (int x = 0; x < theList.size(); x++) {
                temp = theList.get(x);

                //Set xml tags for specific module in list
                String header = ("<TheModule_" + x + ">");
                String endTag = ("</TheModule_" + x + ">");

                //Append module data on writer for current module
                writer.append(header);
                writer.append("<module_id>").append(temp[0]).append("</module_id>");
                writer.append("<name>").append(temp[1]).append("</name>");
                writer.append("<coordinator>").append(temp[2]).append("</coordinator>");

                //Close xml tags
                writer.append(endTag);
            }
            //Close xml tags
            writer.append("</Modules>");
            //Set xml as part of rresponse
            response.getWriter();

        } else {

            //There was an error and/or empty list was returned from database
            writer.append("<Modules>");
            writer.append("<name>No Modules</name>");
            writer.append("</Modules>");
        }
    }

}
