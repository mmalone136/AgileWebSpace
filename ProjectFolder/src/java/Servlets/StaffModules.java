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
       
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String staff_id = request.getParameter("staff_id");


        ArrayList<String[]> theList;
        String[] temp;
        HttpSession session = request.getSession();

        StaffClassModel scm = new StaffClassModel();
        theList = scm.getAllModules(staff_id);
        //System.out.println("THE STUFF SHOULD DO THINGS HERE | "  + theList);

        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

        if (theList != null) {
            writer.append("<Modules>");
            for (int x = 0; x < theList.size(); x++) {
                temp = theList.get(x);

                String header = ("<TheModule_" + x + ">");
                String endTag = ("</TheModule_" + x + ">");

                writer.append(header);
                writer.append("<module_id>").append(temp[0]).append("</module_id>");
                writer.append("<name>").append(temp[1]).append("</name>");
                writer.append("<coordinator>").append(temp[2]).append("</coordinator>");

                
                
                System.out.println("_");
                writer.append(endTag);
                System.out.println("_");
            }
            
                            System.out.println("_");
            writer.append("</Modules>");
                System.out.println("_");
            request.setAttribute("list", theList);
            response.getWriter();
            System.out.println("PLACEHOLDER");
            //}

        } else {
            writer.append("<Modules>");
            writer.append("<name>No Modules</name>");
            writer.append("</Modules>");
        }
    }
    
    
    
    
}
