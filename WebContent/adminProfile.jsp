<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.kbconnect.entity.*, com.kbconnect.boundary.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

<%
	AdminDAO adminDao = new AdminDAO();
	CommuterDAO comuterDao = new CommuterDAO();
	ArrayList<User> listOfUsers = new ArrayList<User>();
	listOfUsers = comuterDao.getAllUsers();

	if (session.getAttribute("adminUsername") == null) {
		response.sendRedirect("adminLogin.jsp?message=login");
        return;
	}

	String username = String.valueOf(session.getAttribute("adminUsername"));

	Admin admin = adminDao.getUser(username);
%>
<title><% out.print(admin.get_username()); %></title>
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
    <hr>

    <ul class="list-group">
        <li class="list-group-item"><strong>Username: </strong><% out.print(admin.get_username()); %></li>
        <li class="list-group-item"><strong>Fullname: </strong><% out.print(admin.get_fullName()); %></li>
        <li class="list-group-item"><strong>Email: </strong><% out.print(admin.get_email()); %></li>
        <li class="list-group-item"><strong>Address: </strong><% out.print(admin.get_address()); %></li>
    
    </ul>

    <hr>

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
	    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="navbar-toggler-icon"></span>
	    </button>
	
	    <div class="collapse navbar-collapse" id="navbarSupportedContent">
	        <ul class="navbar-nav mr-auto">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Manage Products</a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="productList.jsp">List all products</a>
                        <a class="dropdown-item" href="productForm.jsp?action=createPass">Add a new Travel Pass</a>
                        <a class="dropdown-item" href="productForm.jsp?action=createProduct">Add a new Product</a>
                    </div>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Manage Commuters</a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="allCommuters.jsp">List all Commuters</a>
                        <a class="dropdown-item" href="userForm.jsp?action=createCommuter">Add a new Commuter</a>
                    </div>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Manage Orders</a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="adminOrderList.jsp">List all Orders</a>
                        <a class="dropdown-item" href="allCommuters.jsp?action=add">Add New Order</a>
                    </div>
                </li>
                
                 <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Alerts</a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="adminListAlerts.jsp">List all Alerts</a>
                        <a class="dropdown-item" href="createAlert.jsp?action=new">Send out a new Alert</a>
                    </div>
                </li>

	        </ul>
	    </div>
	</nav>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>
