package com.kbconnect.entity;

/**
 * @author John Ugonabo
 *
 */

public class User {
	
	int _id; // unique id 
	String _fullName;
	String _username;
	String _password;
	String _email;
	int _phoneNumber;
	String _address;
	String _DOB;  // date of birth
	String _cardNumber; // the card number
	boolean _isAdmin; // 
	
	public User() {
		
	}
	
	
	public void registerUser(String fName, String userN, String passW, String email, int phoneN, String address, String dob) {
		_fullName = fName;
		_username = userN;
		_password = passW;
		_email = email;
		_phoneNumber = phoneN;
		_address = address;
		_DOB = dob;
	}
	
	
	public boolean login(String userN, String passW) {
		boolean correct = false;
		if(_username.compareTo(userN) == 1 && _password.compareTo(passW) == 1) {
			correct = true;
		}
		return correct;
	}
	
	public void logout() {
		
	}
	
	
	  public void updateUser(int id, String fName, String userN, String passW, String email, int phoneN, String addtress, String dob) {
		 _fullName = fName;
		 _username = userN; 
		 _password = passW; 
		 _email = email; 
		 _phoneNumber = phoneN;
	     _address = addtress; 
	     _DOB = dob;
	  
	  }
	 
	
	public void unlinkCard(String cardN) {
		_cardNumber = cardN;
	}
	
	public void deleteCard() {
		_cardNumber = null;
	}


	/**
	 * @return the _id
	 */
	public int get_id() {
		return _id;
	}


	/**
	 * @param _id the _id to set
	 */
	public void set_id(int _id) {
		this._id = _id;
	}


	/**
	 * @return the _fullName
	 */
	public String get_fullName() {
		return _fullName;
	}


	/**
	 * @param _fullName the _fullName to set
	 */
	public void set_fullName(String _fullName) {
		this._fullName = _fullName;
	}


	/**
	 * @return the _username
	 */
	public String get_username() {
		return _username;
	}


	/**
	 * @param _username the _username to set
	 */
	public void set_username(String _username) {
		this._username = _username;
	}


	/**
	 * @return the _password
	 */
	public String get_password() {
		return _password;
	}


	/**
	 * @param _password the _password to set
	 */
	public void set_password(String _password) {
		this._password = _password;
	}


	/**
	 * @return the _email
	 */
	public String get_email() {
		return _email;
	}


	/**
	 * @param _email the _email to set
	 */
	public void set_email(String _email) {
		this._email = _email;
	}


	/**
	 * @return the _phoneNumber
	 */
	public int get_phoneNumber() {
		return _phoneNumber;
	}


	/**
	 * @param _phoneNumber the _phoneNumber to set
	 */
	public void set_phoneNumber(int _phoneNumber) {
		this._phoneNumber = _phoneNumber;
	}


	/**
	 * @return the _address
	 */
	public String get_address() {
		return _address;
	}


	/**
	 * @param _address the _address to set
	 */
	public void set_address(String _address) {
		this._address = _address;
	}


	/**
	 * @return the _DOB
	 */
	public String get_DOB() {
		return _DOB;
	}


	/**
	 * @param _DOB the _DOB to set
	 */
	public void set_DOB(String _DOB) {
		this._DOB = _DOB;
	}


	/**
	 * @return the _cardNumber
	 */
	public String get_cardNumber() {
		return _cardNumber;
	}


	/**
	 * @param _cardNumber the _cardNumber to set
	 */
	public void set_cardNumber(String _cardNumber) {
		this._cardNumber = _cardNumber;
	}


	/**
	 * @return the _isAdmin
	 */
	public boolean is_isAdmin() {
		return _isAdmin;
	}


	/**
	 * @param _isAdmin the _isAdmin to set
	 */
	public void set_isAdmin(boolean _isAdmin) {
		this._isAdmin = _isAdmin;
	}
	
	
//	
	
}
