/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Models.ModuleModel;
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
public class BookingsForModule extends HttpServlet {

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String staff_id = request.getParameter("module_id");

        ArrayList<String[]> theList;
        String[] temp;
        
        ModuleModel mm = new ModuleModel();
        theList = mm.getBookingsForModule(staff_id);
        //System.out.println("THE STUFF SHOULD DO THINGS HERE | "  + theList);

        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

        if (theList != null) {
            writer.append("<Bookings>");
            for (int x = 0; x < theList.size(); x++) {
                temp = theList.get(x);

                String header = ("<TheBooking_" + x + ">");
                String endTag = ("</TheBooking_" + x + ">");

                writer.append(header);
                writer.append("<booking_id>").append(temp[0]).append("</booking_id>");
                writer.append("<lecture_id>").append(temp[1]).append("</lecture_id>");
                writer.append("<start_time>").append(temp[2]).append("</start_time>");
                writer.append("<end_time>").append(temp[3]).append("</end_time>");
                writer.append("<location_id>").append(temp[4]).append("</location_id>");
                writer.append("<staff_id>").append(temp[5]).append("</staff_id>");
                writer.append("<att_list_id>").append(temp[6]).append("</att_list_id>");
                writer.append("<duration>").append(temp[7]).append("</duration>");
                writer.append("<class_name>").append(temp[8]).append("</class_name>");
                writer.append("<class_type>").append(temp[9]).append("</class_type>");
                writer.append("<room_number>").append(temp[10]).append("</room_number>");
                writer.append("<building>").append(temp[11]).append("</building>");
                writer.append("<module_id>").append(temp[12]).append("</module_id>");

                writer.append(endTag);

            }
            
            writer.append("</Bookings>");
            request.setAttribute("list", theList);
            response.getWriter();


        } else {
            writer.append("<Bookings>");
            writer.append("<booking_id>No Bookings</booking_id>");
            writer.append("</Bookings>");
        }
    }
    
    
    
    
}
