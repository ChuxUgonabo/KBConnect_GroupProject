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
	private String _description;
	Date _dateOfLastUpdate; // date of last update
	Date _dateCreated; // date created
	
	
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
	 * @return the _dateOfLastUpdate
	 */
	public java.sql.Date get_dateOfLastUpdate() {
		return _dateOfLastUpdate;
	}
	/**
	 * @param _dateOfLastUpdate the _dateOfLastUpdate to set
	 */
	public void set_dateOfLastUpdate(java.sql.Date _dateOfLastUpdate) {
		this._dateOfLastUpdate = _dateOfLastUpdate;
	}
	/**
	 * @return the _dateCreated
	 */
	public java.sql.Date get_dateCreated() {
		return _dateCreated;
	}
	/**
	 * @param _dateCreated the _dateCreated to set
	 */
	public void set_dateCreated(java.sql.Date _dateCreated) {
		this._dateCreated = _dateCreated;
	}


	

}
