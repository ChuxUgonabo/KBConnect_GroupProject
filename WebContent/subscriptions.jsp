<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList, com.kbconnect.entity.*, com.kbconnect.boundary.*" %>
<%
	CommuterDAO userDao = new CommuterDAO();
    if (session.getAttribute("username") == null) {
        response.sendRedirect("login.jsp?message=login");
        return;
    }
	String username = String.valueOf(session.getAttribute("username"));

    User user = userDao.getUser(username);

    // initialize the route class
    RouteDAO rdao = new RouteDAO();
    ArrayList<Route> allRoutes = new ArrayList<>();

    // get all the routes
    allRoutes = rdao.getAllRoutes();

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title><% out.print(user.get_username()); %></title>
</head>
<body>
    <!-- main header -->
    <nav class="navbar navbar-light bg-light">
        <a class="navbar-brand" href="profile.jsp"><% out.print(user.get_username()); %></a>

         <form action="LoginController" method="post">
            <input type="hidden" value="logout" name="action">
            <input class="btn btn-primary" type="submit" value="Logout">
        </form>
    </nav>
    <div class="container">
        <h4 class="">Add a new bus route to alert subscriptions.</h4>
        <form method="post" action="SubscriptionsController">
            <div class="form-group w-50">

                <label for="routeNo">Route No.</label>
                <select class="form-control form-control-sm" name="routeNo">
                    <%
                    for (Route aRoute: allRoutes) {
                    out.println("<option value='" + aRoute.get_id() + "'>" + 
                    		aRoute.get_routeNo() + " :    " + aRoute.get_fromCity() + 
                    		"    ->    " + aRoute.get_toCity() +"</option>");
                    }
                    %>
                </select>
            </div>
            <div class="form-group w-25">
                <input class="form-control btn btn-primary" type="submit" value="Add">
            </div>
        </form>

        <h4 class="">You are subscribed to receive alerts for following bus routes.</h4>

        <ul class="list-group">

        </ul>

    </div>
</body>
</html>
