package com.kbconnect.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.parser.ParseException;


import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.kbconnect.boundary.ConnectGmailAPI;
import com.kbconnect.boundary.GmailServiceImplement;
import com.kbconnect.entity.GmailCredential;

/**
 * Servlet implementation class GmailController
 */
@WebServlet("/GmailController")
public class GmailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GmailController() {
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
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		
		PrintWriter out = response.getWriter();
		out.println("start");

		HttpTransport httpTransport = null;
		try {
			httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		GmailCredential credential = null;
		ConnectGmailAPI connect=new ConnectGmailAPI();
		
		try {
			credential = connect.connectGmaiService();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		GmailServiceImplement gmailService = new GmailServiceImplement();
		gmailService.setGmailCredential(credential);
		gmailService.setHttpTransport(httpTransport);

		try {
			if (gmailService.sendMessage("williamshi5358@hotmail.com", "today", "hello world")
					&& gmailService.sendMessage("shiweijun2000@hotmail.com", "today", "hello world")) {
				System.out.println("sent email");
			}

		} catch (

		MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("fail to send email");
		}
	}

}
