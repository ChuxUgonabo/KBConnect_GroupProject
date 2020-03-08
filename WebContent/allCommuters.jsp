<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.kbconnect.boundary.*, com.kbconnect.entity.*"%>

<%
	ComuterDAO commuterDao = new ComuterDAO();
	ArrayList<User> commuterList = commuterDao.getAllUsers();

	AdminDAO userDao = new AdminDAO();
	if (session.getAttribute("username") == null ) {
		response.sendRedirect("adminLogin.jsp?message=login");
		return;
	}
	String username = String.valueOf(session.getAttribute("username"));

	User user = userDao.getUser(username);
	if (user == null ) {
		response.sendRedirect("adminLogin.jsp?message=login");
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

<title>List of Commuters</title>
</head>
<body>	
	<h2>All Registered Commuters</h2>
	<form action="LoginController" method="post">
		<input type="hidden" value="admin" name="admin">
        <input type="hidden" value="logout" name="action">
		<input class="btn btn-primary" type="submit" value="Logout">
	</form>

	<table>
		<tr>
			<thead>
				<th>ID</th>
				<th>Full Name</th>
				<th>Username</th>
				<th>email</th>
				<th>Card Number</th>
				<th>Date Of Birth</th>
				<th>Address</th>
				<th>Edit</th>	
				<th>Delete</th>	
			</thead>
		</tr>
		<%
			for(User commuterOne : commuterList){
				out.println("<tr>");
				out.println("<td> "+commuterOne.get_id()+ " </td>");
				out.println("<td> "+commuterOne.get_fullName()+ " </td>");
				out.println("<td> "+commuterOne.get_username()+ " </td>");
				out.println("<td> "+commuterOne.get_email()+ " </td>");
				out.println("<td> "+commuterOne.get_cardNumber()+ " </td>");
				out.println("<td> "+commuterOne.get_DOB()+ " </td>");
				out.println("<td> "+commuterOne.get_address()+ " </td>");
				out.println("<td><a href='editProfile.jsp?action=update&userId=" +commuterOne.get_id()+ "'>Edit</a> </td>");
                out.println("<td> <form action='UserDAOController' method='post'>");
                out.println("<input type='hidden' value='"+commuterOne.get_id()+ "' name='userId'>");
                out.println("<input type='hidden' name='action' value='delete' >");
                out.println("<input type='submit' value='Delete' >");
                out.println("</form></td>");
				out.println("</tr>");	
			}
		%>
	</table>
</body>
</html>