<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*"%>
<%@ page import="com.kbconnect.boundary.*, com.kbconnect.entity.*"%>

<%
	ProductDAO productDao = new ProductDAO();
	TravelPassDAO passDao = new TravelPassDAO();
	ArrayList<TravelPass> passList = passDao.getAllPasses();
	ArrayList<Product> productList = new ArrayList<Product>();
	productList = productDao.getAllProducts();

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
<title>
	<%
		out.print(user.get_username());
	%>
</title>
</head>
<body>
	<nav class="navbar navbar-light bg-light">
		<a class="navbar-brand" href="adminProfile.jsp"> <%
 	out.print(user.get_username());
 %>
		</a>

		<form action="LoginController" method="post">
			<input type="hidden" value="logout" name="action"> <input
				class="btn btn-primary" type="submit" value="Logout">
		</form>
	</nav>

	<h2 align='center'>Manage Products</h2>
	<table class="table">
		<thead>

			<tr>
				<h4>Current Available Cards</h4>
			</tr>
			<br>
			<tr>
				<th scope="col">ID</th>
				<th scope="col">Description</th>
				<th scope="col">Price</th>
				<th scope="col">Type</th>
				<th scope="col">Edit</th>
				<th scope="col">Delete</th>
			</tr>
		</thead>

		<%
			for (Product productOne : productList) {
				out.println("<tr>");
				out.println("<td> " + productOne.get_id() + " </td>");
				out.println("<td> " + productOne.get_description() + " </td>");
				out.println("<td> " + productOne.get_price() + " </td>");
				out.println("<td> " + productOne.get_type() + " </td>");
				out.println("<td><a href='productForm.jsp?action=updateProduct&productId=" + productOne.get_id()
						+ "'>Edit</a> </td>");
				out.println("<td> <form action='ProductController' method='post'>");
				out.println("<input type='hidden' value='" + productOne.get_id() + "' name='productId'>");
				out.println("<input type='hidden' name='action' value='deleteProduct' >");
				out.println("<input type='submit' value='Delete' >");
				out.println("</form></td>");
				out.println("</tr>");
			}
		%>

	</table>

	<br>
	<table class="table">
		<tr>
			<h4>Current Available Passes</h4>
		</tr>
		<br>

		<tr>
			<th scope="col">ID</th>
			<th scope="col">Duration</th>
			<th scope="col">Type</th>
			<th scope="col">Price</th>
			<th scope="col">Edit</th>
			<th scope="col">Delete</th>
		</tr>
		<%
			for (TravelPass a_pass : passList) {
				out.println("<tr>");
				out.println("<td> " + a_pass.get_id() + " </td>");
				out.println("<td> " + a_pass.get_passDuration() + " </td>");
				out.println("<td> " + a_pass.get_passType() + " </td>");
				out.println("<td> " + a_pass.get_price() + " </td>");
				out.println("<td><a href='productForm.jsp?action=updatePass&passId=" + a_pass.get_id()
						+ "'>Edit</a> </td>");
				out.println("<td> <form action='ProductController' method='post'>");
				out.println("<input type='hidden' value='" + a_pass.get_id() + "' name='passId'>");
				out.println("<input type='hidden' name='action' value='deletePass' >");
				out.println("<input type='submit' value='Delete' >");
				out.println("</form></td>");
				out.println("</tr>");
			}
		%>
	</table>

</body>
</html>
