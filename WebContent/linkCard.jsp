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
<title>Link Card</title>
</head>
<body>
    <nav class="navbar navbar-light bg-light">
      <a class="navbar-brand" href="profile.jsp"><% out.print(user.get_username()); %></a>

         <form action="LoginController" method="post">
            <input type="hidden" value="logout" name="action">
            <input class="btn btn-primary" type="submit" value="Logout">
        </form>
    </nav>

    <div class="container">
        <div class="row">
            <form action="CardController" method="post">
                <div class="form-group">
                    <label for="cardNumber">Card Number</label>
                    <input class="form-control" type="text" placeholder="Card Number"  name="cardNumber">
                </div>
                <div class="form-group">
                    <label for="cardNumber">3 Digit Security Code</label>
                    <input class="form-control" type="password" placeholder="Security code (CVN)"  name="cvn">
                </div>
                    <input type="hidden" name="action" value="linkCard">
                <div class="form-group">
                    <input class="form-control btn btn-primary" type="submit" value="Link card">
                </div>
            </form>
        </div>
    </div>
</body>
</html>
