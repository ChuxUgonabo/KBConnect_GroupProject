package com.kbconnect.entity;

import org.mindrot.jbcrypt.BCrypt;


/**
 * @author Gursewak Singh
 *
 */
public class CompassCard {

	int _id; // database id
	String _cardNumber; // the card number
	String _cvn; // the encrypted 3 digit code
	boolean _isActive; // is the card active or not
	double _balance; // the money balance on the card

	public CompassCard() {
		
	}

	
	public CompassCard(String _cardNumber, String cvn, boolean _isActive, double _balance) {
		this._cardNumber = _cardNumber;
		this._cvn = BCrypt.hashpw(cvn, BCrypt.gensalt());
		this._isActive = _isActive;
		this._balance = _balance;
	}

	/**
	 * @return the _loadedBalance
	 */
	public double get_loadedBalance() {
		return _balance;
	}

	/**
	 * @param _loadedBalance the _loadedBalance to set
	 */
	public void set_loadedBalance(double _loadedBalance) {
		this._balance = _loadedBalance;
	}

	/**
	 * @return the _cardNumber
	 */
	public String get_cardNumber() {
		return _cardNumber;
	}

	/**
	 * @return the _isActive
	 */
	public boolean is_isActive() {
		return _isActive;
	}
	
	/**
	 * 
	 * Compare the given cvn with the actual cvn to verify card integrity
	 * @param cvn this is security code
	 * @return true if cvn matches false otherwise
	 */
	public boolean compareCVN( String cvn ) {
		// encrypted cvn
		return BCrypt.checkpw(cvn, this._cvn);
	}

    public String get_cvn() {
        return this._cvn;
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
	 * @param _cardNumber the _cardNumber to set
	 */
	public void set_cardNumber(String _cardNumber) {
		this._cardNumber = _cardNumber;
	}

	/**
	 * @param _cvn the _cvn to set
	 */
	public void set_cvn(String _cvn) {
//		this._cvn = BCrypt.hashpw(_cvn, BCrypt.gensalt());
		this._cvn = _cvn;
	}
	
	public void set_encryptedCvn(String _cvn) {
		this._cvn = BCrypt.hashpw(_cvn, BCrypt.gensalt());
	}
	


	/**
	 * @param _isActive the _isActive to set
	 */
	public void set_isActive(boolean _isActive) {
		this._isActive = _isActive;
	}

}
