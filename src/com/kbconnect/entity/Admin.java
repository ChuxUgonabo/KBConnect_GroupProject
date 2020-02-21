/**
 * 
 */
package com.kbconnect.entity;

/**
 * @author John Ugonabo
 *
 */
public class Admin extends User{
	private boolean _isAdmin;
	
	/**
	 * The constructor for Admin
	 */
	public Admin() {
		
	}
	
	public void set_isAdmin(boolean is_Admin) {
		_isAdmin = is_Admin;
	}

	public boolean is_isAdmin() {
		return _isAdmin;
	}

	

}
