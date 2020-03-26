package com.kbconnect.boundary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kbconnect.entity.Route;
import com.kbconnect.entity.SubscribedTo;
import com.kbconnect.entity.User;

public class SubscribedDAO implements SubscribedDAOInterface {

	private Connection conn = null;
	private ResultSet rs = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private String databaseName = "kbconnect";
	private DAOAgent daoAgent = new DAOAgent();

	@Override
	public ArrayList<SubscribedTo> getAllSubscriptions() {
		// list to store all the subscriptions
		ArrayList<SubscribedTo> allSubscriptions = new ArrayList<>();

		// list all the subscriptions
		String sql = "SELECT * FROM subscribedTo;";
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
				SubscribedTo subscription = new SubscribedTo();

				// populate the properties of the object from the database
				subscription.set_id(rs.getInt("id"));
				subscription.set_routeId(rs.getInt("id"));
				subscription.set_routeId(rs.getInt("routeId"));
				subscription.set_userId(rs.getInt("userId"));

				// add the object to the list
				allSubscriptions.add(subscription);
			}

			// disconnect from the database
			this.conn = daoAgent.disconnectDB(conn);
		} catch (SQLException sx) {
			daoAgent.displayException(sx);

		}
		return allSubscriptions;
	}

	@Override
	public SubscribedTo getSubscription(int subscriptionId) {
		// list to store all the subscriptions
		SubscribedTo subscription = new SubscribedTo();

		// list all the subscriptions
		String sql = "SELECT * FROM subscribedTo where id = ?;";
		try {

			// connecting the connectDB
			this.conn = daoAgent.connectDB(this.conn, databaseName);

			// create the statement
			this.pstmt = this.conn.prepareStatement(sql);

			this.pstmt.setInt(1, subscriptionId);
			// Execute and store
			this.rs = this.pstmt.executeQuery();

			// convert all the results into java objects
			while (rs.next()) {
				// instantiate a new Alert
				subscription = new SubscribedTo();

				// populate the properties of the object from the database
				subscription.set_id(rs.getInt("id"));
				subscription.set_routeId(rs.getInt("id"));
				subscription.set_routeId(rs.getInt("routeId"));
				subscription.set_userId(rs.getInt("userId"));

				// add the object to the list
			}

			// disconnect from the database
			this.conn = daoAgent.disconnectDB(conn);
		} catch (SQLException sx) {
			daoAgent.displayException(sx);

		}
		return subscription;
	}

	@Override
	public boolean createSubscription(SubscribedTo newSubscription) {
		String sql = "INSERT INTO subscribedTo (routeId, userId) values (?,?);";
		int count = -1;
		try {
			// get connect to database
			this.conn = daoAgent.connectDB(this.conn, databaseName);
			// create the prepare statement
			this.pstmt = this.conn.prepareStatement(sql);
			// set parameters
			this.pstmt.setInt(1, newSubscription.get_routeId());
			this.pstmt.setInt(2, newSubscription.get_userId());

			// execute
			this.pstmt.execute();
			count = this.pstmt.getResultSetType();

			// disconnect
			this.conn = daoAgent.disconnectDB(this.conn);

		} catch (SQLException sx) {
			daoAgent.displayException(sx);
		}

		return (count > 0);

	}

	@Override
	public boolean updateSubscription(SubscribedTo updatedSubscription) {
		String sql = "UPDATE subscribedTo set routeId=?, userId=? WHERE id=?;";
		int count = -1;
		
		try {
			// connect to the database
			this.conn = daoAgent.connectDB(this.conn, databaseName);
			// create the prepare statement
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, updatedSubscription.get_routeId());
			this.pstmt.setInt(2, updatedSubscription.get_userId());
			this.pstmt.setInt(3, updatedSubscription.get_id());
			
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
	public boolean deleteSubscription(SubscribedTo deletedSubscription) {
		// create a query to delete one
		String sql = "DELETE FROM subscribedTo WHERE id=?;";
		
		int effectRow = 0;

		try {
			// connect to the database
			this.conn = daoAgent.connectDB(this.conn, databaseName);
			// create a prepare statement
			this.pstmt = this.conn.prepareStatement(sql);
			// set the parameter
			this.pstmt.setInt(1, deletedSubscription.get_id());

			// execute and get effect row it should be 1 if execute successfully
			effectRow = this.pstmt.executeUpdate();		

			// disconnect
			this.conn = daoAgent.disconnectDB(this.conn);

		} catch (SQLException sx) {
			daoAgent.displayException(sx);
		}
		
		return effectRow > 0;
	}

	@Override
	public ArrayList<SubscribedTo> getUsersSubscribedTo(Route route) {
		// list to store all the subscriptions
		ArrayList<SubscribedTo> routeSubscriptions = new ArrayList<>();

		// list all the subscriptions
		String sql = "SELECT * FROM subscribedTo where routeId = ?;";
		try {

			// connecting the connectDB
			this.conn = daoAgent.connectDB(this.conn, databaseName);

			// create the statement
			this.pstmt = this.conn.prepareStatement(sql);

			this.pstmt.setInt(1, route.get_id());
			// Execute and store
			this.rs = this.pstmt.executeQuery();

			// convert all the results into java objects
			while (rs.next()) {
				// instantiate a new Alert
				SubscribedTo subscription = new SubscribedTo();

				// populate the properties of the object from the database
				subscription.set_id(rs.getInt("id"));
				subscription.set_routeId(rs.getInt("id"));
				subscription.set_routeId(rs.getInt("routeId"));
				subscription.set_userId(rs.getInt("userId"));

				// add the object to the list
				routeSubscriptions.add(subscription);
			}

			// disconnect from the database
			this.conn = daoAgent.disconnectDB(conn);
		} catch (SQLException sx) {
			daoAgent.displayException(sx);

		}
		return routeSubscriptions;
	}

	@Override
	public ArrayList<SubscribedTo> getSubscriptionForUser(User givenUser) {
		// list to store all the subscriptions
		ArrayList<SubscribedTo> routeSubscriptions = new ArrayList<>();

		// list all the subscriptions
		String sql = "SELECT * FROM subscribedTo where userId = ?;";
		try {

			// connecting the connectDB
			this.conn = daoAgent.connectDB(this.conn, databaseName);

			// create the statement
			this.pstmt = this.conn.prepareStatement(sql);

			this.pstmt.setInt(1, givenUser.get_id());
			// Execute and store
			this.rs = this.pstmt.executeQuery();

			// convert all the results into java objects
			while (rs.next()) {
				// instantiate a new Alert
				SubscribedTo subscription = new SubscribedTo();

				// populate the properties of the object from the database
				subscription.set_id(rs.getInt("id"));
				subscription.set_routeId(rs.getInt("id"));
				subscription.set_routeId(rs.getInt("routeId"));
				subscription.set_userId(rs.getInt("userId"));

				// add the object to the list
				routeSubscriptions.add(subscription);
			}

			// disconnect from the database
			this.conn = daoAgent.disconnectDB(conn);
		} catch (SQLException sx) {
			daoAgent.displayException(sx);

		}
		return routeSubscriptions;
	}
}
