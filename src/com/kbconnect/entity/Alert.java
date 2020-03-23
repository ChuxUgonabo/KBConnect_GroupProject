/**
 * 
 */
package com.kbconnect.entity;

import java.sql.Date;

/**
 * @author John Ugonabo
 *
 */
public class Alert {
	private int _id;
	private String _shortDescription;
	private String _description;
	private Route _route;
	private Date _dateOfLastUpdate; // date of last update
	private Date _dateCreated; // date created

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
	 * @return the _shortDescription
	 */
	public String get_shortDescription() {
		return _shortDescription;
	}

	/**
	 * @param _shortDescription the _shortDescription to set
	 */
	public void set_shortDescription(String _shortDescription) {
		this._shortDescription = _shortDescription;
	}

	/**
	 * @return the _dateOfLastUpdate
	 */
	public Date get_dateOfLastUpdate() {
		return _dateOfLastUpdate;
	}

	/**
	 * @param _dateOfLastUpdate the _dateOfLastUpdate to set
	 */
	public void set_dateOfLastUpdate( Date _dateOfLastUpdate) {
		this._dateOfLastUpdate = _dateOfLastUpdate;
	}

	/**
	 * @return the _dateCreated
	 */
	public Date get_dateCreated() {
		return _dateCreated;
	}

	/**
	 * @param _dateCreated the _dateCreated to set
	 */
	public void set_dateCreated( Date _dateCreated) {
		this._dateCreated = _dateCreated;
	}

	/**
	 * @return the _route
	 */
	public Route get_route() {
		return _route;
	}

	/**
	 * @param _route the _route to set
	 */
	public void set_route(Route _route) {
		this._route = _route;
	}

}
