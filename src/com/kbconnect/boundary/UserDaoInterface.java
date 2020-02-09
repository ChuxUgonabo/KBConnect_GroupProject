package com.kbconnect.boundary;

import java.util.ArrayList;

import com.kbconnect.entity.User;
/**
 * 
 * @author 300108357 weijun shi
 *
 */


public interface UserDaoInterface {
	/**
	 * Retrieve all the users from the database of users
	 * @return ArrayList allStudents
	 */
	public ArrayList<User> getAllUsers();
	/**
	 * @param name
	 * @return object of User base on parameter of name
	 * Retrieve one user if the given name matches 
	 */

	public User getUser(String name);
	/**
	 * 
	 * @param newUser
	 * Update the specified user 
	 * @return boolean
	 */
	public boolean updateUser(User newUser);
	
    /**
     * 
     * @param currUser
     * Delete one user
     */
	public void deleteUser(User currUser);
	/**
	 * 
	 * @param newUser
	 * @return boolean
	 * Create one new user 
	 */
	public boolean createUser(User newUser);

}
