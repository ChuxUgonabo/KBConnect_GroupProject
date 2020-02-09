/**
 * 
 */
package com.kbconnect.entity;

import java.math.BigDecimal;

/**
 * @author Gursewak Singh
 *
 */
public class TravelPass {

	int _id;
	String _passDuration; // day pass, weekly pass, or monthly pass
	BigDecimal _price; // the price of the pass
	String _passType; // adult, child

	/**
	 * @return the _passDuration
	 */
	public String get_passDuration() {
		return _passDuration;
	}

	/**
	 * @param _passDuration the _passDuration to set
	 */
	public void set_passDuration(String _passDuration) {
		this._passDuration = _passDuration;
	}

	/**
	 * @return the _price
	 */
	public BigDecimal get_price() {
		return _price;
	}

	/**
	 * @param _price the _price to set
	 */
	public void set_price(BigDecimal _price) {
		this._price = _price;
	}

	/**
	 * @return the _passType
	 */
	public String get_passType() {
		return _passType;
	}

	/**
	 * @param _passType the _passType to set
	 */
	public void set_passType(String _passType) {
		this._passType = _passType;
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



}
