<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*"%>
<%@ page import="com.kbconnect.boundary.*, com.kbconnect.entity.*"%>

<%
	ProductDAO productDao = new ProductDAO();
	ArrayList<Product> productList = new ArrayList<Product>();
	productList = productDao.getAllProducts();

	ComuterDAO userDao = new ComuterDAO();
	if (session.getAttribute("username") == null ) {
		response.sendRedirect("login.jsp?message=login");
	}
	String username = String.valueOf(session.getAttribute("username"));

	User user = userDao.getUser(username);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title><% out.print(user.get_username()); %></title>
</head>
<body>
	<% out.print(user.get_username()); %>
	<form action="LoginController" method="post">
		<input class="btn btn-primary" type="submit" name="action" value="logout">
	</form>
	
	<table>
		<tr>
			<thead>
				<th>ID</th>
				<th>Description</th>
				<th>Price</th>
				<th>Type</th>	
			</thead>
		</tr>
		<%
			for(Product productOne : productList){
				out.println("<tr>");
				out.println("<td> "+productOne.get_id()+ " </td>");
				out.println("<td> "+productOne.get_description()+ " </td>");
				out.println("<td> "+productOne.get_price()+ " </td>");
				out.println("<td> "+productOne.get_id()+ " </td>");
				out.println("</tr>");
			}
		%>
	</table>

</body>
</html>