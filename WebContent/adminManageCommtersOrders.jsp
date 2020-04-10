<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.kbconnect.boundary.*, com.kbconnect.entity.*"%>

<%
	CommuterDAO commuterDao = new CommuterDAO();
	ArrayList<User> commuterList = commuterDao.getAllUsers();
	OrderDAO orderDao = new OrderDAO();

	AdminDAO userDao = new AdminDAO();
	if (session.getAttribute("adminUsername") == null) {
		response.sendRedirect("adminLogin.jsp?message=login");
		return;
	}
	String username = String.valueOf(session.getAttribute("adminUsername"));

	User user = userDao.getUser(username);
	if (user == null) {
		response.sendRedirect("adminLogin.jsp?message=login");
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<title>Manage Commuters' Orders</title>
</head>
<body>

	<nav class="navbar navbar-light bg-light">
		<a class="navbar-brand" href="adminProfile.jsp"> <%
 	out.print( user.get_username() );
 %>
		</a>

		<form action="LoginController" method="post">
			<input type="hidden" value="logout" name="action"> <input
				class="btn btn-primary" type="submit" value="Logout">
		</form>
	</nav>
	
	<h2 align="center">Manage All Commuters' Orders</h2>

	<table class="table">
		<tr>
		<thead>
			<th>Full Name</th>
			<th>Username</th>
			<th>email</th>
			<th>Address</th>
			<th>Total Products Selected</th>
			<th>Manage Order</th>
		</thead>
		</tr>
		<%
			ArrayList<Order> userOrderList;
			for (User commuterOne : commuterList) {
				out.println("<tr>");
				out.println("<td> " + commuterOne.get_fullName() + " </td>");
				out.println("<td> " + commuterOne.get_username() + " </td>");
				out.println("<td> " + commuterOne.get_email() + " </td>");
				out.println("<td> " + commuterOne.get_address() + " </td>");
				userOrderList = orderDao.getAllUserOrders(commuterOne.get_id());
				if (userOrderList.size() >= 1) {
					out.println("<td align='center'>" + userOrderList.size() + " </td>");
					out.println("<form action='UserDAOController' method='post'>");
					out.println("<input type='hidden' name='userId' value='" + commuterOne.get_id() + "'>");
					out.println("<td><button type='submit' formaction='adminCreateNewOrder.jsp'>Manage Order</button>");
					out.println("</form></td>");
				} else {
					out.println("<td align='center'> 0 </td>");
					out.println("<form action='UserDAOController' method='post'>");
					out.println("<input type='hidden' name='userId' value='" + commuterOne.get_id() + "'>");
					out.println("<td><button type='submit' formaction='adminCreateNewOrder.jsp'>Add Order</button>");
					out.println("</form></td>");
				}

				out.println("</tr>");
			}
		%>
	</table>
	<hr>


</body>
</html>