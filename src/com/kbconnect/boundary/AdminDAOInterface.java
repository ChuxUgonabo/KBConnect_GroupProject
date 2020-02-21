package com.kbconnect.boundary;

import java.util.ArrayList;

import com.kbconnect.entity.Admin;
/**
 * 
 * @author 300108357 weijun shi
 *
 */


public interface AdminDAOInterface {

	/**
	 * Retrieve all the users from the database of users
	 * @return ArrayList allStudents
	 */
	public ArrayList<Admin> getAllUsers();

	/**
	 * @param name
	 * @return object of User base on parameter of name
	 * Retrieve one user if the given name matches 
	 */
	public Admin getUser(String name);

	/**
	 * 
	 * @param newUser
	 * Update the specified user 
	 * @return boolean
	 */
	public boolean updateUser(Admin newUser);
	
    /**
     * 
     * @param currUser
     * Delete one user
     */
	public void deleteUser(Admin currUser);
	/**
	 * 
	 * @param newUser
	 * @return boolean
	 * Create one new user 
	 */
	public boolean createUser(Admin newUser);
	
	/**
	 * 
	 * @param id
	 * @return user object with the given database id
	 */
	public Admin getUser(int id);

}
