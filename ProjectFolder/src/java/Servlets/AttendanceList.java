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

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AttendanceList</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AttendanceList at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String theID = request.getParameter("AttList_ID");

        ArrayList<String[]> theList;
        String[] temp;
        HttpSession session = request.getSession();

        AttendanceListModel alm = new AttendanceListModel();
        theList = alm.getAttendanceList(theID);
        //System.out.println("THE STUFF SHOULD DO THINGS HERE | "  + theList);

        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

        if (theList != null) {
            writer.append("<Register>");
            for (int x = 0; x < theList.size(); x++) {
                temp = theList.get(x);

                String header = ("<Student_" + x + ">");
                String endTag = ("</Student_" + x + ">");

                writer.append(header);
                writer.append("<firstname>").append(temp[0]).append("</firstname>");
                writer.append("<surname>").append(temp[1]).append("</surname>");
                writer.append("<student_id>").append(temp[2]).append("</student_id>");
                writer.append("<exception>").append(temp[3]).append("</exception>");
                writer.append("<present>").append(temp[4]).append("</present>");

                System.out.println("_");
                writer.append(endTag);
                System.out.println("_");
            }
            
                            System.out.println("_");
            writer.append("</Register>");
                System.out.println("_");
            request.setAttribute("list", theList);
            response.getWriter();
            System.out.println("PLACEHOLDER");
            //}

        } else {
            writer.append("<Register>");
            writer.append("<student_id>No Registered</student_id>");
            writer.append("</Register>");
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
