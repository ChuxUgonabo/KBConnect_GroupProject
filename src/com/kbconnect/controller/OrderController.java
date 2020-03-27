package com.kbconnect.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kbconnect.boundary.AdminDAO;
import com.kbconnect.boundary.CommuterDAO;
import com.kbconnect.boundary.OrderDAO;
import com.kbconnect.boundary.ProductDAO;
import com.kbconnect.entity.Admin;
import com.kbconnect.entity.Alert;
import com.kbconnect.entity.Order;
import com.kbconnect.entity.Product;
import com.kbconnect.entity.User;

/**
 * Servlet implementation class OrderController
 */
@WebServlet("/OrderController")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmailController emailSender;

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

		// messages to show to the user
		String message = "";
		// Initialize order DAO
		OrderDAO ordrDao = new OrderDAO();
		// Initialize product DAO
		ProductDAO productDao = new ProductDAO();
		// Initialize commuter DAO
		CommuterDAO commuterDao = new CommuterDAO();
		// Initialize admin DAO
		AdminDAO adminDao = new AdminDAO();
		// initialize order id // which you would need to manipulate orders
		int orderId;
		// initialize current order
		Order currOrder;
		String action = request.getParameter("action");

		switch (action) {

		case "add":
			// collect data from user
			int qty = Integer.parseInt(request.getParameter("quantity"));
			Product currentProduct = productDao.getProduct(Integer.parseInt(request.getParameter("productId")));
			User currentCommuter = commuterDao.getUser(Integer.parseInt(request.getParameter("userId")));
			long millis = System.currentTimeMillis();
			Date transactionDate = new Date(millis);

			// create new order
			Order newOrder = new Order(qty, transactionDate, currentProduct, currentCommuter, false);

			// Add order to dateBase
			ordrDao.createOrder(newOrder);
			response.sendRedirect("placeOrder.jsp");
			break;

		case "Delete":
			// collect data from user
			orderId = Integer.parseInt(request.getParameter("orderId"));
			currOrder = ordrDao.getOrder(orderId);
			// delete current order from DB
			ordrDao.deleteOrder(currOrder);
			String adminStatus = request.getParameter("adminStatus");
			// redirect depending on authorization
			if (adminStatus == null) {
				response.sendRedirect("placeOrder.jsp");
			} else {
				response.sendRedirect("adminOrderList.jsp");
			}
			break;

		case "-":
			orderId = Integer.parseInt(request.getParameter("orderId"));
			currOrder = ordrDao.getOrder(orderId);
			// get current quantity and decrease it by 1
			int currQtyy = currOrder.get_quantity() - 1;
			// set new quantity
			currOrder.set_quantity((currQtyy));
			// update quantity to DB
			ordrDao.updateOrder(currOrder);

			response.sendRedirect("placeOrder.jsp");
			break;

		case "+":
			orderId = Integer.parseInt(request.getParameter("orderId"));
			currOrder = ordrDao.getOrder(orderId);
			// get current quantity and increase it by 1
			int currQty = currOrder.get_quantity() + 1;
			// set new quantity
			currOrder.set_quantity((currQty));
			// update quantity to DB
			ordrDao.updateOrder(currOrder);
			response.sendRedirect("placeOrder.jsp");
			break;

		case "Approve":
			orderId = Integer.parseInt(request.getParameter("orderId"));
			//get current order
			currOrder = ordrDao.getOrder(orderId);
			// set it's approvalStatus to true
			currOrder.set_approvalStatus(true);
			//get current admin
			Admin approvedBy = adminDao.getUser(Integer.parseInt(request.getParameter("userId")));
			// set admin who approvad order
			currOrder.set_approvedBy(approvedBy);
			// update DB
			boolean updated = ordrDao.updateApproval(currOrder);

			// create and send out confirmtion mail
//			String subject = "Order Confirmation";
//			String content = "Your " + currOrder.get_productOrdered().get_description()
//					+ " order has been approved. \n Your item will be shipped soon. \n Thanks for using us";
//			String currentUserEmail = currOrder.get_placedBy().get_email();
//			boolean wasEmailSuccessful;
//			if (updated == true) {
//				// send an alert to user with order inforomation
//				wasEmailSuccessful = emailSender.notifyOrderApproval(subject, content, "chuxjv@gmail.com");
//				if (!wasEmailSuccessful) {
//
//					message += "Error occured while sending out some emails!";
//				}
				response.sendRedirect("adminOrderList.jsp");
//			} 
			break;

		}

	}

}
