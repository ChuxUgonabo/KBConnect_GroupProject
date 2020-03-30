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
    
    // initialize the subscriptions dao
    SubscribedDAO sdao = new SubscribedDAO();
    ArrayList<SubscribedTo> allSubscriptions = new ArrayList<>();
    
    allSubscriptions = sdao.getSubscriptionForUser(user);
    

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
    
    <%
    	String message;
    	if ((message = request.getParameter("message")) != null ) {
			switch(message) {
				case "subscriptionAdded":
					int routeId = Integer.parseInt(request.getParameter("routeId"));
					Route route = rdao.getRoute(routeId);
	        		out.println("<div class='alert alert-info' role='alert'>" +
							"You are now subscribed receive alerts for route " + route.get_routeNo() + "!" +
	            			"</div>");
					break;

				default:
					break;
			}
    	}
    %>
    
    <div class="container">
        <h4 class="">Add a new bus route to alert subscriptions.</h4>
        <form method="post" action="SubscriptionController">
            <div class="form-group w-50">

                <label for="routeId">Route No.</label>
                <select class="form-control form-control-sm" name="routeId">
                    <%
                    for (Route aRoute: allRoutes) {
                    out.println("<option value='" + aRoute.get_id() + "'>" + 
                    		aRoute.get_routeNo() + " :    " + aRoute.get_fromCity() + 
                    		"    ->    " + aRoute.get_toCity() +"</option>");
                    }
                    %>
                </select>
            </div>
            <input type="hidden" name="action" value="addSubscription">
            <div class="form-group w-25">
                <input class="form-control btn btn-primary" type="submit" value="Add">
            </div>
        </form>

        <h4 class="">You are subscribed to receive alerts for following bus routes.</h4>

        <ul class="list-group">
        <%
        	if (allSubscriptions.size() < 1) {
        		out.println("<div class='alert alert-info' role='alert'>" +
        		  	"You don't have any active subscriptions!" +
        			"</div>");
        	} else {
	        	for (SubscribedTo subscription: allSubscriptions) {
	        		Route currentRoute = rdao.getRoute(subscription.get_routeId());
	        		out.println("<li class='list-group-item'>" + 
	        				currentRoute.get_routeNo() + " :    " + currentRoute.get_fromCity() + 
	                		"    ->    " + currentRoute.get_toCity() +
	                		" <form action='SubscriptionController' method='post'>"+
	                		"<input type='hidden' name='action' value='delete' />" +
	    	                "<input type='hidden' name='subscriptionId' value='"+ subscription.get_id()+"' />" +
	                		"<input type='submit' value='Delete' />" +
	                		"</form>" +
	                		"</li>");
	        	}
        	}
        %>

        </ul>

    </div>
</body>
</html>
