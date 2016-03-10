/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Models.AttendanceListModel;
import Models.BookingModel;
import Models.LoginModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Matt
 */
public class CreateBooking extends HttpServlet {

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
            out.println("<title>Servlet CreateBooking</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateBooking at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    
    
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session=request.getSession();
        String flag = request.getParameter("flag");
        int att_List= -1;
        if (flag.equals("Create"))
        {
            
            AttendanceListModel alm = new AttendanceListModel();
            att_List = alm.createList();
            
            int lecture_id = Integer.parseInt(request.getParameter("lec_id"));
            int location_id = Integer.parseInt(request.getParameter("loc_id"));
            String staff_id = request.getParameter("staff_id");

            Date theTime = new Date();
            BookingModel bm = new BookingModel();
            
            int genKey = bm.doBooking(lecture_id,location_id,theTime,staff_id,att_List);
            
            
            response.setContentType("text/xml;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            writer.append("<booking_details>");
                       
            writer.append("<Data>");
            writer.append("<AttListID>" + att_List +"</AttListID>");      
            writer.append("<booking>" + genKey +"</booking>"); 
            writer.append("</Data>");
            
            writer.append("</booking_details>");

            response.getWriter();
            //RequestDispatcher rd=request.getRequestDispatcher("writer");
	    //rd.forward(request,response);
            System.out.println("PLACEHOLDER");
                        
        }else{
        int booking_id = Integer.parseInt(request.getParameter("book"));
        Date theTime = new Date();
            BookingModel bm = new BookingModel();
            bm.endClass(booking_id,theTime);
        
        }


        
    }

}
