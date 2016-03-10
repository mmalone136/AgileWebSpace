package Servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Models.LoginModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class Login extends HttpServlet {

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
        //response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String[] theList;
        String[] theSecondList;
        HttpSession session = request.getSession();

        LoginModel lm = new LoginModel();
        theList = lm.generalLogin(username, password);
        //System.out.println("THE STUFF SHOULD DO THINGS HERE | "  + theList);

        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

        if (theList != null) {

            //theSecondList = lm.staffLogin(username);
            String tag = "";
            String id = "";
            if (theList[5].equals("Staff")) {
                tag = "staff";
                id = "staff_id";
            } else if (theList[5].equals("Student")) {
                tag = "student";
                id = "student_id";
            }

            writer.append("<").append(tag).append(">");
            writer.append("<username>").append(username).append("</username>");
            writer.append("<firstname>").append(theList[2]).append("</firstname>");
            writer.append("<surname>").append(theList[3]).append("</surname>");
            //writer.append("<job_ID>").append(theSecondList[2]).append("</job_ID>");
            writer.append("<").append(id).append(">").append(theList[4]).append("</").append(id).append(">");

            writer.append("</").append(tag).append(">");

            request.setAttribute("list", theList);
            response.getWriter();
            System.out.println("PLACEHOLDER");
            //}

        } else {
            writer.append("<login_details>");
            writer.append("<user>");
            writer.append("<username>Login Failed</username>");
            writer.append("</user>");
            writer.append("</login_details>");
        }
    }

}
