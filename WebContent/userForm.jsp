<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="com.kbconnect.entity.*, com.kbconnect.boundary.*"%>

<%
	AdminDAO adminDao = new AdminDAO();

	ComuterDAO udao = new ComuterDAO();
if (session.getAttribute("username") == null) {
	response.sendRedirect("adminLogin.jsp?message=login");
    return;
}

String username = String.valueOf(session.getAttribute("username"));

Admin admin = adminDao.getUser(username);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<!--  Reference from Skeleton CSS -->
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/skeleton.css">
<title>Create or Edit Users</title>
</head>
<body class="w-100">
	<!-- form for creating a new user -->
	<!-- form for update the user -->
	<%
		String action = request.getParameter("action");
		if (action != null) {
			switch (action) {
			case "createCommuter":
				out.print("<form action='UserDAOController' method='post'>");
				out.print("<input type='hidden' name='action' value='create'>");
				out.print("<input type='hidden' name='authority' value='admin'>");

                   
				out.print ("<div><h4>Create a new user</h4></div>");
				out.print("<div class='row'>");
				out.print("<div class='five columns'>");
				out.print("<label>Full Name</label>");
				out.print(
						"<input class='u-full-width' type='text' placeholder='Full Name' name='fullName' required>");
				out.print("</div>");

				out.print("<div class='five columns'>");
				out.print("<label>User Name</label>");
				out.print(
						"<input class='u-full-width' type='text' placeholder='User Name' name='username' required>");
				out.print("</div>");
				out.print("</div>");

				out.print("<div class='row'>");
				out.print("<div class='five columns'>");
				out.print("<label>Email</label>");
				out.print(
						"<input class='u-full-width' type='email' placeholder='test@gmail.com' name='email' required>");
				out.print("</div>");

				out.print("<div class='five columns'>");
				out.print("<label>Password</label>");
				out.print(
						"<input class='u-full-width' type='password' placeholder='at least 8 characters' pattern='.{8,}'");
				out.print("title='Eight or more characters' name='password' required>");
				out.print("</div>");
				out.print("</div>");

				out.print("<div class='row'>");
				out.print("<div class='five columns'>");
				out.print("<label>DOB</label>");
				out.print("<input class='u-full-width' type='date' placeholder='mm/dd/yyyy' name='DOB'>");
				out.print("</div>");
				out.print("<div class='five columns'></div>");
				out.print("</div>");

				out.print("<div class='row'>");
				out.print("<div class='five columns'>");
				out.print("<label>Street Address</label>");
				out.print("<textarea rows='3' cols='50' name='address'></textarea>");
				out.print("</div>");

				out.print("</div>");

				out.print("<button type='submit' class='button-primary'>Add</button>");
				out.print("</form>");
				break;

			case "updateCommuter":
				int commuterId = Integer.parseInt(request.getParameter("commuterId"));
				User currCommuter = udao.getUser(commuterId);
				out.print("<form action='UserDAOController' method='post'>");

				out.print("<input type='hidden' name='action' value='update'>");
				out.print("<input type='hidden' name='authority' value='admin'>");
				out.print("<input type='hidden' name='userId' value='" + currCommuter.get_id() + "'>");

				out.print ("<div><h4>Edit the current user</h4></div>");
				out.print("<div class='row'>");
				out.print("<div class='five columns'>");
				out.print("<label>Full Name</label>");
				out.print(
						"<input class='u-full-width' type='text' placeholder='Full Name' name='fullName' value='"+ currCommuter.get_fullName()+"' required>");
				out.print("</div>");

				out.print("<div class='five columns'>");
				out.print("<label>User Name</label>");
				out.print(
						"<input class='u-full-width' type='text' placeholder='User Name' name='userName' value='"+ currCommuter.get_username()+"' required>");
				out.print("</div>");
				out.print("</div>");

				out.print("<div class='row'>");
				out.print("<div class='five columns'>");
				out.print("<label>Email</label>");
				out.print(
						"<input class='u-full-width' type='email' placeholder='test@gmail.com' name='email' value='"+ currCommuter.get_email()+"' required>");
				out.print("</div>");

				
				out.print("<div class='five columns'>");
				out.print("<label>DOB</label>");
				out.print("<input class='u-full-width' type='date' placeholder='mm/dd/yyyy' name='DOB' value='"+ currCommuter.get_DOB()+"'>");
				out.print("</div>");
				out.print("</div>");

				out.print("<div class='row'>");
				out.print("<div class='five columns'>");
				out.print("<label>Street Address</label>");
				out.print("<textarea rows='3' cols='50' name='address'>"+ currCommuter.get_address()+"</textarea>");
				out.print("</div>");

				out.print("</div>");

				out.print("<button type='submit' class='button-primary'>Save</button>");
				out.print("</form>");
				

				break;

			}
		}
	%>


</body>
</html>
