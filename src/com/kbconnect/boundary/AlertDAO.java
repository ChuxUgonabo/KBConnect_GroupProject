package com.kbconnect.boundary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kbconnect.entity.Alert;

public class AlertDAO implements AlertDAOInterface {

	private Connection conn = null;
	private ResultSet rs = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private String databaseName = "kbconnect";
	private DAOAgent daoAgent = new DAOAgent();

	@Override
	public ArrayList<Alert> getAllAlerts() {
		// list to store all the Alerts
		ArrayList<Alert> allAlerts = new ArrayList<>();

		// list all the Alerts
		String sql = "SELECT * FROM alerts;";
		try {

			// connecting the connectDB
			this.conn = daoAgent.connectDB(this.conn, databaseName);

			// create the statement
			this.stmt = this.conn.createStatement();

			// Execute and store
			this.rs = this.stmt.executeQuery(sql);

			// convert all the results into java objects
			while (rs.next()) {
				// instantiate a new Alert
				Alert alert = new Alert();

				// populate the properties of the object from the database
				alert.set_id(rs.getInt("id"));
				alert.set_description(rs.getString("description"));
				alert.set_dateCreated(rs.getDate("dateCreated"));
				alert.set_dateOfLastUpdate(rs.getDate("dateOfLastUpdate"));

				// add the object to the list
				allAlerts.add(alert);
			}

			// disconnect from the database
			this.conn = daoAgent.disconnectDB(conn);
		} catch (SQLException sx) {
			daoAgent.displayException(sx);

		}
		return allAlerts;
	}

	@Override
	public Alert getAlert(int alertId) {
		// Instantiate the object of alert
		Alert alert = new Alert();

		// get one object of alert by condition of its id
		String sql = "SELECT * FROM alerts where id=?;";
		try {

			// connecting the connectDB
			this.conn = daoAgent.connectDB(this.conn, databaseName);
			// create the prepared statement
			this.pstmt = this.conn.prepareStatement(sql);
			// set the parameter for the query
			this.pstmt.setInt(1, alertId);
			// Execute
			this.rs = this.pstmt.executeQuery();
			while (rs.next()) {
				// populate the properties of the object from the database
				alert.set_id(rs.getInt("id"));
				alert.set_description(rs.getString("description"));
				alert.set_dateCreated(rs.getDate("dateCreated"));
				alert.set_dateOfLastUpdate(rs.getDate("dateOfLastUpdate"));				
			}
			// disconnect from the database
			this.conn = daoAgent.disconnectDB(this.conn);

		} catch (SQLException sx) {
			daoAgent.displayException(sx);
		}
		return alert;
	}

	@Override
	public boolean createAlert(Alert newAlert) {
		// create a query to insert one
		String sql = "INSERT INTO alerts (description,dateOfLastUpdate, dateCreated) values (?,?,?);";
		int count = -1;
		try {
			// get connect to database
			this.conn = daoAgent.connectDB(this.conn, databaseName);
			// create the prepare statement
			this.pstmt = this.conn.prepareStatement(sql);
			// set parameters
			this.pstmt.setString(1, newAlert.get_description());
			this.pstmt.setDate(2, newAlert.get_dateOfLastUpdate());
			this.pstmt.setDate(3, newAlert.get_dateCreated());
			// execute
			this.pstmt.execute();
			count = this.pstmt.getResultSetType();

			// disconnect
			this.conn = daoAgent.disconnectDB(this.conn);

		} catch (SQLException sx) {
			daoAgent.displayException(sx);
		}

		return count > 0;

	}

	@Override
	public boolean updateAlert(Alert updatedAlert) {
		String sql = "UPDATE alerts set description =?, dateOfLastUpdate=?, dateCreated=? WHERE id=?;";
		int count = -1;
		
		try {
			// connect to the database
			this.conn = daoAgent.connectDB(this.conn, databaseName);
			// create the prepare statement
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, updatedAlert.get_description());
			this.pstmt.setDate(2, updatedAlert.get_dateOfLastUpdate());
			this.pstmt.setDate(3, updatedAlert.get_dateCreated());
			this.pstmt.setString(4, String.valueOf(updatedAlert.get_id()));

			this.pstmt.execute();
			count = this.pstmt.getUpdateCount();

			// disconnect from the database
			this.conn = daoAgent.disconnectDB(this.conn);

		} catch (SQLException sx) {
			daoAgent.displayException(sx);
		}

		return count > 0;
	}

	@Override
	public void deleteAlert(Alert deletedAlert) {
		// create a query to delete one
		String sql = "DELETE FROM alerts WHERE id=?;";
		try {
			// connect to the database
			this.conn = daoAgent.connectDB(this.conn, databaseName);
			// create a prepare statement
			this.pstmt = this.conn.prepareStatement(sql);
			// set the parameter
			this.pstmt.setInt(1, deletedAlert.get_id());
			// execute
			this.pstmt.execute();			
			// disconnect
			this.conn = daoAgent.disconnectDB(this.conn);

		} catch (SQLException sx) {
			daoAgent.displayException(sx);
		}
		
		
	}

}
