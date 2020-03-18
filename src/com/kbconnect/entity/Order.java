

package com.kbconnect.entity;

import java.sql.Date;

/**
 * Create an order object for commuter
 * @author Weijun Shi
 *
 */
public class Order {
	private int _id;
	private String _description;
	private double _unitPrice;
	private int _quantity;
	private double _amount;
	private Date _transactionDate;
	
	/*reference database table 
	 * mysql> desc orders;
		+-----------------+--------------+------+-----+---------+----------------+
		| Field           | Type         | Null | Key | Default | Extra          |
		+-----------------+--------------+------+-----+---------+----------------+
		| id              | int(11)      | NO   | PRI | NULL    | auto_increment |
		| description     | varchar(255) | YES  |     | NULL    |                |
		| quantity        | int(11)      | YES  |     | NULL    |                |
		| unitPrice       | double(5,2)  | YES  |     | NULL    |                |
		| amount          | double(6,2)  | YES  |     | NULL    |                |
		| transactionDate | date         | YES  |     | NULL    |                |
		+-----------------+--------------+------+-----+---------+----------------+
		6 rows in set (0.00 sec)
	 */

	/**
	 * default constructor 
	 */
	public Order() {
		super();
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
	 * @return the _unitPrice
	 */
	public double get_unitPrice() {
		return _unitPrice;
	}
	/**
	 * @param _unitPrice the _unitPrice to set
	 */
	public void set_unitPrice(double _unitPrice) {
		this._unitPrice = _unitPrice;
	}
	/**
	 * @return the _quantity
	 */
	public int get_quantity() {
		return _quantity;
	}
	/**
	 * @param _quantity the _quantity to set
	 */
	public void set_quantity(int _quantity) {
		this._quantity = _quantity;
	}
	/**
	 * @return the _amount
	 */
	public double get_amount() {
		return _amount;
	}
	/**
	 * @param _amount the _amount to set
	 */
	public void set_amount(double _amount) {
		this._amount = _amount;
	}
	/**
	 * @return the _transactionDate
	 */
	public Date get_transactionDate() {
		return _transactionDate;
	}
	/**
	 * @param _transactionDate the _transactionDate to set
	 */
	public void set_transactionDate(Date _transactionDate) {
		this._transactionDate = _transactionDate;
	}
	

}
