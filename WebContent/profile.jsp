<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="com.kbconnect.entity.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>"${sessionScope.username}"</title>
</head>
<body>
<p>"${sessionScope.username}"</p>

<form action="LoginController" method="post">
<input type="submit" name="action" value="logout">
</form>

<% 
//User user = (User) request.getAttribute("loggeduser");

//out.println(user.get_fullName());
//out.print(user.get_address());
//out.print(user.get_email());
%>
</body>
</html>