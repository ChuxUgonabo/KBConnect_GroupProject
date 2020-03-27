<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="com.kbconnect.entity.*, com.kbconnect.boundary.*, java.util.*"%>

<%
	CommuterDAO userDao = new CommuterDAO();
	AdminDAO adao = new AdminDAO();
	ProductDAO productDao = new ProductDAO();
	OrderDAO orderDao = new OrderDAO();

	if (session.getAttribute("username") == null) {
		response.sendRedirect("login.jsp?message=login");
		return;
	}
	String username = String.valueOf(session.getAttribute("username"));

	// if someone is logged in but he is not an admin, then redirect to the forbidden page

	Admin admin = adao.getUser(username);
	User user = userDao.getUser(username);
	if (admin == null && user == null) {
		response.sendRedirect("login.jsp");
		return;
	}
	ArrayList<Product> allProducts = productDao.getAllProducts();
	ArrayList<Order> allCommuterOrders = orderDao.getAllUserOrders(user.get_id());
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<title>Buy Products</title>
</head>
<body>
	<nav class="navbar navbar-light bg-light">
		<a class="navbar-brand" href="profile.jsp"> <%
 	out.print(user.get_username());
 %>
		</a>

		<form action="LoginController" method="post">
            <input type="hidden" value="logout" name="action">
            <input class="btn btn-primary" type="submit" value="Logout">
        </form>
	</nav>

	<div class="container">
			<h3>Available Products</h3>
		
		<table class="table">
			<thead>
				<tr>
					<th scope="col">Description</th>
					<th scope="col">Price</th>
					<th scope="col">Type</th>
					<th scope="col">Quantity</th>
				</tr>
			</thead>

			<tbody>
				<%
					for (int i = 0; i < allProducts.size(); i++) {
						out.print("<tr>");
						out.print("<td>" + allProducts.get(i).get_description() + "</td>" + "<td>"
								+ allProducts.get(i).get_price() + "</td>" + "<td>" + allProducts.get(i).get_type() + "</td>"
								+ "<td><form action='OrderController' method='post'>"
								+ "<input type='number' name='quantity'  min=1 Required/></td>");
						out.print("<input type='hidden' name='productId' value='" + allProducts.get(i).get_id() + "'/>");
						out.print("<input type='hidden' name='userId' value='" + user.get_id() + "'/>");
						out.print("<input type='hidden' name='action' value='add'/>");
						out.print("<td><input type='submit' value='+' /></td>");
						out.print("</form></tr>");

					}
				%>

			</tbody>

		</table>
		<hr>


		<table class="table">
				
				<%
					if (allCommuterOrders.size() >= 1) {
						out.print("<thead><tr><th scope='col'><h4>"+ user.get_fullName()	+"'s Cart <th scope='col'>Price</th><th scope='col'>Status</th><th scope='col'>Quantity</th></tr></thead><tbody>");

						double totalPrice = 0;
						int totalQuantity = 0;
						for (int i = 0; i < allCommuterOrders.size(); i++) {
							double price = (allCommuterOrders.get(i).get_productOrdered().get_price()
									* allCommuterOrders.get(i).get_quantity());
							out.print("<tr>");
							out.print("<td>" + allCommuterOrders.get(i).get_productOrdered().get_description() + "</td>"
									+ "<td>" + price + "</td>");
							if(allCommuterOrders.get(i).is_approvalStatus()){
								out.print("<td>Approved</td>");
							}else{
								out.print("<td>Awaiting Approval</td>");
							}
							out.print("<td><form action='OrderController' method='post'><input type='submit' name='action' value='-'>"
									+"   "+allCommuterOrders.get(i).get_quantity()
									+ "  <input type='hidden' name='orderId' value='" + allCommuterOrders.get(i).get_id() + "'><input type='submit' name='action' value='+'></td>"
									+ "<td><input type='submit' name='action' value='Delete'</td></form>");
							out.print("</tr>");
							
							totalPrice += price;
							totalQuantity += allCommuterOrders.get(i).get_quantity();
						}
						out.print("<tr><td></td><td>"+totalPrice+"</td><td></td><td>"+totalQuantity+"</td><td><h4>Total<h4></td></tr>");
						out.print("<tr><td><h3> Subtotal (" + allCommuterOrders.size() + " Products) $" + totalPrice + "</h3></td><td><h3><form action='' method='' > <input type='submit' class='btn btn-primary' value='Proceed to Checkout'></form></h3></td></tr>");
					}else{
						out.print("<thead><tr<th scope='col'></thead><tbody>");
					}
				%>

			</tbody>
		</table>

	</div>
</body>
</html>



