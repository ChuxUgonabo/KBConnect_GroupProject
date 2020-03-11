<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kbconnect.boundary.*, com.kbconnect.entity.*" %>    
<% 
	AdminDAO adminDAO = new AdminDAO();
	AlertDAO aldao = new AlertDAO();
    if (session.getAttribute("username") == null) {
        response.sendRedirect("adminLogin.jsp?message=login");
        return;
    }
	String username = String.valueOf(session.getAttribute("username"));

    Admin user = adminDAO.getUser(username);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Alert</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>


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
                "</div>" +
                "<button type='submit' class='btn btn-primary'>Create Alert</button>" +
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
