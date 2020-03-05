<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create user here</title>
<!--  Reference from Skeleton CSS -->
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/skeleton.css">
</head>
<body>

	<h4>Register User</h4>
	<form action="UserDAOController" method="post">
		<div class="row">
			<div class="five columns">
				<label>Full Name</label> <input class="u-full-width" type="text"
					placeholder="Full name" name="fullName" required>
			</div>
			<div class="five columns">
				<label>User Name</label> <input class="u-full-width" type="text"
					placeholder="username" name="username" required>
			</div>
		</div>
		<div class="row">

			<div class="five columns">
				<label>Email</label> <input class="u-full-width" type="email"
					placeholder="test@gmail.com" name="email" required>
			</div>
			<div class="five columns">
				<label>Password</label> <input class="u-full-width" type="password"
					placeholder="at least 8 characters" pattern=".{8,}"
					title="Eight or more characters" name="password" required>
			</div>

		</div>
		<div class="row">
			<div class="five columns">
				<label>DOB</label> <input class="u-full-width" type="date"
					placeholder="yyyy-mm-dd" name="DOB">
			</div>
			<div class="five columns"></div>
		</div>

		<div class="row">
			<div class="five columns">
				<label>Street Address</label>
				<textarea rows="3" cols="50" name="address"></textarea>
			</div>

		</div>
		<input type="hidden" name="action" value="create"> <input
			class="button-primary" type="submit" value="Create User"><br>

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