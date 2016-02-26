<%-- 
    Document   : updatebooking
    Created on : 25-Feb-2016, 15:07:46
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
<form method="POST"  action="CreateBooking">
                <ul>
                    <li>BookingID <input type="hidden" name="book" value="36" ></li>
                    <li>FLAG UPDATE<input type="hidden" name="flag" value="Update" ></li>
                    
                    
                    
                </ul>
                <br/>
                <input type="submit" value="Update Booking"> 
            </form>
    </body>
</html>
