/**
 * 
 */
package com.kbconnect.entity;

/**
 * @author Gursewak Singh
 * 
 *         Route
 *
 */
public class Route {

	private int _id;
	private String _routeNo;
	private String _startingStop;
	private String _terminationStop;
	private String _fromCity;
	private String _toCity;

	/**
	 * 
	 */
	public Route() {

	}

	/**
	 * 
	 * @param id the database id of the route
	 */
	public void set_id(int id) {
		_id = id;
	}

	/**
	 * 
	 * @return the database id of the route
	 */
	public int get_id() {
		return _id;
	}

	/**
	 * @return the _routeNo
	 */
	public String get_routeNo() {
		return _routeNo;
	}

	/**
	 * @param _routeNo the _routeNo to set
	 */
	public void set_routeNo(String _routeNo) {
		this._routeNo = _routeNo;
	}

	/**
	 * @return the _startingStop
	 */
	public String get_startingStop() {
		return _startingStop;
	}

	/**
	 * @param _startingStop the _startingStop to set
	 */
	public void set_startingStop(String _startingStop) {
		this._startingStop = _startingStop;
	}

	/**
	 * @return the _terminationStop
	 */
	public String get_terminationStop() {
		return _terminationStop;
	}

	/**
	 * @param _terminationStop the _terminationStop to set
	 */
	public void set_terminationStop(String _terminationStop) {
		this._terminationStop = _terminationStop;
	}

	/**
	 * @return the _fromCity
	 */
	public String get_fromCity() {
		return _fromCity;
	}

	/**
	 * @param _fromCity the _fromCity to set
	 */
	public void set_fromCity(String _fromCity) {
		this._fromCity = _fromCity;
	}

	/**
	 * @return the _toCity
	 */
	public String get_toCity() {
		return _toCity;
	}

	/**
	 * @param _toCity the _toCity to set
	 */
	public void set_toCity(String _toCity) {
		this._toCity = _toCity;
	}

}
