package com.kbconnect.boundary;

import java.util.ArrayList;

import com.kbconnect.entity.Order;

/**
 * 
 * @author Weijun Shi
 *
 */

public interface OrderDAOInterface {
	/**
	 * 
	 * @return ArrayList
	 */
	public ArrayList<Order> getAllOrders();

	/**
	 * 
	 * @param orderId given id to retrieve specific order
	 * @return object of order
	 */

	public Order getOrder(int orderId);

	/**
	 * 
	 * @param newOrder given object of order to create
	 * @return boolean value, true when it is successful, otherwise false
	 */

	public boolean createOrder(Order newOrder);

	/**
	 * 
	 * @param updatedOrder given object of order to update
	 * @return boolean value, true when it is successful, otherwise false
	 */
	public boolean updateOrder(Order updatedOrder);

	/**
	 * 
	 * @param deletedOrder given object of order to delete
	 * @return boolean value, true when it is successful, otherwise false
	 */

	public boolean deleteOrder(Order deletedOrder);

}
