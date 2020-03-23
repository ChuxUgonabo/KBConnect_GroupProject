package com.kbconnect.boundary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kbconnect.entity.Order;

/**
 * create the OrderDAO class to implement CRUD with database
 * 
 * @author Weijun Shi
 *
 */
public class OrderDAO implements OrderDAOInterface {

	private Connection _conn = null;
	private Statement _stmt = null;
	private PreparedStatement _pstmt = null;
	private ResultSet _rs = null;

	// instantiate DAOAgent to get the connection and disconnection
	DAOAgent daoAgent = new DAOAgent();
	private String databaseName = "kbconnect";

	/**
	 * @return an ArrayList
	 */
	@Override
	public ArrayList<Order> getAllOrders() {
		// create mySQL query to get all
		String sql = "SELECT * FROM orders;";
		// define an arrayList to store orders
		ArrayList<Order> currList = new ArrayList<Order>();
		try {
			// connect the database
			this._conn = daoAgent.connectDB(this._conn, databaseName);

			this._stmt = this._conn.createStatement();
			this._rs = this._stmt.executeQuery(sql);
			while (this._rs.next()) {
				// instantiate new order
				Order order = new Order();
				order.set_id(this._rs.getInt("id"));
				order.set_description(this._rs.getString("description"));
				order.set_quantity(this._rs.getInt("quantity"));
				order.set_unitPrice(this._rs.getDouble("unitPrice"));
				order.set_amount(this._rs.getDouble("amount"));
				order.set_transactionDate(this._rs.getDate("transactionDate"));

				currList.add(order);

			}
			// after executing , close the connection
			this._conn = daoAgent.disconnectDB(this._conn);

		} catch (SQLException sx) {
			daoAgent.displayException(sx);
		}

		return currList;
	}

	/**
	 * @return an order of object
	 */
	@Override
	public Order getOrder(int orderId) {
		// create mySQL query to get one by ID
		String sql = "SELECT * FROM orders WHERE id=?;";
		// instantiate new Order
		Order currOrder = new Order();
		try {
			// connect the database
			this._conn = daoAgent.connectDB(this._conn, databaseName);

			this._pstmt = this._conn.prepareStatement(sql);
			this._pstmt.setInt(1, orderId);
			this._rs = this._stmt.executeQuery(sql);
			while (this._rs.next()) {
				currOrder.set_id(this._rs.getInt("id"));
				currOrder.set_description(this._rs.getString("description"));
				currOrder.set_quantity(this._rs.getInt("quantity"));
				currOrder.set_unitPrice(this._rs.getDouble("unitPrice"));
				currOrder.set_amount(this._rs.getDouble("amount"));
				currOrder.set_transactionDate(this._rs.getDate("transactionDate"));

			}
			// after executing , close the connection
			this._conn = daoAgent.disconnectDB(this._conn);

		} catch (SQLException sx) {
			daoAgent.displayException(sx);
		}

		return currOrder;
	}

	/**
	 * @return boolean value, if process successfully return true,otherwise false
	 */
	@Override
	public boolean createOrder(Order newOrder) {
		// Create mySql query to insert new one to database
		String sql = "INSERT INTO orders (description,quantity,unitPrice,amount,transactionDate) VALUES(?,?,?,?,?);";
		// sentinel
		int effectRow = 0;
		try {
			// connect the database
			this._conn = daoAgent.connectDB(this._conn, databaseName);
			// set the variables
			this._pstmt = this._conn.prepareStatement(sql);
			this._pstmt.setString(1, newOrder.get_description());
			this._pstmt.setInt(2, newOrder.get_quantity());
			this._pstmt.setDouble(3, newOrder.get_unitPrice());
			this._pstmt.setDouble(4, newOrder.get_amount());
			this._pstmt.setDate(5, newOrder.get_transactionDate());

			// execute and get effect row it should be 1 if execute successfully
			effectRow = this._pstmt.executeUpdate();

			// disconnect the database
			this._conn = daoAgent.disconnectDB(this._conn);

		} catch (SQLException sx) {
			daoAgent.displayException(sx);
		}

		return effectRow > 0;
	}

	/**
	 * @return boolean value, if process successfully return true,otherwise false
	 */

	@Override
	public boolean updateOrder(Order updatedOrder) {
		// Create mySql query to update current one on database
		String sql = "UPDATE orders SET description=?, quantity=?, unitPrice=?, amount=?, transactionDate=? WHERE id=?;";
		// sentinel
		int effectRow = 0;
		try {
			// connect the database
			this._conn = daoAgent.connectDB(this._conn, databaseName);
			// set the variables
			this._pstmt = this._conn.prepareStatement(sql);
			this._pstmt.setString(1, updatedOrder.get_description());
			this._pstmt.setInt(2, updatedOrder.get_quantity());
			this._pstmt.setDouble(3, updatedOrder.get_unitPrice());
			this._pstmt.setDouble(4, updatedOrder.get_amount());
			this._pstmt.setDate(5, updatedOrder.get_transactionDate());
			this._pstmt.setInt(6, updatedOrder.get_id());

			// execute and get effect row it should be 1 if execute successfully
			effectRow = this._pstmt.executeUpdate();

			// disconnect the database
			this._conn = daoAgent.disconnectDB(this._conn);

		} catch (SQLException sx) {
			daoAgent.displayException(sx);
		}

		return effectRow > 0;
	}

	/**
	 * @return boolean value, if process successfully return true,otherwise false
	 */
	@Override
	public boolean deleteOrder(Order deletedOrder) {
		// Create mySql query to delete current one on database
		String sql = "DELETE FROM orders WHERE id=?;";
		// sentinel
		int effectRow = 0;
		try {
			// connect the database
			this._conn = daoAgent.connectDB(this._conn, databaseName);
			// set the variables
			this._pstmt = this._conn.prepareStatement(sql);

			this._pstmt.setInt(1, deletedOrder.get_id());

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
