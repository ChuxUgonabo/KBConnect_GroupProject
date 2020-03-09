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
                response.sendRedirect("listAlerts.jsp");
                break;

            // if the admin is posting the update on a previous alert
            case "updateAlert":

                // get the id of the alert to be updated
                int updateId = Integer.parseInt(request.getParameter("updateId"));

                // get  the new values for the alert
                String updateDesc = request.getParameter("description");
                long updateMillis = System.currentTimeMillis();
                Date lastUpdate = new Date(updateMillis);

                // update the old alert to reflect new values
                Alert oldAlert = aldao.getAlert(updateId);
                oldAlert.set_description(updateDesc);
                oldAlert.set_dateOfLastUpdate(lastUpdate);

                // save the alert in the database
                aldao.updateAlert(oldAlert);
                response.sendRedirect("listAlert.jsp?message=updated&id=" + oldAlert.get_id());
                break;

            // if the admin is deleting the alert
            case "deleteAlert":
                // get the id of the alert to be deleted
                int deleteId = Integer.parseInt(request.getParameter("alertId"));

                // get the alert from the database
                Alert deleteAlert = aldao.getAlert(deleteId);

                // delete the alert
                aldao.deleteAlert(deleteAlert);
                response.sendRedirect("listAlert.jsp?message=deleted&id=" + deleteAlert.get_id());
                break;
        }

	}

}
