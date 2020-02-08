/**
 * 
 */
package com.kbconnect.entity;

/**
 * @author Gursewak Singh
 *
 */
public class TravelPass {

	String _id;
	String _passDuration; // day pass, weekly pass, or monthly pass
	int _price; // the price of the pass
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
	public int get_price() {
		return _price;
	}

	/**
	 * @param _price the _price to set
	 */
	public void set_price(int _price) {
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
	public String get_id() {
		return _id;
	}

}
