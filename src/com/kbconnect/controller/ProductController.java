package com.kbconnect.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kbconnect.boundary.ProductDAO;
import com.kbconnect.boundary.TravelPassDAO;
import com.kbconnect.entity.Product;
import com.kbconnect.entity.TravelPass;

/**
 * Servlet implementation class ProductController
 */
@WebServlet("/ProductController")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       ProductDAO pdao = new ProductDAO(); 
       TravelPassDAO tpdao = new TravelPassDAO(); 

       switch (request.getParameter("action")) {

           case "create":
               // get the new product info
               String description = request.getParameter("description");
               String type = request.getParameter("type");
               double price = Double.parseDouble(request.getParameter("price"));

               // create a new product instance
               Product newProduct = new Product();

               // populate the product
               newProduct.set_description(description);
               newProduct.set_type(type);
               newProduct.set_price(price);

               // save the product in the database
               pdao.createProduct(newProduct);
               response.sendRedirect("productList.jsp");
               break;

           case "createPass":
               // get the new pass info
               String duration = request.getParameter("duration");
               String passType = request.getParameter("passType");
               double passPrice = Double.parseDouble(request.getParameter("price"));

               // create a new pass instance
               TravelPass newPass = new TravelPass();

               // populate the pass
               newPass.set_passDuration(duration);
               newPass.set_passType(passType);
               newPass.set_price(passPrice);

               // save the pass in the database
               tpdao.createPass(newPass);
               response.sendRedirect("productList.jsp");
               break;

           case "updatePass":

               int passId = Integer.parseInt(request.getParameter("passId"));

               // get the new pass info
               String editDuration = request.getParameter("duration");
               String editPassType = request.getParameter("passType");
               double editPrice = Double.parseDouble(request.getParameter("price"));

               // create a new pass instance
               TravelPass editPass = tpdao.getPass(passId);

               // populate the pass
               editPass.set_passDuration(editDuration);
               editPass.set_passType(editPassType);
               editPass.set_price(editPrice);

               // save the pass in the database
               tpdao.updatePass(editPass);
               response.sendRedirect("productList.jsp");
               break;

           case "updateProduct":
               // get the info for product to be updated
               int productId = Integer.parseInt(request.getParameter("productId"));
               String newDescription = request.getParameter("description");
               String newType = request.getParameter("type");
               double newPrice = Double.parseDouble(request.getParameter("price"));

               // create a new product instance
               Product updatedProduct = pdao.getProduct(productId);

               // populate the product
               updatedProduct.set_description(newDescription);
               updatedProduct.set_type(newType);
               updatedProduct.set_price(newPrice);

               // save the product in the database
               pdao.updateProduct(updatedProduct);
               response.sendRedirect("productList.jsp");
               break;

            case "deleteProduct":
               // get the product id to be deleted
               int deleteId = Integer.parseInt(request.getParameter("productId"));
               
               // retrieve the product to be deleted
               Product deletedProduct = pdao.getProduct(deleteId);

               // delete the product
               pdao.deleteProduct(deletedProduct);
               response.sendRedirect("productList.jsp");
               break;

            case "deletePass":
               // get the product id to be deleted
               int passDeleteId = Integer.parseInt(request.getParameter("passId"));
               
               // retrieve the product to be deleted
               TravelPass deletedPass = tpdao.getPass(passDeleteId);

               // delete the product
               tpdao.deletePass(deletedPass);
               response.sendRedirect("productList.jsp");
               break;

       }


	}

}
