package com.kbconnect.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import com.kbconnect.boundary.AlertDAO;
import com.kbconnect.entity.Alert;

/**
 * Servlet implementation class AlertController
 */
@WebServlet("/AlertController")
public class AlertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // initialize a alert dao
        AlertDAO aldao = new AlertDAO();

        // check for action if it is to create an alert, update or delete
        String action = request.getParameter("action");

        // switch case on the action
        switch (action) {

            // if the admin is sending out alerts
            case "sendAlert":

                // get the alert information
                String alertDescription = request.getParameter("description");
                // for a new alert, the date created and last update are same
                long millis = System.currentTimeMillis();
                Date alertCreationDate = new Date(millis);

                // initialize a new alert
                Alert newAlert = new Alert();

                // set the parameters for the alert
                newAlert.set_description(alertDescription);
                newAlert.set_dateOfLastUpdate(alertCreationDate);
                newAlert.set_dateCreated(alertCreationDate);

                // save the alert to the database
                aldao.createAlert(newAlert);
                break;

            // if the admin is posting the update on a previous alert
            case "updateAlert":
                break;

            // if the admin is deleting the alert
            case "deleteAlert":
                break;
        }

	}

}
