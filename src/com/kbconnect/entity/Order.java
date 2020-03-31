/**
 *
@startuml
:Customer: ->(view product list to create orders)
:Customer: -->(list all orders)
:Customer: -->(edit product quantity of orders)
:Customer: -->(delete orders)
note "Order" as n1
(view product list to create orders) .. n1
(list all orders) .. n1
(edit product quantity of orders) .. n1
(delete orders) .. n1
@enduml


@startuml
:Admin: ->(create orders)
:Admin: -->(list all orders)
:Admin: -->(process to approve orders)
:Admin: -->(delete orders)
note "Order" as n1
(create orders) .. n1
(list all orders) .. n1
(process to approve orders) .. n1
(delete orders) .. n1
@enduml

 */
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
	private boolean _approvalStatus;


	/**
	 * @return the _approvalStatus
	 */
	public boolean is_approvalStatus() {
		return _approvalStatus;
	}
	/**
	 * @param _approvalStatus the _approvalStatus to set
	 */
	public void set_approvalStatus(boolean _approvalStatus) {
		this._approvalStatus = _approvalStatus;
	}
	/**
	 * default constructor
	 */
	public Order() {
		super();
	}
	public Order(int qty, Date tranDate, Product productOrd, User placedBy, boolean approvalS) {
		this._quantity = qty;
		this._transactionDate = tranDate;
		this._productOrdered = productOrd;
		this._placedBy = placedBy;
		this._approvalStatus = approvalS;
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
