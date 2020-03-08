<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.kbconnect.entity.*, com.kbconnect.boundary.*" %>
<%

	ComuterDAO userDao = new ComuterDAO();
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
    <p><% out.print(user.get_username()); %></p>

    <form action="LoginController" method="post">
    <input class="btn btn-primary" type="submit" name="action" value="logout">
    </form>

    <hr>

    <ul class="list-group">
        <li class="list-group-item"><strong>Username: </strong><% out.print(user.get_username()); %></li>
        <li class="list-group-item"><strong>Fullname: </strong><% out.print(user.get_fullName()); %></li>
        <li class="list-group-item"><strong>Email: </strong><% out.print(user.get_email()); %></li>
        <li class="list-group-item"><strong>Address: </strong><% out.print(user.get_address()); %></li>
        <%
            if (user.get_cardNumber() != null) {
                out.print("<li class='list-group-item'><strong>Linked Card: </strong>" + user.get_cardNumber() + "</li>");
            } else {
                out.print("<li class='list-group-item'><strong>Linked Card: </strong>None</li>");
            }
        %>
    </ul>

    <hr>

    <button class="btn btn-primary"><a class="text-light" href="editProfile.jsp">Edit Profile</a></button>
    <button class="btn btn-primary"><a class="text-light" href="linkCard.jsp">Link Card</a></button>

</body>
</html>
