/**
 * 
 */
package com.kbconnect.boundary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.kbconnect.entity.Route;

/**
 * @author Gursewak Singh
 *
 */
public class RouteDAO implements RouteDAOInterface{
	
	private Connection _conn = null;
	private Statement _stmt = null;
	private PreparedStatement _pstmt = null;
	private ResultSet _rs = null;

	// instantiate DAOAgent to get the connection and disconnection
	DAOAgent daoAgent = new DAOAgent();
	private String databaseName = "kbconnect";

	@Override
	public ArrayList<Route> getAllRoutes() {
		// create mySQL query to get all
		String sql = "SELECT * FROM routes;";
		// define an arrayList to store routes
		ArrayList<Route> currList = new ArrayList<Route>();
		try {
			// connect the database
			this._conn = daoAgent.connectDB(this._conn, databaseName);

			this._stmt = this._conn.createStatement();
			this._rs = this._stmt.executeQuery(sql);
			while (this._rs.next()) {
				// instantiate new Route
				Route route = new Route ();
				route.set_id(this._rs.getInt("id"));
				route.set_routeNo(this._rs.getString("routeNo"));
				route.set_startingStop(this._rs.getString("startingStop"));
				route.set_terminationStop(this._rs.getString("terminationStop"));
				route.set_fromCity(this._rs.getString("fromCity"));
				route.set_toCity(this._rs.getString("toCity"));

				currList.add(route);

			}
			// after executing , close the connection
			this._conn = daoAgent.disconnectDB(this._conn);

		} catch (SQLException sx) {
			daoAgent.displayException(sx);
		}

		return currList;
	}

	@Override
	public Route getRoute(int routeId) {
		// create mySQL query to get one by ID
		String sql = "SELECT * FROM routes WHERE id=?";
		// define new route
		Route route= new Route();
		try {
			// connect the database
			this._conn = daoAgent.connectDB(this._conn, databaseName);

			// prepare the statement
			this._pstmt = this._conn.prepareStatement(sql);
			this._pstmt.setInt(1, routeId);
			
			this._rs = this._pstmt.executeQuery();
			while (this._rs.next()) {
				route.set_id(this._rs.getInt("id"));
				route.set_routeNo(this._rs.getString("routeNo"));
				route.set_startingStop(this._rs.getString("startingStop"));
				route.set_terminationStop(this._rs.getString("terminationStop"));
				route.set_fromCity(this._rs.getString("fromCity"));
				route.set_toCity(this._rs.getString("toCity"));


			}
			// after executing , close the connection
			this._conn = daoAgent.disconnectDB(this._conn);

		} catch (SQLException sx) {
			daoAgent.displayException(sx);
		}

		return route;
	}

	@Override
	public boolean createRoute(Route newRoute) {
		// Create mySql query to insert new one to database
		String sql = "INSERT INTO routes (routeNo, startingStop, terminationStop, fromCity, toCity) VALUES(?,?,?,?,?);";
		// sentinel
		int effectRow = 0;
		try {
			// connect the database
			this._conn = daoAgent.connectDB(this._conn, databaseName);
			// set the variables
			this._pstmt = this._conn.prepareStatement(sql);
			this._pstmt.setString(1, newRoute.get_routeNo());
			this._pstmt.setString(2, newRoute.get_startingStop());
			this._pstmt.setString(3, newRoute.get_terminationStop());
			this._pstmt.setString(4, newRoute.get_fromCity());
			this._pstmt.setString(5, newRoute.get_toCity());

			// execute and get effect row it should be 1 if execute successfully
			effectRow = this._pstmt.executeUpdate();

			// disconnect the database
			this._conn = daoAgent.disconnectDB(this._conn);

		} catch (SQLException sx) {
			daoAgent.displayException(sx);
		}

		return effectRow > 0;
	}

	@Override
	public boolean updateRoute (Route updatedRoute) {
		// Create mySql query to update current one on database
		String sql = "UPDATE routes SET routeNo=?, startingStop=?, terminationStop=?, fromCity=?, toCity=? WHERE id=?;";
		// sentinel
		int effectRow = 0;
		try {
			// connect the database
			this._conn = daoAgent.connectDB(this._conn, databaseName);
			// set the variables
			this._pstmt = this._conn.prepareStatement(sql);
			this._pstmt.setString(1, updatedRoute.get_routeNo());
			this._pstmt.setString(2, updatedRoute.get_startingStop());
			this._pstmt.setString(3, updatedRoute.get_terminationStop());
			this._pstmt.setString(4, updatedRoute.get_fromCity());
			this._pstmt.setString(5, updatedRoute.get_toCity());
			this._pstmt.setInt(6, updatedRoute.get_id());

			// execute and get effect row it should be 1 if execute successfully
			effectRow = this._pstmt.executeUpdate();

			// disconnect the database
			this._conn = daoAgent.disconnectDB(this._conn);

		} catch (SQLException sx) {
			daoAgent.displayException(sx);
		}

		return effectRow > 0;
	}

	@Override
	public boolean deleteRoute(Route deletedRoute) {
		// Create mySql query to delete current one on database
		String sql = "DELETE FROM routes WHERE id=?;";
		// sentinel
		int effectRow = 0;
		try {
			// connect the database
			this._conn = daoAgent.connectDB(this._conn, databaseName);
			// set the variables
			this._pstmt = this._conn.prepareStatement(sql);

			this._pstmt.setInt(1, deletedRoute.get_id());

			// execute and get effect row it should be 1 if execute successfully
			effectRow = this._pstmt.executeUpdate();

			// disconnect the database
			this._conn = daoAgent.disconnectDB(this._conn);

		} catch (SQLException sx) {
			daoAgent.displayException(sx);
		}

		return effectRow > 0;
	}

}
