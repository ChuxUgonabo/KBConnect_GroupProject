package com.kbconnect.boundary;

import java.util.ArrayList;

import com.kbconnect.entity.Route;
import com.kbconnect.entity.SubscribedTo;
import com.kbconnect.entity.User;

/**
 * 
 * @author Gursewak Singh
 *
 */
public interface SubscribedDAOInterface {
	
	/**
	 * 
	 * @return ArrayList
	 */
	public ArrayList<SubscribedTo> getAllSubscriptions();

	/**
	 * 
	 * @param orderId given id to retrieve specific subscription
	 * @return subscribedTo object
	 */

	public SubscribedTo getSubscription(int subscriptionId);

	/**
	 * 
	 * @param newSubscription given object of subscribeTo to create
	 * @return boolean value, true when it is successful, otherwise false
	 */

	public boolean createSubscription(SubscribedTo newSubscription);

	/**
	 * 
	 * @param updatedSubscription given object of order to update
	 * @return boolean value, true when it is successful, otherwise false
	 */
	public boolean updateSubscription(SubscribedTo updatedSubscription);

	/**
	 * 
	 * @param deletedSubscription given object of SubscribedTo to delete
	 * @return boolean value, true when it is successful, otherwise false
	 */

	public boolean deleteSubscription(SubscribedTo deletedSubscription);

	/**
	 * 
	 * @param route the route for which the subscribtions are to be found
	 * @return the subscriptions that contain the id for the given route
	 */
	public ArrayList<SubscribedTo> getUsersSubscribedTo( Route route);
	
	/**
	 * 
	 * @param givenUser
	 * @return
	 */
	public ArrayList<SubscribedTo> getSubscriptionForUser( User givenUser );
}
