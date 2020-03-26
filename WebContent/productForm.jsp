<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="com.kbconnect.entity.*, com.kbconnect.boundary.*" %>

<%
ProductDAO pdao = new ProductDAO();
TravelPassDAO tpdao = new TravelPassDAO();


AdminDAO adminDao = new AdminDAO();

if (session.getAttribute("adminUsername") == null) {
	response.sendRedirect("adminLogin.jsp?message=login");
    return;
}

String username = String.valueOf(session.getAttribute("adminUsername"));

Admin admin = adminDao.getUser(username);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title>Create or edit products</title>
</head>
<body class="w-100">
    <!-- form for creating a new product -->
    <%
    String action = request.getParameter("action");
    if ( action != null ) {
        switch( action ) {
            case "createPass":
                out.print("<form action='ProductController' method='post'>");
                out.print("<input type='hidden' name='action' value='createPass'>");
                out.print("<div class='form-group '>");
                out.print("<label for='duration'>Pass Duration</label>");
                out.print("<select name='duration' type='text'>");
                out.print("<option value='Daily'>Daily</option>");
                out.print("<option value='Weekly'>Weekly</option>");
                out.print("<option value='Monthly'>Monthly</option>");
                out.print("</select>");
                out.print("</div>");
                out.print("<div class='form-group '>");
                out.print("<label for='passType'>Pass Type</label>");
                out.print("<select name='passType' type='text'>");
                out.print("<option value='Adult'>Adult</option>");
                out.print("<option value='Concession'>Concession</option>");
                out.print("</select>");
                out.print("</div>");
                out.print("<div class='form-group '>");
                out.print("<label for='price'>Price</label>");
                out.print("<input type='number' step='0.01' min='1' class='w-25 form-control' name='price'>");
                out.print("</div>");
                out.print("<button type='submit' class='btn btn-primary'>Add</button>");
                out.print("</form>");
            break;

            case "updateProduct":
                int productId = Integer.parseInt(request.getParameter("productId"));
                Product editProduct = pdao.getProduct(productId);
                out.print("<form action='ProductController' method='post'>");
                out.print("<input type='hidden' name='action' value='updateProduct'>");
                out.print("<input type='hidden' name='productId' value='"+ editProduct.get_id() + "'>");
                out.print("<div class='form-group '>");
                out.print("<label for='description'>Product Desctiption</label>");
                out.print("<input type='text' class='w-25 form-control' value='" + editProduct.get_description() + "' name='description'> ");
                out.print("</div>");
                out.print("<div class='form-group '>");
                out.print("<label for='price'>Price</label>");
                out.print("<input type='number' step='0.01' min='1' class='form-control w-25' value='"+ editProduct.get_price() +"'  name='price'>");
                out.print("</div>");
                out.print("<div class='form-group '>");
                out.print("<label for='price'>Type</label>");
                out.print("<input type='text'  class='form-control w-25' value='"+ editProduct.get_type() +"' name='type'>");
                out.print("</div>");
                out.print("<button type='submit' class='btn btn-primary'>Save</button>");
                out.print("</form>");

            break;

            case "updatePass":
                int passId = Integer.parseInt(request.getParameter("passId"));
                TravelPass editPass = tpdao.getPass(passId);

                out.print("<form action='ProductController' method='post'>");
                out.print("<input type='hidden' name='action' value='updatePass'>");
                out.print("<input type='hidden' name='passId' value='" + editPass.get_id() + "'>");
                out.print("<div class='form-group '>");
                out.print("<label for='duration'>Pass Duration</label>");
                out.print("<select name='duration' type='text'>");

                out.print("<option value='" + editPass.get_passDuration()+ "' selected disabled hidden>" + editPass.get_passDuration() + "</option>");
                out.print("<option value='Daily'>Daily</option>");
                out.print("<option value='Weekly'>Weekly</option>");
                out.print("<option value='Monthly'>Monthly</option>");
                out.print("</select>");
                out.print("</div>");
                out.print("<div class='form-group '>");
                out.print("<label for='passType'>Pass Type</label>");
                out.print("<select name='passType' type='text'>");
                out.print("<option value='" + editPass.get_passType()+ "' selected disabled hidden>" + editPass.get_passType()+ "</option>");
                out.print("<option value='Adult'>Adult</option>");
                out.print("<option value='Concession'>Concession</option>");
                out.print("</select>");
                out.print("</div>");
                out.print("<div class='form-group '>");
                out.print("<label for='price'>Price</label>");
                out.print("<input type='number' step='0.01' min='1' class='w-25 form-control' value='" + editPass.get_price() + "' name='price'>");
                out.print("</div>");
                out.print("<button type='submit' class='btn btn-primary'>Save</button>");
                out.print("</form>");
            break;


            case "createProduct":
                out.print("<form action='ProductController' method='post'>");
                out.print("<input type='hidden' name='action' value='create'>");
                out.print("<div class='form-group '>");
                out.print("<label for='description'>Product Desctiption</label>");
                out.print("<input type='text' class='form-control w-25' name='description'> ");
                out.print("</div>");
                out.print("<div class='form-group '>");
                out.print("<label for='price'>Price</label>");
                out.print("<input type='number' step='0.01' min='1' class='form-control w-25' name='price'>");
                out.print("</div>");
                out.print("<div class='form-group '>");
                out.print("<label for='type'>Type</label>");
                out.print("<input type='text' class='form-control w-25' name='type'>");
                out.print("</div>");
                out.print("<button type='submit' class='btn btn-primary'>Add</button>");
                out.print("</form>");
            break;

            default:
            break;
        }
    }
    %>

    <a href="productList.jsp" class="btn btn-primary text-light">Cancel</a>
    <!-- form for editing a product -->
    <!-- form for creating a pass -->
    <!-- form for editing a pass -->

</body>
</html>
