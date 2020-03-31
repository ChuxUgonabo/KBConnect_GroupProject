<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page
	import="com.kbconnect.entity.*, com.kbconnect.boundary.*, java.util.*"%>

<%
	CommuterDAO userDao = new CommuterDAO();
	if (session.getAttribute("username") == null) {
		response.sendRedirect("login.jsp?message=login");
		return;
	}
	String username = String.valueOf(session.getAttribute("username"));

	User user = userDao.getUser(username);

	AlertDAO alertDao = new AlertDAO();
	int alertId = Integer.parseInt(request.getParameter("alertId"));
	Alert thisAlert = alertDao.getAlert(alertId);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<title>
<%
	out.println(thisAlert.get_shortDescription());
%>
</title>
</head>
<body>

	<nav class="navbar navbar-light bg-light">
		<a class="navbar-brand" href="profile.jsp"> <%
 	out.print(user.get_username());
 %>
		</a>

		<form action="LoginController" method="post">
			<input type="hidden" value="logout" name="action"> <input
				class="btn btn-primary" type="submit" value="Logout">
		</form>
	</nav>
	<br>
	<div class="card">
		<h5 class="card-header">
			<%
				out.println(thisAlert.get_shortDescription());
			%>
		</h5>
		<div class="card-body">
			<p class="card-text">
				<%
					out.println(thisAlert.get_description());
				%>
			</p>
			<br>
			<span class="form-text text-muted">Posted On: <% out.println(thisAlert.get_dateCreated()); %></span>
			<br>
			<span class="form-text text-muted">Last Updated: <% out.println(thisAlert.get_dateOfLastUpdate()); %></span>
			<br>
			<a href="listAlerts.jsp" class="btn btn-primary">Go Back</a>
		</div>
	</div>
</body>
</html>