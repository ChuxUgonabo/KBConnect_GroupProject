package com.kbconnect.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kbconnect.boundary.CommuterDAO;
import com.kbconnect.boundary.OrderDAO;
import com.kbconnect.boundary.ProductDAO;
import com.kbconnect.entity.Order;
import com.kbconnect.entity.Product;
import com.kbconnect.entity.User;

/**
 * Servlet implementation class OrderController
 */
@WebServlet("/OrderController")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);

		OrderDAO ordrDao = new OrderDAO();
		ProductDAO productDao = new ProductDAO();
		CommuterDAO commuterDao = new CommuterDAO();
		
		

		switch (request.getParameter("action")) {

		case "add":
			// collect data from user
			int qty = Integer.parseInt(request.getParameter("quantity"));
			Product currentProduct = productDao.getProduct(Integer.parseInt(request.getParameter("productId")));
			User currentCommuter = commuterDao.getUser(Integer.parseInt(request.getParameter("userId")));
			long millis = System.currentTimeMillis();
			Date transactionDate = new Date(millis);
			
			// create new order
			Order newOrder = new Order(qty, transactionDate,currentProduct, currentCommuter);
			
			// Add order to dateBase
			ordrDao.createOrder(newOrder);
			response.sendRedirect("placeOrder.jsp");
			break;
			
		case "Delete":			
			int orderId = Integer.parseInt(request.getParameter("orderId"));
			Order currOrder = ordrDao.getOrder(orderId);
			ordrDao.deleteOrder(currOrder);
			response.sendRedirect("placeOrder.jsp");
			break;
			
		case "-":
			orderId = Integer.parseInt(request.getParameter("orderId"));
			currOrder = ordrDao.getOrder(orderId);
			int currQtyy = currOrder.get_quantity()-1;
			currOrder.set_quantity((currQtyy));
			ordrDao.updateOrder(currOrder);
			response.sendRedirect("placeOrder.jsp");
			break;
			
		case "+":
			orderId = Integer.parseInt(request.getParameter("orderId"));
			currOrder = ordrDao.getOrder(orderId);
			int currQty = currOrder.get_quantity()+1;
			currOrder.set_quantity((currQty));
			ordrDao.updateOrder(currOrder);
			response.sendRedirect("placeOrder.jsp");
			break;

		}

	}

}
