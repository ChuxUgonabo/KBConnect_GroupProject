package com.kbconnect.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kbconnect.boundary.CommuterDAO;
import com.kbconnect.boundary.CompassCardDAO;
import com.kbconnect.entity.Commuter;
import com.kbconnect.entity.CompassCard;
import com.kbconnect.entity.User;

/**
 * Servlet implementation class CardController
 */
@WebServlet("/CardController")
public class CardController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CompassCardDAO cdao = new CompassCardDAO();
    private CommuterDAO udao = new CommuterDAO();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CardController() {
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

        // get the session associated with the request
        HttpSession thisSession = request.getSession();
        
        // if there is no user logged in, redirect to login page
        if (thisSession.getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            
            // get the username of logged in user
        } else {
            String loggedUsername = String.valueOf(thisSession.getAttribute("username"));
            // get the user info from the database
            User user = udao.getUser(loggedUsername);
            
            String action = request.getParameter("action");
            
            // switch case on action
            switch (action) {
            
                // if the action is to link a card
                case "linkCard":
                    // get the card number
                    String cardNumber = request.getParameter("cardNumber");
                    
                    // get the cvn
                    String cvn = request.getParameter("cvn");
                    
                    //  get the card from the data base
                    CompassCard compassCard = cdao.getCompassCard(cardNumber);

                    // get the user if the card is already in use by another user
                    User alreadyUser = udao.getUserFromCard(cardNumber);
                    
                    // compare the card cvn
                    if (compassCard == null || !compassCard.compareCVN(cvn)) {
                        response.sendRedirect("linkCard.jsp?message=invalidCard");
                        break;
                    } else if (alreadyUser != null ) {
                        response.sendRedirect("linkCard.jsp?message=invalidCard");
                        break;                    	
                    } else {
                        user.set_cardNumber(cardNumber);
                    }

                    udao.updateUser(user);

                    response.sendRedirect("profile.jsp");
                    
                    break;
                
                default:
                    
                    break;
            }
        }
    }

}
