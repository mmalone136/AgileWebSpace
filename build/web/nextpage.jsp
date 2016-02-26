<%-- 
    Document   : nextpage
    Created on : 24-Feb-2016, 16:21:08
    Author     : Matt
--%>

<!DOCTYPE html>
<html>

<body>
 <%
		//ArrayList<String> theList = (ArrayList<String>) request.getAttribute("list");
	        String [] theList;
                theList = (String[]) request.getAttribute("list");
                
                String username = theList[0];
		String password = theList[1];
		String first = theList[2];
		String last = theList[3];
			
	%>
    <br><br>
    <%            

    %>  
    <br><br>
    First Name:<%=first%> <br>
    Surname:<%=last%> <br>
    Username:<%=username%> <br>
    Password:<%=password%> <br>


</body>  
</html>
