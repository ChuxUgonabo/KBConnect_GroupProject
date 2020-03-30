package com.kbconnect.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kbconnect.boundary.CommuterDAO;
import com.kbconnect.boundary.SubscribedDAO;
import com.kbconnect.entity.SubscribedTo;
import com.kbconnect.entity.User;

/**
 * Servlet implementation class SubscriptionController
 */
@WebServlet("/SubscriptionController")
public class SubscriptionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubscriptionController() {
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

		String action;
		HttpSession thisSession = request.getSession();
		CommuterDAO udao = new CommuterDAO();
		User user;
		SubscribedTo newSubscription;
		SubscribedDAO sdao = new SubscribedDAO();
		
		if( thisSession.getAttribute("username") == null) {
			response.sendRedirect("profile.jsp");
		} else if ((action = request.getParameter("action")) == null) {
			response.sendRedirect("profile.jsp");
		} else {

			String username = String.valueOf(thisSession.getAttribute("username"));
			user = udao.getUser(username);
			switch (action) {
				
				case "addSubscription":
				
					int routeId = Integer.parseInt(request.getParameter("routeId"));
					int userId = user.get_id();
					
					newSubscription = new SubscribedTo();
					newSubscription.set_routeId(routeId);
					newSubscription.set_userId(userId);
					
					sdao.createSubscription(newSubscription);
					response.sendRedirect("subscriptions.jsp?message=subscriptionAdded&routeId=" + routeId);
					break;
					
				case "delete":
					int subscriptionId = Integer.parseInt(request.getParameter("subscriptionId"));
					SubscribedTo subscriptionToDelete = sdao.getSubscription(subscriptionId);
					sdao.deleteSubscription(subscriptionToDelete);
					response.sendRedirect("subscriptions.jsp");
					break;
					
				default:
					response.sendRedirect("profile.jsp");
					break;
			}
			
		}
	}

}
