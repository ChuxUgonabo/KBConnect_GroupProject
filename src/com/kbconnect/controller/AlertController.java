package com.kbconnect.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import com.kbconnect.boundary.AlertDAO;
import com.kbconnect.boundary.RouteDAO;
import com.kbconnect.entity.Alert;
import com.kbconnect.entity.Route;

/**
 * Servlet implementation class AlertController
 */
@WebServlet("/AlertController")
public class AlertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmailController emailSender;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlertController() {
        super();
        emailSender = new EmailController();
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

		// messages to show to the user
		String message = "";
		
        // initialize a alert dao
        AlertDAO aldao = new AlertDAO();
        
        // initialize the route dao
        RouteDAO rdao = new RouteDAO();

        // check for action if it is to create an alert, update or delete
        String action = request.getParameter("action");

        // switch case on the action
        switch (action) {

            // if the admin is sending out alerts
            case "sendAlert":

                // get the alert information
                String shortDescription = request.getParameter("shortDescription");
                String alertDescription = request.getParameter("description");
                int routeId = Integer.parseInt(request.getParameter("routeId"));
                
                Route alertRoute = rdao.getRoute(routeId);
                // for a new alert, the date created and last update are same
                long millis = System.currentTimeMillis();
                Date alertCreationDate = new Date(millis);

                // initialize a new alert
                Alert newAlert = new Alert();

                // set the parameters for the alert
                newAlert.set_shortDescription(shortDescription);
                newAlert.set_description(alertDescription);
                newAlert.set_dateOfLastUpdate(alertCreationDate);
                newAlert.set_dateCreated(alertCreationDate);
                newAlert.set_route(alertRoute);

                // save the alert to the database
                aldao.createAlert(newAlert);
                // send the email notifications
                boolean wasEmailSuccessful = emailSender.notifySubscribed(newAlert);
                if (!wasEmailSuccessful) {

                	message += "Error occured wile sending out some emails!";
                }
                response.sendRedirect("listAlerts.jsp?message=" + message);
                break;

            // if the admin is posting the update on a previous alert
            case "updateAlert":

                // get the id of the alert to be updated
                int updateId = Integer.parseInt(request.getParameter("alertId"));

                // get  the new values for the alert
                String updateShortDesc = request.getParameter("shortDescription");
                String updateDesc = request.getParameter("description");
                long updateMillis = System.currentTimeMillis();
                Date lastUpdate = new Date(updateMillis);
                
                int updatedRouteId = Integer.parseInt(request.getParameter("routeId"));
                
                Route newAlertRoute = rdao.getRoute(updatedRouteId);

                // update the old alert to reflect new values
                Alert oldAlert = aldao.getAlert(updateId);
                oldAlert.set_shortDescription(updateShortDesc);
                oldAlert.set_description(updateDesc);
                oldAlert.set_dateOfLastUpdate(lastUpdate);
                oldAlert.set_route(newAlertRoute);

                // save the alert in the database
                aldao.updateAlert(oldAlert);
                
                boolean wasNotifySuccessful = emailSender.notifySubscribed(oldAlert);
                if (!wasNotifySuccessful) {

                	message += "Error occured wile sending out some emails!";
                }
                response.sendRedirect("listAlerts.jsp?message=updated&id=" + oldAlert.get_id());
                break;

            // if the admin is deleting the alert
            case "deleteAlert":
                // get the id of the alert to be deleted
                int deleteId = Integer.parseInt(request.getParameter("alertId"));

                // get the alert from the database
                Alert deleteAlert = aldao.getAlert(deleteId);

                // delete the alert
                aldao.deleteAlert(deleteAlert);
                response.sendRedirect("listAlerts.jsp?message=deleted&id=" + deleteAlert.get_id());
                break;
        }

	}

}
