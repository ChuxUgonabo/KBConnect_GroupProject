package com.kbconnect.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.kbconnect.boundary.GmailServiceImplement;

/**
 * Servlet implementation class GmailController
 */
@WebServlet("/GmailController")
public class GmailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// define the variables for store information of Gmail
	private String host;
	private String port;
	private String user;
	private String pass;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GmailController() {
		super();
		// put the attributes of Gmail
		this.host = "smtp.gmail.com";
		this.port = "587";
		this.user = "csis3275kbconnect@gmail.com";
		this.pass = "kbconnect3275";

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// reads form fields
//		String recipient = request.getParameter("recipient");
//		String subject = request.getParameter("subject");
//		String content = request.getParameter("content");

		String recipient = "shiweijun2000@hotmail.com";
		String subject = "from servlet by kbconnect";
		String content = "hello world";
		String resultMessage = "";
		PrintWriter out = response.getWriter();
		// sending 
		try {
			GmailServiceImplement.sendEmail(this.host, this.port, this.user, this.pass, recipient, subject, content);
			resultMessage = "The e-mail was sent successfully";

			out.append(resultMessage);
		} catch (Exception ex) {
			ex.printStackTrace();
			resultMessage = "There were an error: " + ex.getMessage();
			out.append(resultMessage);
		} finally {
			request.setAttribute("Message", resultMessage);
//			getServletContext().getRequestDispatcher("/successfulSending.jsp").forward(request, response);
		}

	}

}
