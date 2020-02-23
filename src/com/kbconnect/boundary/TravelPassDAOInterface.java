/**
 * 
 */
package com.kbconnect.boundary;

import java.util.ArrayList;

import com.kbconnect.entity.TravelPass;

/**
 * @author Gursewak Singh
 *
 */
public interface TravelPassDAOInterface {

	/**
	 * Retrieve all the passes from the database and return to the user
	 * 
	 * @return a list of all the passes in the database
	 */
	ArrayList<TravelPass> getAllPasses();

	/**
	 * Retrieves the pass from the database where the pass id matches the given id
	 * If no pass with the given id exists, then return null
	 * 
	 * @param id
	 * @return Travel pass
	 */
	TravelPass getPass(int id);

	/**
	 * Deletes a TravelPass from the database where the pass id matches the given id
	 * 
	 * @param id
	 * @return 
	 */
	boolean deletePass(TravelPass pass);

	/**
	 * Store the given pass in the database
	 * 
	 * @param pass
	 */
	boolean createPass(TravelPass pass);

	/**
	 * 
	 * update the price of the the pass with the given id.
	 * @param passId
	 * @param price
	 * 
	 * @return true if the database operation successful , false otherwise
	 */
	boolean updatePrice(int passId, int price);

}
