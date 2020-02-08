package com.kbconnect.entity;

/**
 * 
 * @author Gursewak Singh
 *
 */
public class CompassCard {

	int _id; // database id
	String _cardNumber; // the card number
	String _cvn; // the encrypted 3 digit code
	boolean _isActive; // is the card active or not
	int _balance; // the money balance on the card


	/**
	 * @return the _loadedBalance
	 */
	public int get_loadedBalance() {
		return _balance;
	}

	/**
	 * @param _loadedBalance the _loadedBalance to set
	 */
	public void set_loadedBalance(int _loadedBalance) {
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
	 * @param cvn
	 * @return true if cvn matches false otherwise
	 */
	public boolean compareCVN( String cvn ) {
		// TODO compare cvn and return true if it matches, false otherwise
		return true;
	}

}
