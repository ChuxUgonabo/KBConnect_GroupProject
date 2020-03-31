package com.kbconnect.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kbconnect.boundary.AdminDAO;
import com.kbconnect.boundary.CommuterDAO;
import com.kbconnect.boundary.OrderDAO;
import com.kbconnect.boundary.OrderDAOInterface;
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
		emailSender = new EmailController();
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
		// create a list to hold all orders created by current commuter
		ArrayList<Order> listOfCommutersOrders;
		boolean orderExists = false;
		String action = request.getParameter("action");
		// store variables to determine admin current status
		String adminManageUserOrder = "";
		String adminStatus;
		int currentUserId;

		switch (action) {
		case "add":
			// collect data from user
			// Get Quantity of order
			int qty = Integer.parseInt(request.getParameter("quantity"));
			// Get current product
			Product currentProduct = productDao.getProduct(Integer.parseInt(request.getParameter("productId")));
			currentUserId = Integer.parseInt(request.getParameter("userId"));
			User currentCommuter = commuterDao.getUser(currentUserId);
			// Get current timestamp
			long millis = System.currentTimeMillis();
			Date transactionDate = new Date(millis);

			// create new order
			Order newOrder = new Order(qty, transactionDate, currentProduct, currentCommuter, false);
			String product = newOrder.get_productOrdered().get_description();

			// Get list of all current commuter's orders
			listOfCommutersOrders = ordrDao.getAllUserOrders(currentUserId);

			// this check if this product is already in commuter's cart
			for (int i = 0; i < listOfCommutersOrders.size(); i++) {
				// if order is already in cart only increase the quantity
				if (listOfCommutersOrders.get(i).get_productOrdered().get_description().compareTo(product) == 0) {
					currOrder = ordrDao.getOrder(listOfCommutersOrders.get(i).get_id());
					// get current quantity and increase it by 1
					int currQty = currOrder.get_quantity() + newOrder.get_quantity();
					// set new quantity
					currOrder.set_quantity((currQty));
					// it has to be re-approved by admin
					currOrder.set_approvalStatus(false);
					// update quantity to DB
					ordrDao.updateQuantity(currOrder);

					orderExists = true;
				}
			}

			// Add order to dateBase
			if (orderExists == false) {
				ordrDao.createOrder(newOrder);
			}
			adminStatus = request.getParameter("adminStatus");
			adminManageUserOrder = request.getParameter("manageUserOrder");
			currentUserId = Integer.parseInt(request.getParameter("userId"));

			if (adminStatus == null) {
				response.sendRedirect("placeOrder.jsp");
			} else {
				if (adminManageUserOrder.equalsIgnoreCase("currentUser")) {
					response.sendRedirect("adminCreateNewOrder.jsp?userId="+currentUserId);
				} 
			}
			break;

		case "Delete":
			// collect data from user
			orderId = Integer.parseInt(request.getParameter("orderId"));
			currOrder = ordrDao.getOrder(orderId);
			// delete current order from DB
			ordrDao.deleteOrder(currOrder);
			adminStatus = request.getParameter("adminStatus");
			// redirect depending on authorization
			adminManageUserOrder = request.getParameter("manageUserOrder");
			currentUserId = Integer.parseInt(request.getParameter("userId"));

			if (adminStatus == null) {
				response.sendRedirect("placeOrder.jsp");
			} else {
				if (adminManageUserOrder != null) {
					response.sendRedirect("adminCreateNewOrder.jsp?userId="+currentUserId);
				} else {
					response.sendRedirect("adminOrderList.jsp");
				}
			}
			break;

		case "-":
			orderId = Integer.parseInt(request.getParameter("orderId"));
			currOrder = ordrDao.getOrder(orderId);
			// get current quantity and decrease it by 1
			int currQtyy = currOrder.get_quantity() - 1;
			// set new quantity
			currOrder.set_quantity((currQtyy));
			// it has to be re-approved by admin
			currOrder.set_approvalStatus(false);
			// update quantity to DB
			ordrDao.updateQuantity(currOrder);
			adminStatus = request.getParameter("adminStatus");
			adminManageUserOrder = request.getParameter("manageUserOrder");
			currentUserId = Integer.parseInt(request.getParameter("userId"));

			if (adminStatus == null) {
				response.sendRedirect("placeOrder.jsp");
			} else {
				if (adminManageUserOrder != null) {
					response.sendRedirect("adminCreateNewOrder.jsp?userId="+currentUserId);
				} else {
					response.sendRedirect("adminOrderList.jsp");
				}
			}
			break;

		case "+":
			orderId = Integer.parseInt(request.getParameter("orderId"));
			currOrder = ordrDao.getOrder(orderId);
			// get current quantity and increase it by 1
			int currQty = currOrder.get_quantity() + 1;
			// set new quantity
			currOrder.set_quantity((currQty));
			// it has to be re-approved by admin
			currOrder.set_approvalStatus(false);
			// update quantity to DB
			ordrDao.updateQuantity(currOrder);
			adminStatus = request.getParameter("adminStatus");
			// redirect depending on authorization
			adminManageUserOrder = request.getParameter("manageUserOrder");
			currentUserId = Integer.parseInt(request.getParameter("userId"));

			if (adminStatus == null) {
				response.sendRedirect("placeOrder.jsp");
			} else {
				if (adminManageUserOrder != null) {
					response.sendRedirect("adminCreateNewOrder.jsp?userId="+currentUserId);
				} else {
					response.sendRedirect("adminOrderList.jsp");
				}
			}

			break;

		case "Approve":
			orderId = Integer.parseInt(request.getParameter("orderId"));
			// get current order
			currOrder = ordrDao.getOrder(orderId);
			// set it's approvalStatus to true
			currOrder.set_approvalStatus(true);
			// get current admin
			Admin approvedBy = adminDao.getUser(Integer.parseInt(request.getParameter("adminId")));
			// set admin who approved order
			currOrder.set_approvedBy(approvedBy);
			// update DB
			boolean updated = ordrDao.updateApproval(currOrder);

//			create and send out confirmation mail
			String subject = "Order Confirmation";
			String content = "Your " + currOrder.get_productOrdered().get_description()
					+ " order has been approved. \n Your item will be shipped soon. \n Thanks for using us";
			String currentUserEmail = currOrder.get_placedBy().get_email();
			adminManageUserOrder = request.getParameter("manageUserOrder");
			currentUserId = Integer.parseInt(request.getParameter("userId"));

			boolean wasEmailSuccessful;
			if (updated == true) {
				// send an alert to user with order information
				wasEmailSuccessful = emailSender.notifyOrderApproval(subject, content, currentUserEmail);
				if (!wasEmailSuccessful) {

					message += "Error occured while sending out some emails!";
				}
				if (adminManageUserOrder != null) {
					response.sendRedirect("adminCreateNewOrder.jsp?userId="+currentUserId);
				} else {
					response.sendRedirect("adminOrderList.jsp");
				}
			}
			break;

		}

	}

}
