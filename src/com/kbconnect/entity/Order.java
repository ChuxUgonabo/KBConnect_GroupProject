
package com.kbconnect.entity;

import java.sql.Date;

/**
 * Create an order object for commuter
 * 
 * @author Weijun Shi
 *
 */
public class Order {
	private int _id;
	private int _quantity;
	private Date _transactionDate;
	private Product _productOrdered;
	private User _placedBy;
	private Admin _approvedBy;

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
	 * @return the _productOrdered
	 */
	public Product get_productOrdered() {
		return _productOrdered;
	}

	/**
	 * @param _productOrdered the _productOrdered to set
	 */
	public void set_productOrdered(Product _productOrdered) {
		this._productOrdered = _productOrdered;
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

	/**
	 * @return the _placedBy
	 */
	public User get_placedBy() {
		return _placedBy;
	}

	/**
	 * @param _placedBy the _placedBy to set
	 */
	public void set_placedBy(User _placedBy) {
		this._placedBy = _placedBy;
	}

	/**
	 * @return the _approvedBy
	 */
	public Admin get_approvedBy() {
		return _approvedBy;
	}

	/**
	 * @param _approvedBy the _approvedBy to set
	 */
	public void set_approvedBy(Admin _approvedBy) {
		this._approvedBy = _approvedBy;
	}

}
