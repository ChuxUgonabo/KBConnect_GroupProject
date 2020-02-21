package com.kbconnect.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kbconnect.boundary.ProductDAO;
import com.kbconnect.entity.Product;

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
               break;

           case "update":
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
               break;

            case "delete":
               // get the product id to be deleted
               int deleteId = Integer.parseInt(request.getParameter("productId"));
               
               // retrieve the product to be deleted
               Product deletedProduct = pdao.getProduct(deleteId);

               // delete the product
               pdao.deleteProduct(deletedProduct);
               break;

       }


	}

}
