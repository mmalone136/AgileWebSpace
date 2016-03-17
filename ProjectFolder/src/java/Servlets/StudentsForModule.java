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

        String module_id = request.getParameter("module_id");

        ArrayList<String[]> theList;
        String[] temp;
        
        ModuleModel mm = new ModuleModel();
        theList = mm.getStudentsForModule(module_id);

        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

        if (theList != null) {
            writer.append("<Students>");
            for (int x = 0; x < theList.size(); x++) {
                temp = theList.get(x);

                String header = ("<TheStudent_" + x + ">");
                String endTag = ("</TheStudent_" + x + ">");

                writer.append(header);
                writer.append("<firstname>").append(temp[0]).append("</firstname>");
                writer.append("<surname>").append(temp[1]).append("</surname>");
                writer.append("<student_id>").append(temp[2]).append("</student_id>");

                writer.append(endTag);

            }
            
                writer.append("</Students>");

            response.getWriter();

        } else {
            writer.append("<Modules>");
            writer.append("<name>No Modules</name>");
            writer.append("</Modules>");
        }
    }

}
