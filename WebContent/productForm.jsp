<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="com.kbconnect.entity.*, com.kbconnect.boundary.ProductDAO" %>

<%
ProductDAO pdao = new ProductDAO();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title>Create or edit products</title>
</head>
<body>
    <!-- form for creating a new product -->
    <%
    String action = request.getParameter("action");
    if ( action != null ) {
        switch( action ) {
            case "createPass":
                out.print("<form action='ProductController' method='post'>");
                out.print("<input type='hidden' name='action' value='createPass'>");
                out.print("<input type='hidden' name='type' value='pass'>");
                out.print("<div class='form-group'>");
                out.print("<label for='duration'>Pass Duration</label>");
                out.print("<select name='duration' type='text'>");
                out.print("<option value='daily'>Daily</option>");
                out.print("<option value='weekly'>Weekly</option>");
                out.print("<option value='monthly'>Monthly</option>");
                out.print("</select>");
                out.print("<input type='text' class='form-control' name='description'> ");
                out.print("</div>");
                out.print("<div class='form-group form-check'>");
                out.print("<label for='price'>Price</label>");
                out.print("<input type='number' step='0.01' min='1' class='form-control' name='price'>");
                out.print("</div>");
                out.print("<button type='submit' class='btn btn-primary'>Create</button>");
                out.print("</form>");
            break;

            case "updateProduct":
            int productId = Integer.parseInt(request.getParameter("id"));
                Product editProduct = pdao.getProduct(productId);
                out.print("<form action='ProductController' method='post'>");
                out.print("<input type='hidden' name='action' value='create'>");
                out.print("<input type='hidden' name='type' value='card'>");
                out.print("<div class='form-group'>");
                out.print("<label for='description'>Product Desctiption</label>");
                out.print("<input type='text' class='form-control' value=" + editProduct.get_description() + "name='description'> ");
                out.print("</div>");
                out.print("<div class='form-group form-check'>");
                out.print("<label for='price'>Price</label>");
                out.print("<input type='number' step='0.01' min='1' class='form-control' name='price'>");
                out.print("</div>");
                out.print("<button type='submit' class='btn btn-primary'>Create</button>");
                out.print("</form>");

            break;

            case "updatePass":
            int passId = Integer.parseInt(request.getParameter("passId"));
            break;


            default:
                out.print("<form action='ProductController' method='post'>");
                out.print("<input type='hidden' name='action' value='create'>");
                out.print("<input type='hidden' name='type' value='card'>");
                out.print("<div class='form-group'>");
                out.print("<label for='description'>Product Desctiption</label>");
                out.print("<input type='text' class='form-control' name='description'> ");
                out.print("</div>");
                out.print("<div class='form-group form-check'>");
                out.print("<label for='price'>Price</label>");
                out.print("<input type='number' step='0.01' min='1' class='form-control' name='price'>");
                out.print("</div>");
                out.print("<button type='submit' class='btn btn-primary'>Create</button>");
                out.print("</form>");
            break;
        }
    }
    %>

    <!-- form for editing a product -->
    <!-- form for creating a pass -->
    <!-- form for editing a pass -->

</body>
</html>
