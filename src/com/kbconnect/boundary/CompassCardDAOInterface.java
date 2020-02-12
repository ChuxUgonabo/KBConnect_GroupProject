package com.kbconnect.boundary;

import java.util.ArrayList;

import com.kbconnect.entity.CompassCard;

/**
 * 
 * @author Gursewak Singh
 *
 */
public interface CompassCardDAOInterface {

	/**
	 * 
	 * @return all the compass cards in the database
	 */
	ArrayList<CompassCard> getAllCards();

	/**
	 * 
	 * @param cardNumber the unique card number
	 * @return the card for which the card 
	 */
	CompassCard getCompassCard(String cardNumber);

	/**
	 * 
	 * @param cardNumber the unique card number
	 * @return delete the card with the given number
	 */
	boolean deleteCard(CompassCard card);

	/**
	 * 
	 * @param card CompassCard to be updated
	 * @return true if successful false otherwise
	 */
	boolean updateCard(CompassCard card);

	/**
	 * 
	 * @param card CompassCard to be created
	 * @return true if successful false otherwise
	 */
    boolean createCard(CompassCard card);
}
