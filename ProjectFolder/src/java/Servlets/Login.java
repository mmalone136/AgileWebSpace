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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Get http request parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String[] theList;

        //Create login model and call function to carry out login functionality with database
        LoginModel lm = new LoginModel();
        theList = lm.generalLogin(username, password);

        //Set up xml writer
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

        //Check if response list is empty or not
        if (theList != null) {

            String tag = "";
            String id = "";
            //Distiinguish between staff or student and set tag variables as required
            if (theList[5].equals("Staff")) {
                tag = "staff";
                id = "staff_id";
            } else if (theList[5].equals("Student")) {
                tag = "student";
                id = "student_id";
            }

            //Add correct details to xml writer object
            writer.append("<").append(tag).append(">");
            writer.append("<username>").append(username).append("</username>");
            writer.append("<firstname>").append(theList[2]).append("</firstname>");
            writer.append("<surname>").append(theList[3]).append("</surname>");
            writer.append("<job_id>").append(theList[6]).append("</job_id>");
            writer.append("<access_level>").append(theList[7]).append("</access_level>");
            writer.append("<").append(id).append(">").append(theList[4]).append("</").append(id).append(">");

            //Close xml tags
            writer.append("</").append(tag).append(">");
            //Set writer as part of http response
            response.getWriter();

        } else {
            //Return failure message in xml response data if the login has failed
            writer.append("<login_details>");
            writer.append("<user>");
            writer.append("<username>Login Failed</username>");
            writer.append("</user>");
            writer.append("</login_details>");
        }
    }

}
