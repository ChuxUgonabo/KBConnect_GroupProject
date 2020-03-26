package com.kbconnect.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kbconnect.boundary.AdminDAO;
import com.kbconnect.boundary.CommuterDAO;
import com.kbconnect.entity.Admin;
import com.kbconnect.entity.Commuter;
import com.kbconnect.entity.User;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// initialize the dao for admin and user
		CommuterDAO userDao = new CommuterDAO();
		AdminDAO adminDao = new AdminDAO();
		switch (request.getParameter("action")) {
			
		// if the action was simple login
		case "login":
			// retrieve the user from the database
			String username = request.getParameter("username");
			String password = request.getParameter("password");
            User requestedUser = userDao.getUser(username);

            // check if the user with given username exists and the password matches
			if(requestedUser == null || !requestedUser.comparePassword(password)) {
				// send the invalid credentials error message
				response.sendRedirect("login.jsp?message=invalidCredentials");
			} else {
				// start the session for the user
				HttpSession session = request.getSession();
				session.setAttribute("username", requestedUser.get_username());
				// redirect the user to the profile page
				response.sendRedirect("profile.jsp");
			}
			break;
			
		// if the action was for admin login
		case "adminLogin":
			// get the admin from the database and
			String adminUsername = request.getParameter("username");
			String adminPassword = request.getParameter("password");
            Admin admin = adminDao.getUser(adminUsername);

            // check if the admin with the given username exists
            // compare to check if the passwords match
			if (admin == null || !admin.comparePassword(adminPassword)) {
				// send the invalid credentials error
				response.sendRedirect("adminLogin.jsp?message=invalidCredentials");
			} else {
				// start the session for admin
				HttpSession session = request.getSession();
				session.setAttribute("adminUsername", admin.get_username());
				// redirect the admin to his profile page
				response.sendRedirect("adminProfile.jsp");
			}
            break;

		case "logout":
			// check if the admin is loging out or comuter
            String isAdmin = request.getParameter("admin");
            
            // destroy the user session associated with the request
			HttpSession session = request.getSession();
			session.invalidate();
			
			// if the loggedout user was admin, redirect to admin login page
            if (isAdmin != null && isAdmin.equals("admin")) {
                response.sendRedirect("adminLogin.jsp?message=loggedOut");
                break;
            }
            // redirect user to login page
			response.sendRedirect("login.jsp?message=loggedOut");
            break;
		}
		
	}

}
