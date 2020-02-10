package com.kbconnect.entity;

import java.sql.Date;

/**
 * 
 * @author Gursewak Singh
 *
 *         Stores the information about the passes loaded on different compass
 *         cards
 */
public class LoadedPasses {

	int _id;
	CompassCard _card; // the card on which the passes is loaded
	TravelPass _pass; // the pass that is loaded
	Date _startDate; // starting date of the pass
	Date _endDate; // ending date of the pass
	boolean autorenew; // pointer to check if the pass auto-renews

	/**
	 * @return the autorenew
	 */
	public boolean isAutorenew() {
		return autorenew;
	}

	/**
	 * @param autorenew the auto-renew to set
	 */
	public void setAutorenew(boolean autorenew) {
		this.autorenew = autorenew;
	}

	/**
	 * @param _id the _id to set
	 */
	public void set_id(int _id) {
		this._id = _id;
	}

	/**
	 * @return the _card
	 */
	public CompassCard get_card() {
		return _card;
	}

	/**
	 * @param _card the _card to set
	 */
	public void set_card(CompassCard _card) {
		this._card = _card;
	}

	/**
	 * @return the _pass
	 */
	public TravelPass get_pass() {
		return _pass;
	}

	/**
	 * @param _pass the _pass to set
	 */
	public void set_pass(TravelPass _pass) {
		this._pass = _pass;
	}

	/**
	 * @return the _startDate
	 */
	public Date get_startDate() {
		return _startDate;
	}

	/**
	 * @param _startDate the _startDate to set
	 */
	public void set_startDate(Date _startDate) {
		this._startDate = _startDate;
	}

	/**
	 * @return the _endDate
	 */
	public Date get_endDate() {
		return _endDate;
	}

	/**
	 * @param _endDate the _endDate to set
	 */
	public void set_endDate(Date _endDate) {
		this._endDate = _endDate;
	}

	/**
	 * @return the _id
	 */
	public int get_id() {
		return _id;
	}
}
