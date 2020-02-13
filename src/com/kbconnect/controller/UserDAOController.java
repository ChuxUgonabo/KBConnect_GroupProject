package com.kbconnect.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

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
	// initialize an array list of allUser
	ArrayList<User> allUser = new ArrayList<User>();

	public UserDAOController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *		response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *		response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// get all users for compare with a new user if it exists
		allUser = bdao.getAllUsers();
		// sentinel for checking the action if it is successful
		boolean process = false;
		// See what the form action was
		switch (request.getParameter("action")) {
		case "create":

			String fullname = request.getParameter("fullName");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			String DOB = request.getParameter("DOB");

			//
			// ^ # Start of the line
			// [a-z0-9_-] # Match characters and symbols in the list, a-z, 0-9, underscore,
			// hyphen
			// {3,15} # Length at least 2 characters and maximum length of 15
			// $ # End of the line
			Pattern pattern = Pattern.compile("^[a-z0-9A-Z_-]{2,15}$");
			boolean exist = false;
			boolean valid = (username != null) && pattern.matcher(username).matches();
			if (valid) {
				// compare with every user in the list by username
				for (User u : allUser) {
					if (u.get_username().equals(username)) {
						exist = true;
						break;
					}
				}

				if (exist) {
					response.sendRedirect("registeUser.jsp?error=same");

				} else {

					// instantiate object of User with the given parameters
					User newUser = new User(fullname, username, password, email, address, DOB);
					// add new one to database
					process = bdao.createUser(newUser);
					System.out.println("creating: " + process);
					// back to login page
					response.sendRedirect("login.jsp?message=succeed");
				}

			}

			else {

				response.sendRedirect("registeUser.jsp?error=invalid");
			}

			break;

		case "edit":
			// instantiate object of user
			User editUser;

			// call getUser() to get current user by id
			editUser = bdao.getUser(request.getParameter("id"));

			// set attributes value to object
			/**
			editUser.registerUser(request.getParameter("fullName"), 
					request.getParameter("username"),
					request.getParameter("password"),
					request.getParameter("email"), 
					request.getParameter("address"),
					request.getParameter("DOB"));
					*/
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
//		response.sendRedirect("login.jsp");

//		doGet(request, response);
	}

}
