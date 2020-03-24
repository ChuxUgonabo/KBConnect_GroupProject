<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ page import="com.kbconnect.entity.*, com.kbconnect.boundary.*" %>
<%
	CommuterDAO userDao = new CommuterDAO();
if (session.getAttribute("username") == null) {
    response.sendRedirect("login.jsp?message=login");
}
String username = String.valueOf(session.getAttribute("username"));

User user = userDao.getUser(username);
%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

<title>Edit profile</title>
</head>
<body>
    <nav class="navbar navbar-light bg-light">
        <a class="navbar-brand" href="profile.jsp"><% out.print(user.get_username()); %></a>

         <form action="LoginController" method="post">
            <input type="hidden" value="logout" name="action">
            <input class="btn btn-primary" type="submit" value="Logout">
        </form>
    </nav>

    <h4>Edit profile</h4>
    <div class="container">
        <form action="UserDAOController" method="post">
            <div class="form-group">
                <label for="fullName">Full Name</label>
                <input class="form-control" type="text" placeholder="Full name" name="fullName" value="<% out.print(user.get_fullName()); %>" required>
            </div>
        


            <div class="form-group">
                <label>Email</label>
                <input class="form-control" type="email" placeholder="test@gmail.com" name="email" value="<% out.print(user.get_email()); %>" required>
            </div>


            <div class="form-group">
                <label>Street Address</label>
                <textarea class="form-control" rows="3" cols="50" name="address"><% out.print(user.get_address()); %></textarea>
            </div>

            <input type="hidden" name="userId" value="<% out.print(user.get_id()); %>">
            <input type="hidden" name="action" value="update">
            <input class="form-control btn-primary" type="submit" value="Save"><br>

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
    </div>
</body>
</html>
