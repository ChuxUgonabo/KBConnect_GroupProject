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
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title><% out.print(user.get_username()); %></title>
</head>
<body>
	<% out.print(user.get_username()); %>
	<form action="LoginController" method="post">
		<input type="hidden" value="admin" name="admin">
        <input type="hidden" value="logout" name="action">
		<input class="btn btn-primary" type="submit" value="Logout">
	</form>

    <a href="adminProfile.jsp" class="btn btn-primary">Profile Page</a>
	

    <h4>Cards</h4>
	<table>
		<tr>
			<thead>
				<th>ID</th>
				<th>Description</th>
				<th>Price</th>
				<th>Type</th>	
				<th>Edit</th>	
				<th>Delete</th>	
			</thead>
		</tr>
		<%
			for(Product productOne : productList){
				out.println("<tr>");
				out.println("<td> "+productOne.get_id()+ " </td>");
				out.println("<td> "+productOne.get_description()+ " </td>");
				out.println("<td> "+productOne.get_price()+ " </td>");
				out.println("<td> "+productOne.get_type()+ " </td>");
                out.println("<td><a href='productForm.jsp?action=updateProduct&productId=" +productOne.get_id()+ "'>Edit</a> </td>");
                out.println("<td> <form action='ProductController' method='post'>");
                out.println("<input type='hidden' value='"+productOne.get_id()+ "' name='productId'>");
                out.println("<input type='hidden' name='action' value='deleteProduct' >");
                out.println("<input type='submit' value='Delete' >");
                out.println("</form></td>");
				out.println("</tr>");
			}
		%>
	</table>

    <h4>Passes</h4>
	<table>
		<tr>
			<thead>
				<th>ID</th>
				<th>Duration</th>
				<th>Type</th>
				<th>Price</th>	
				<th>Edit</th>	
				<th>Delete</th>	
			</thead>
		</tr>
		<%
			for(TravelPass a_pass : passList){
				out.println("<tr>");
				out.println("<td> "+a_pass.get_id()+ " </td>");
				out.println("<td> "+a_pass.get_passDuration()+ " </td>");
				out.println("<td> "+a_pass.get_passType()+ " </td>");
				out.println("<td> "+a_pass.get_price()+ " </td>");
                out.println("<td><a href='productForm.jsp?action=updatePass&passId=" +a_pass.get_id()+ "'>Edit</a> </td>");
                out.println("<td> <form action='ProductController' method='post'>");
                out.println("<input type='hidden' value='"+a_pass.get_id()+ "' name='passId'>");
                out.println("<input type='hidden' name='action' value='deletePass' >");
                out.println("<input type='submit' value='Delete' >");
                out.println("</form></td>");
				out.println("</tr>");
			}
		%>
	</table>

</body>
</html>
