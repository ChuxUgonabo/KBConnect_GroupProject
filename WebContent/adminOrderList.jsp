<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.kbconnect.boundary.*, com.kbconnect.entity.*"%>

<%
	CommuterDAO commuterDao = new CommuterDAO();
	ArrayList<User> commuterList = commuterDao.getAllUsers();

	OrderDAO orderDao = new OrderDAO();
	//ArrayList<Order> orderList = orderDao.getAllOrders();

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
<title>List Of Orders</title>
</head>
<body>
	<nav class="navbar navbar-light bg-light">
		<a class="navbar-brand" href="adminProfile.jsp"> <%
 	out.print(user.get_username());
 %>
		</a>

		<form action="LoginController" method="post">
			<input type="hidden" value="admin" name="admin"> <input
				type="hidden" value="logout" name="action"> <input
				class="btn btn-primary" type="submit" value="Logout">
		</form>
	</nav>
	<table class="table">
		<thead>
			<h4>Orders Yet To Be Approved</h4>
			<tr>
				<th scope="col">Name</th>
				<th scope="col">Item</th>
				<th scope="col">Quantity</th>
				<th scope="col">Date Placed</th>
				<th scope="col">Status</th>
				<th scope="col">Approve</th>
				<th scope="col">Delete</th>

			</tr>
		</thead>

		<tbody>
			<%
			ArrayList<Order> orderList = orderDao.getAllOrders();

				for (int i = 0; i < orderList.size(); i++) {
					if (!orderList.get(i).is_approvalStatus()) {
						out.print("<tr><td>" + orderList.get(i).get_placedBy().get_fullName() + "</td>"
								+ "<td>" + orderList.get(i).get_productOrdered().get_description() + "</td>" + "<td>"
								+ orderList.get(i).get_quantity() + "</td>" + "<td>"
								+ orderList.get(i).get_transactionDate() + "</td>" + "<td>Awaiting Approval</td>"
								+ "<td><form action='OrderController' method='post'><input type='hidden' name='adminStatus' value='admin'><input type='hidden' name='userId' value='"+user.get_id()+"'><input type='hidden' name='orderId' value='"
										+ orderList.get(i).get_id() + "'><input type='submit' name='action' value='Approve'></td>"
								+ "<td><input type='submit' name='action' value='Delete'></td></form>");
						out.print("</tr>");
					}
				}
			%>


			<tr>
				<td><h4>Approved Orders</h4></td>
			</tr>
			<tr>
				<th scope="col">Name</th>
				<th scope="col">Item</th>
				<th scope="col">Quantity</th>
				<th scope="col">Date Placed</th>
				<th scope="col">Status</th>
				<th scope="col">Delete</th>

			</tr>
			<%
				for (int i = 0; i < orderList.size(); i++) {
					if(orderList.get(i).is_approvalStatus()) {
						out.print("<tr><td>" + orderList.get(i).get_placedBy().get_fullName() + "</td>"
								+ "<td>" + orderList.get(i).get_productOrdered().get_description() + "</td>" + "<td>"
								+ orderList.get(i).get_quantity() + "</td>" + "<td>"
								+ orderList.get(i).get_transactionDate() + "</td>" + "<td>Approved</td>"
								+ "<td><form action='OrderController' method='post'>"
								+ "<input type='hidden' name='adminStatus' value='admin'><input type='hidden' name='userId' value='"+user.get_id()+"'><input type='hidden' name='orderId' value='"
								+ orderList.get(i).get_id() + "'><input type='submit' name='action' value='Delete'></td></form>");
						out.print("</tr>");

					}
				}
			%>
		</tbody>
</body>
</html>