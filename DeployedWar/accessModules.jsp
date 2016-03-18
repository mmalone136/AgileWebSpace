<%-- 
    Document   : accessModules.jsp
    Created on : 18-Mar-2016, 12:56:01
    Author     : Matt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
                <form method="POST"  action="StaffAccessModules">
                <ul>
                    <li>Staff_id <input type="text" name="staff_id" value ="SSE2451"></li>
                    <li>Access Level <input type="text" name="access_level" value ="2"></li>
                </ul>
                <br/>
                <input type="submit" value="GetThings"> 
            </form>
    </body>
</html>
