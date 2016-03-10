<%-- 
    Document   : createbooking
    Created on : 25-Feb-2016, 15:07:29
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
                    <li>LectID <input type="hidden" name="lec_id" value="1" ></li>
                    <li>Loc ID <input type="hidden" name="loc_id" value="22" ></li>
                    <li>Staff id <input type="hidden" name="staff_id" value="SSE2384" ></li>
                    
                    <li>FLAG CREATE<input type="hidden" name="flag" value="Create" ></li>
                    
                    
                </ul>
                <br/>
                <input type="submit" value="Create Booking"> 
            </form>
        
        
        

    </body>
</html>
