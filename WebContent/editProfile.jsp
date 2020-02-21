<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ page import="com.kbconnect.entity.*, com.kbconnect.boundary.*" %>
<%
	ComuterDAO userDao = new ComuterDAO();
if (session.getAttribute("username") == null) {
    response.sendRedirect("login.jsp?message=login");
}
String username = String.valueOf(session.getAttribute("username"));

User user = userDao.getUser(username);
%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/skeleton.css">

<title>Edit profile</title>
</head>
<body>
    <p><% out.print(user.get_username()); %></p>

    <form action="LoginController" method="post">
    <input class="btn btn-primary" type="submit" name="action" value="logout">
    </form>
    <h4>Edit profile</h4>
    <form action="UserDAOController" method="post">
		<div class="row">
			<div class="five columns">
				<label>Full Name</label> <input class="u-full-width" type="text" placeholder="Full name" name="fullName" value="<% out.print(user.get_fullName()); %>" required>
			</div>
			<div class="five columns">
				<label>User Name</label> <input class="u-full-width" type="text" placeholder="username" name="username" value="<% out.print(user.get_username()); %>" required>
			</div>
		</div>
		<div class="row">

			<div class="five columns">
				<label>Email</label> <input class="u-full-width" type="email" placeholder="test@gmail.com" name="email" value="<% out.print(user.get_email()); %>" required>
			</div>

			<div class="five columns">
				<label>DOB</label> <input class="u-full-width" type="date" placeholder="mm/dd/yyyy" value="<% out.print(user.get_DOB()); %>" name="DOB">

			</div>
			<div class="five columns"></div>
		</div>

		<div class="row">
			<div class="five columns">
				<label>Street Address</label>
                <textarea rows="3" cols="50" name="address"><% out.print(user.get_address()); %></textarea>
			</div>

		</div>
        <input type="hidden" name="userId" value="<% out.print(user.get_id()); %>">
		<input type="hidden" name="action" value="update"> <input
			class="button-primary" type="submit" value="Save"><br>

		<%
			if (request.getParameter("error") == null) {
              
			} else if(request.getParameter("error").equals("same")) {
				out.print("<h6 style=\"color:red;\">" + "User Name has rleady existed, please input again..."
						+ "</h6>");
			}else{
				
				out.print("<h6 style=\"color:red;\">" + "Invalid user name, it needs at least 2 characters with 'a-z0-9_-'"
						+ "</h6>");
			}
		%>





	</form>
</body>
</html>
