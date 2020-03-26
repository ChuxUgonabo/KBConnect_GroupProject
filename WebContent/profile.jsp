<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.kbconnect.entity.*, com.kbconnect.boundary.*" %>
<%
	CommuterDAO userDao = new CommuterDAO();
    if (session.getAttribute("username") == null) {
        response.sendRedirect("login.jsp?message=login");
        return;
    }
	String username = String.valueOf(session.getAttribute("username"));

    User user = userDao.getUser(username);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title><% out.print(user.get_username()); %></title>
</head>
<body>
    <nav class="navbar navbar-light bg-light">
        <a class="navbar-brand" href="profile.jsp"><% out.print(user.get_username()); %></a>

         <form action="LoginController" method="post">
            <input type="hidden" value="logout" name="action">
            <input class="btn btn-primary" type="submit" value="Logout">
        </form>
    </nav>

    <hr>

    <ul class="list-group">
        <li class="list-group-item"><strong>Username: </strong><% out.print(user.get_username()); %></li>
        <li class="list-group-item"><strong>Fullname: </strong><% out.print(user.get_fullName()); %></li>
        <li class="list-group-item"><strong>Email: </strong><% out.print(user.get_email()); %></li>
        <li class="list-group-item"><strong>Address: </strong><% out.print(user.get_address()); %></li>
        <%
            if (user.get_cardNumber() == null) {
                out.print("<li class='list-group-item'><strong>Linked Card: </strong>None</li>");
            } else {
                out.print("<li class='list-group-item'><strong>Linked Card: </strong>" + user.get_cardNumber() + "</li>");
            }
        %>
    </ul>

    <hr>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link text-primary" href="editProfile.jsp">Edit Profile</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-primary" href="linkCard.jsp">Link Card</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link text-primary" href="listAlerts.jsp">Show Alerts</a>
            </li>
            
            <li class="nav-item dropdown">
                <a class="nav-link text-primary" href="placeOrder.jsp">Place Order</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link text-primary" href="subscriptions.jsp">Alert Subscriptions</a>
            </li>
        </ul>
    </div>
</nav>

</body>
</html>
