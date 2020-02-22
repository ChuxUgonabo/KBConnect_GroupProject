<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page = import="java.util.*" %>
<%@ page import="com.kbconnect.entity.*, com.kbconnect.boundary.*"%>

<%
	AdminDAO adminDao = new AdminDAO();
	ComuterDAO comuterDa0 = new ComuterDAO();
	ArrayList<User> listOfUsers = new ArrayList<User>();
	listOfUsers = comuterDa0.getAllUsers();
	
	if (session.getAttribute("username") == null) {
		response.sendRedirect("login.jsp?message=login");
	} 
	
	String username = String.valueOf(session.getAttribute("username"));
	
	Admin admin = adminDao.getUser(username);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title><% out.print(admin.get_username()); %></title>
</head>
<body>
    <p><% out.print(admin.get_username()); %></p>
    
     <form action="LoginController" method="post">
    <input class="btn btn-primary" type="submit" name="action" value="logout">
    </form>   

    <hr>

    <ul class="list-group">
        <li class="list-group-item"><strong>Username: </strong><% out.print(admin.get_username()); %></li>
        <li class="list-group-item"><strong>Fullname: </strong><% out.print(admin.get_fullName()); %></li>
        <li class="list-group-item"><strong>Email: </strong><% out.print(admin.get_email()); %></li>
        <li class="list-group-item"><strong>Address: </strong><% out.print(admin.get_address()); %></li>
    
    </ul>

    <hr>
    <table>
   		<tr>	
  			<thead>
  			<tr>Full Name</tr>
  			<tr>Username</tr>
  			<tr>Email</tr>
  			<tr>Address</tr>
  			<tr>Card NUmber</tr>
  			<tr>Edit</tr>
  			<tr>Delete</tr>
  			
  			</thead>
   			
   		</tr>
    	<%
    		for(User userOne : listOfUsers){
    			out.println("<tr>");
    			out.println("<td>"+ userOne.get_fullName() +"</td>");
    			out.println("<td>"+ userOne.get_username() +"</td>");
    			out.println("<td>"+ userOne.get_email() +"</td>");
    			out.println("<td>"+ userOne.get_address() +"</td>");
    			out.println("<td>"+ userOne.get_cardNumber() +"</td>");
    			out.println("<td><a href=editProfile.jsp?action=edit&id"+userOne.get_id()+">Edit</a></td>");
    			out.println("<td><a href=UserDAOController?action=delete&id"+userOne.get_id()+">Delete</a></td>");
    			out.println("</tr>");

    		}
    	
    	%>
   	</table>
	 <hr>
	<p>Create a <a href="registerUser.jsp">new Commuter.</a></p>   

</body>
</html>