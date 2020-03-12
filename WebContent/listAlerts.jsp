<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kbconnect.entity.*, com.kbconnect.boundary.*, java.util.*" %>
<%

	ComuterDAO userDao = new ComuterDAO();
	AdminDAO adao = new AdminDAO();
	AlertDAO aldao = new AlertDAO();
    if (session.getAttribute("username") == null) {
        response.sendRedirect("login.jsp?message=login");
        return;
    }
	String username = String.valueOf(session.getAttribute("username"));

    // if someone is logged in but he is not an admin, then redirect to the forbidden page
    if (adao.getUser(username) == null) {
        response.sendRedirect("403.jsp?message=forbidden");
        return;
    }

    Admin admin = adao.getUser(username);
    ArrayList<Alert>  allAlerts = aldao.getAllAlerts();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title>Alerts</title>
</head>
<body>
<nav class="navbar navbar-light bg-light">
  <a class="navbar-brand" href="adminProfile.jsp"><% out.print(admin.get_username()); %></a>

     <form action="LoginController" method="post">
        <input type="hidden" value="admin" name="admin">
        <input type="hidden" value="logout" name="action">
        <input class="btn btn-primary" type="submit" value="Logout">
    </form>
</nav>
    <div class="container">

        <table class="table">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Short Description</th>
                    <th scope="col">Date Posted</th>
                    <th scope="col">Last Updated</th>
                </tr>
            </thead>
        <tbody>

            <%

            for( int i=0;i<allAlerts.size(); i++) {
            out.print("<tr>" +
                "<th>"+ (i + 1) + "</th>" + 
                "<td>" + allAlerts.get(i).get_shortDescription() + "</td>" +
                "<td>" + allAlerts.get(i).get_dateCreated()+ "</td>" +
                "<td>" + allAlerts.get(i).get_dateOfLastUpdate()+ "</td>" +
                "</tr>" 
                );
            }
        %>
</tbody>
        </table>
    </div>

    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>
