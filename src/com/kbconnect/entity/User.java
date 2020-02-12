package com.kbconnect.entity;

import java.sql.Date;

// java implementation of bcrypt url: http://www.mindrot.org/projects/jBCrypt
import org.mindrot.jbcrypt.BCrypt;

/**
 * @author John Ugonabo
 *
 *
 *		   bcrypt encryption reference: http://www.mindrot.org/projects/jBCrypt/
 */

public class User {

	int _id; // unique id for the database
	String _fullName;
	String _username;
	String _password; // encrypted password
	String _email;
	String _address; // full address including street address city province and postal code
	java.sql.Date _DOB; // date of birth
	String _cardNumber; // the card number

    public User() {
    };

	public User(String fName, String userN, String passW, String email, String address, String dob) {
		_fullName = fName;
		_username = userN;
		_password = BCrypt.hashpw(passW, BCrypt.gensalt());
		_email = email;
		_address = address;
		// date should be in format yyyy-mm-dd
		Date sqlDob = Date.valueOf(dob);
		_DOB = sqlDob;
	}


	public void login(String userN, String passW) {
		// TODO do this in the servlet not here
	}

	public void logout() {
		// TODO do in servlet
	}

	public void updateUser(User updatedUser) {
		_fullName = updatedUser.get_fullName();
		_username = updatedUser.get_username();
		_password = updatedUser.get_password();
		_email = updatedUser.get_email();
		_address = updatedUser.get_address();
		_DOB = updatedUser.get_DOB();
		_cardNumber = updatedUser.get_cardNumber();
	}

	public void linkCard(CompassCard card) {
		// cardN should be the 
		_cardNumber = card.get_cardNumber();
	}

	public void unlinkCard() {
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
	 *	this is only used when retreiving the user from the database
	 *	it is not used when creating a new user
	 *	only storeHashedPassword() is used when creating a new user
	 * @param _password the _password to set
	 */
	public void set_password(String _password) {
		this._password = _password;
	}

	/**
	 * 
	 * @param password
	 */
	public void storeHashedPassword(String password) {
		this._password = BCrypt.hashpw(password, BCrypt.gensalt());
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
	public Date get_DOB() {
		return _DOB;
	}

	/**
	 * @param _DOB the _DOB to set
	 */
	public void set_DOB(String _DOB) {
		// date should be in format yyyy-mm-dd
		Date sqlDob = Date.valueOf(_DOB);
		this._DOB = sqlDob;
	}
	
	public void set_DOB(java.sql.Date DOB) {
		this._DOB = DOB;
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
	 * 
	 * @param password
	 * @return
	 */
	public boolean comparePassword(String password) {
		return BCrypt.checkpw(password, this._password);
	}

}
