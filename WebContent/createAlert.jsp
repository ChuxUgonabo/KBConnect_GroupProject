<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kbconnect.boundary.*, com.kbconnect.entity.*, java.util.ArrayList" %>    
<% 
	AdminDAO adminDAO = new AdminDAO();
	AlertDAO aldao = new AlertDAO();
    if (session.getAttribute("adminUsername") == null) {
        response.sendRedirect("adminLogin.jsp?message=login");
        return;
    }
	String username = String.valueOf(session.getAttribute("adminUsername"));

    Admin admin = adminDAO.getUser(username);

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
<title>Create Alert</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
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

    <% 

    String action = request.getParameter("action");
    if (action != null) {

        switch (action) {

            case "new":

                out.print(
                "<div class='container'>" + 
                "<div class='card m-auto w-75' >" +
                "<div class='card-body'>" +
                "<form action='AlertController' method='post'>" +
                "<input type='hidden' name='action' class='form-control' value='sendAlert'>" +
                "<div class='form-group'>" +
                "<label for='short-description'>Short Description</label>" +
                "<input type='text' name='shortDescription' class='form-control' id='short-description'>" +
                "</div>" +
                "<div class='form-group'>" +
                "<label for='description'>Detail</label>" +
                "<textarea class='form-control' id='detail' name='description'>" +
                "</textarea>" +
                "</div>" +
                "<label for='routeId'>Route No.</label>" + 
                "<div class='form-group'>" + 
                "<select class='form-control form-control-sm' name='routeId'>");
                for (Route aRoute: allRoutes) {
                    out.println("<option value='" + aRoute.get_id() + "'>" + 
                    		aRoute.get_routeNo() + " :    " + aRoute.get_fromCity() + 
                    		"    ->    " + aRoute.get_toCity() +"</option>");
                    }
                out.println("</select>" +
                "</div>" + 
                "<button type='submit' class='btn btn-primary'>Create Alert</button>" +
                "</form>" +
                "</div>" +
                "</div>" +
                "</div>" 
                );
                break;

            case "update":

                Integer alertId = Integer.parseInt(request.getParameter("alertId"));

                if (alertId.equals(null)) {
                    response.sendRedirect("listAlerts.jsp");
                    break;
                }

                Alert alert = aldao.getAlert(alertId);
                out.print(
                "<div class='container'>" + 
                "<div class='card m-auto w-75' style=''>" +
                "<div class='card-body'>" +
                "<form action='AlertController' method='post'>" +
                "<input type='hidden' name='action' class='form-control' value='updateAlert'>" +
                "<input type='hidden' value='" + alert.get_id() + "' name='alertId'>" +
                "<div class='form-group'>" +
                "<label for='short-description'>Short Description</label>" +
                "<input type='text' name='shortDescription' class='form-control' id='short-description' value='" + alert.get_shortDescription() + "'>" +
                "</div>" +
                "<div class='form-group'>" +
                "<label for='description'>Detail</label>" +
                "<textarea class='form-control' id='detail' name='description'>" +
                alert.get_description() + 
                "</textarea>" +
                "<label for='routeId'>Route No.</label>" + 
                "<div class='form-group'>" +
                "<select class='form-control form-control-sm' name='routeId'>");
                for (Route aRoute: allRoutes) {
                    out.println("<option value='" + aRoute.get_id() + "'>" + 
                    		aRoute.get_routeNo() + " :    " + aRoute.get_fromCity() + 
                    		"    ->    " + aRoute.get_toCity() +"</option>");
                    }
                out.println("</select>" +
                "</div>" +
                "<button type='submit' class='btn btn-primary'>Update Alert</button>" +
                "</form>" +
                "</div>" +
                "</div>" +
                "</div>" 
                );
                break;

            default:
                response.sendRedirect("listAlerts.jsp");
                break;
        }

    } else {
        response.sendRedirect("listAlerts.jsp");
    }
    %>

</body>
</html>
