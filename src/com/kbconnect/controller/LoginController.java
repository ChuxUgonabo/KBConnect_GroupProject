package com.kbconnect.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kbconnect.boundary.UserDao;
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
		
		UserDao userDao = new UserDao();
		switch (request.getParameter("action")) {
			
		case "login":
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			User requestedUser = userDao.getUser(username);
			if (requestedUser == null) {
				response.sendRedirect("login.jsp?error=InvalidCredentials");
			} else if(!requestedUser.comparePassword(password)) {
				response.sendRedirect("invalidCredentials");
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("username", requestedUser.get_username());
				response.sendRedirect("profile.jsp");
			}
			break;
			
		case "logout":
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("login.jsp");
		}
		
	}

}
