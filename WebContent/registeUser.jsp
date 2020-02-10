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
					placeholder="Full name" name="fullName">
			</div>
			<div class="five columns">
				<label>User Name</label> <input class="u-full-width" type="text"
					placeholder="username" name="username">
			</div>
		</div>
		<div class="row">

			<div class="five columns">
				<label>Email</label> <input class="u-full-width" type="email"
					placeholder="test@gmail.com" name="email">
			</div>
			<div class="five columns">
				<label>Password</label> <input class="u-full-width" type="text"
					placeholder="password" name="password">
			</div>

		</div>
		<div class="row">
			<div class="five columns">
				<label>DOB</label> <input type="text" class="u-full-width"
					name="DOB" placeholder="yy/dd/mm">
			</div>
			<div class="five columns">
				

			</div>
		</div>

		<div class="row">
			<div class="five columns">
				<label>Street Address</label>
				<textarea rows="3" cols="50" name="address"></textarea>
			</div>

		</div>
		<input type="hidden" name="action" value="create"> <input
			class="button-primary" type="submit" value="Create User">
		
	</form>


</body>
</html>