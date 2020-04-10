<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.kbconnect.boundary.*, com.kbconnect.entity.*"%>

<%
	CommuterDAO commuterDao = new CommuterDAO();
	ProductDAO productDao = new ProductDAO();
	OrderDAO orderDao = new OrderDAO();
	int currentUserId;
	//ArrayList<Order> orderList = orderDao.getAllOrders();

	AdminDAO userDao = new AdminDAO();
	if (session.getAttribute("adminUsername") == null) {
		response.sendRedirect("adminLogin.jsp?message=login");
		return;
	}
	String username = String.valueOf(session.getAttribute("adminUsername"));

	User admin = userDao.getUser(username);
	if (admin == null) {
		response.sendRedirect("adminLogin.jsp?message=login");
		return;
	}
	//if (session.getAttribute("currentUserId") != null) {
		//currentUserId = (int)session.getAttribute("currentUserId");
//	}else{
	//currentUserId = Integer.parseInt(request.getParameter("userId"));
	//}
	currentUserId = Integer.parseInt(request.getParameter("userId"));

	User user = commuterDao.getUser(currentUserId);

	ArrayList<Product> allProducts = productDao.getAllProducts();
	ArrayList<Order> allCommuterOrders = orderDao.getAllUserOrders(currentUserId);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<title>create new order</title>
</head>
<body>
	<nav class="navbar navbar-light bg-light">
		<a class="navbar-brand" href="adminProfile.jsp"> <%
 	out.print(admin.get_username());
 %>
		</a>

		<form action="LoginController" method="post">
			<input type="hidden" value="admin" name="admin"> <input
				type="hidden" value="logout" name="action"> <input
				class="btn btn-primary" type="submit" value="Logout">
		</form>
	</nav>

	<div class="container">
		<h2 align='center'>
			Manage
			<%
			out.print(user.get_fullName());
		%>'s Orders
		</h2>
		<br>
		<h4>Available Products</h4>

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
						out.print("<input type='hidden' name='productId' value='" + allProducts.get(i).get_id() + "'>");
						out.print("<input type='hidden' name='userId' value='" + user.get_id() + "'>");
						out.print("<input type='hidden' name='adminId' value='"+ admin.get_id()+ "'>"
								+"<input type='hidden' name='manageUserOrder' value='currentUser'><input type='hidden' name='adminStatus' value='admin'>"
										+ "<input type='hidden' name='action' value='add'/>");
						out.print("<td><input type='submit' value='Add to cart' /></td>");
						out.print("</form></tr>");

					}
				%>

			</tbody>

		</table>
		<hr>
		<table class="table">

			<%   	if (allCommuterOrders.size() >= 1) {
					out.print("<thead><tr><th scope='col'><h4>" + user.get_fullName()
							+ "'s Current Cart <th scope='col'>Price</th><th scope='col'>Status</th><th scope='col'>Quantity</th></tr></thead><tbody>");

					double totalPrice = 0;
					int totalQuantity = 0;
					for (int i = 0; i < allCommuterOrders.size(); i++) {
						double price = (allCommuterOrders.get(i).get_productOrdered().get_price()
								* allCommuterOrders.get(i).get_quantity());
						out.print("<tr>");
						out.print("<td>" + allCommuterOrders.get(i).get_productOrdered().get_description() + "</td>"
								+ "<td>" + price + "</td>");
						if (allCommuterOrders.get(i).is_approvalStatus()) {
							out.print("<td>Approved</td>");
						} else {
							out.print("<td>Awaiting Approval</td>");
						}
						out.print(
								"<td><form action='OrderController' method='post'><input type='submit' name='action' value='-'>"
										+ "   " + allCommuterOrders.get(i).get_quantity()
										+ "  <input type='hidden' name='orderId' value='"+ allCommuterOrders.get(i).get_id()+ "'>"
										+ "<input type='hidden' name='adminId' value='"+ admin.get_id()+ "'>"
										+ "<input type='hidden' name='userId' value='"+ user.get_id() + "'>"
										+ "<input type='submit' name='action' value='+'><input type='hidden' name='adminStatus' value='admin'></td><td><input type='submit' name='action' value='Approve'></td>"
										+ "<td><input type='hidden' name='manageUserOrder' value='currentUser'><input type='submit' name='action' value='Remove'></td></form>");
						out.print("</tr>");
						totalPrice += price;
						totalQuantity += allCommuterOrders.get(i).get_quantity();
					}
					out.print("<tr><td></td><td>" + totalPrice + "</td><td></td><td>" + totalQuantity
							+ "</td><td><h4>Total<h4></td></tr>");
					out.print("<tr><td><h3> Subtotal (" + allCommuterOrders.size() + " Products) $" + totalPrice
							+ "</h3></td></tr>");
				} else {
					out.print("<thead><tr><th scope='col'><h4>No Ordrers At The Moment<h4></th></tr></thead><tbody>");
				}
			%>

			</tbody>
		</table>

	</div>
</body>
</html>