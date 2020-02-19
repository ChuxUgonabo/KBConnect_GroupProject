<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
	<link rel="stylesheet" href="css/normalize.css">
	<link rel="stylesheet" href="css/skeleton.css">
</head>
<body>
    <%
    String message = request.getParameter("message");
    if ( message != null ) {
        switch ( message ){

            case "login":
                out.print("<h4>Please Login to proceed</h4>");
            break;

            case "invalidCredentials":
                out.print("<h4>Invalid Credentials!</h4>");
            break;

            case "loggedOut":
                out.print("<h4>You have been logged out. Please log in again to continue.</h4>");
            break;

            default:
            break;
        }

    } else {
        out.print("<h3>WELCOME TO KB CONNECT</h3>");
    }
    %>
	<form action="LoginController" method="post">
	<input type="hidden" value="login" name="action">
		<div class="row">
			<div class="five columns">
				<label>User Name</label> <input class="u-full-width" type="text"
					placeholder="Enter Username" name="username">
			</div>
		</div>
		<div class="row">
			<div class="five columns">
				<label>Password</label> <input class="u-full-width" type="password"
					placeholder="Enter Password" name="password">
			</div>
		</div>
		<input type="hidden" name="action" value="verify">
		<input class="button-primary" type="submit" value="Login">
		
		
	</form>
	<p>
	For new user,please click here to create a <A  class="button-primary" HREF="registeUser.jsp">new User.</A>
	</p>

</body>
</html>
