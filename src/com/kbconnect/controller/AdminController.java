package com.kbconnect.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kbconnect.boundary.AdminDAO;
import com.kbconnect.entity.Admin;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
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


        AdminDAO adoa = new AdminDAO();


        String action = request.getParameter("action");

        switch (action) {

            case "create":

                String fullname = request.getParameter("fullName");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String email = request.getParameter("email");
                String address = request.getParameter("address");
                String DOB = request.getParameter("DOB");

                Admin newAdmin = new Admin();

                newAdmin.set_fullName(fullname);
                newAdmin.set_username(username);
                newAdmin.storeHashedPassword(password);
                newAdmin.set_email(email);
                newAdmin.set_address(address);
                newAdmin.set_DOB(DOB);
                newAdmin.set_isAdmin(true);

                adoa.createUser(newAdmin);
                response.sendRedirect("adminProfile.jsp");
                break;
                
            case "update":
                String updatedFullname = request.getParameter("fullName");
                String updatedPassword = request.getParameter("password");
                String updatedEmail = request.getParameter("email");
                String updatedAddress = request.getParameter("address");

                Admin oldAdmin = new Admin();

                oldAdmin.set_fullName(updatedFullname);
                oldAdmin.storeHashedPassword(updatedPassword);
                oldAdmin.set_email(updatedEmail);
                oldAdmin.set_address(updatedAddress);

                adoa.updateUser(oldAdmin);
                response.sendRedirect("adminProfile.jsp");
                break;

            case "delete":
                // instantiate object of User
                Admin adminToDelete;
                // call getUser to get current User by id
                adminToDelete = adoa.getUser(request.getParameter("username"));
                // remove the admin from the database
                adoa.deleteUser(adminToDelete);
                response.sendRedirect("allCommuters.jsp");
                break;

            default:
                response.sendRedirect("404.jsp");
                break;
        }
    }

}
