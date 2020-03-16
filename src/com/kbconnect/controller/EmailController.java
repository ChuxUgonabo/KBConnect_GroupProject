package com.kbconnect.controller;

import java.util.ArrayList;

import com.kbconnect.boundary.CommuterDAO;
import com.kbconnect.boundary.GmailServiceImplement;
import com.kbconnect.entity.Alert;
import com.kbconnect.entity.User;

/**
 * 
 * @author Gursewak Singh
 * 
 * This class handles the email notifications of alerts to the users
 * 
 */
public class EmailController {

	// define the variables for store information of Gmail
	private String _host;
	private String _port;
	private String _user;
	private String _pass;
	private ArrayList<User> _allUsers;
	private CommuterDAO _userDAO;
	
	public EmailController() {
		// put the attributes of Gmail
		_host = "smtp.gmail.com";
		_port = "587";
		_user = "csis3275kbconnect@gmail.com";
		_pass = "kbconnect3275";
		_userDAO = new CommuterDAO();
		_allUsers = _userDAO.getAllUsers();
	}
	
	/**
	 * 
	 * Send an alert to all the users to their email
	 * 
	 * @param alert the alert to be sent
	 * @return true if the message was successfully sent, false otherwise
	 */
	public boolean notifyAllUsers( Alert alert ) {
		
		// set the recipient, subject, the main content of the messsage
		String recipient;
		String subject = alert.get_shortDescription();
		String content = alert.get_description();
		String resultMessage = "";
		boolean successful = true;
		
		// send a message to each user on the file
		for( User a_user: _allUsers ) {
			
			// get the email of each user
			recipient = a_user.get_email();
			// try to send the email
			try {
				GmailServiceImplement.sendEmail(_host, _port, _user, _pass, recipient, subject, content);
				resultMessage = "The e-mail was sent successfully";

				// if there was an exception, handle it
			} catch (Exception ex) {
				ex.printStackTrace();
				resultMessage = "There were an error: " + ex.getMessage();
				successful = false;
			} finally {
				System.out.println(resultMessage);
				
			}
		}
		// return true if all the notifications were sent, false even if one of
		// the notification failed
		return successful;
	}
	
	public boolean notifySubscribed( Alert alert ) {
		// to be implemented in iteration #3
		return false;
	}
}
