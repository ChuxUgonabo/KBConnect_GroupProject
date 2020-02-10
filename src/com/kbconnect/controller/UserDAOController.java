package com.kbconnect.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kbconnect.boundary.UserDao;
import com.kbconnect.entity.User;

/**
 * Servlet implementation class UserDAOController
 */
@WebServlet("/UserDAOController")
public class UserDAOController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	// instantiate DAO
	UserDao bdao = new UserDao();

	public UserDAOController() {
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
		// sentinel for checking the action if it is successful
		boolean process = false;
		// See what the form action was
		switch (request.getParameter("action")) {
		case "create":
			// instantiate object of User
			User newUser = new User();
			// set attributes value to object of User
			newUser.registerUser(request.getParameter("fullName"), 
					request.getParameter("username"),
					request.getParameter("password"),
					request.getParameter("email"), 
					request.getParameter("address"),
					request.getParameter("DOB"));

			// add new one to database

			process = bdao.createUser(newUser);
			System.out.println("creating: " + process);

			break;

		case "edit":
			// instantiate object of user
			User editUser = new User();

			// call getUser() to get current user by id
			editUser = bdao.getUser(request.getParameter("id"));

			// set attributes value to object
			editUser.registerUser(request.getParameter("fullName"), 
					request.getParameter("username"),
					request.getParameter("password"),
					request.getParameter("email"), 
					request.getParameter("address"),
					request.getParameter("DOB"));
//			editUser.set_fullName(request.getParameter("fullName"));
//			editUser.set_username(request.getParameter("username"));
//			editUser.set_email(request.getParameter("email"));
//			editUser.set_password(request.getParameter("password"));
//			editUser.set_address(request.getParameter("address"));
//			editUser.set_DOB(request.getParameter("DOB"));

			// call update method to editing
			process = bdao.updateUser(editUser);
			System.out.println("Editing: " + process);

			break;

		case "delete":
			// instantiate object of User
			User curr = new User();
			// call getUser to get current User by id
			curr = bdao.getUser(request.getParameter("id"));
			// add new one to database
			System.out.println("Deleting is coming");
			bdao.deleteUser(curr);

			break;

		default:
			System.out.println("there is no action to do");
			break;
		}

		// back to login page
		response.sendRedirect("login.jsp");

//		doGet(request, response);
	}

}
