/**
 * 
 */
package com.kbconnect.entity;

/**
 * @author John Ugonabo
 *
 */
public class Product {
	int _id; // unique database id
	String _description;
	double _price;
	String _type;

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
	 * @return the _description
	 */
	public String get_description() {
		return _description;
	}

	/**
	 * @param _description the _description to set
	 */
	public void set_description(String _description) {
		this._description = _description;
	}

	/**
	 * @return the _price
	 */
	public double get_price() {
		return _price;
	}

	/**
	 * @param _price the _price to set
	 */
	public void set_price(double _price) {
		this._price = _price;
	}

	/**
	 * @return the _type
	 */
	public String get_type() {
		return _type;
	}

	/**
	 * @param _type the _type to set
	 */
	public void set_type(String _type) {
		this._type = _type;
	}

}
