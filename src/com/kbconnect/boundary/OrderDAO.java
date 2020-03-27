package com.kbconnect.boundary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kbconnect.entity.Admin;
import com.kbconnect.entity.Order;
import com.kbconnect.entity.Product;
import com.kbconnect.entity.User;

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

				order.set_id(this._rs.getInt("orderId"));
				order.set_quantity(this._rs.getInt("quantity"));
				order.set_transactionDate(this._rs.getDate("transactionDate"));

				Product p = new Product();
				ProductDAO pdao = new ProductDAO();
				p = pdao.getProduct(this._rs.getInt("productId"));
				order.set_productOrdered(p);

				User u = new User();
				CommuterDAO udao = new CommuterDAO();
				u = udao.getUser(this._rs.getInt("placedBy"));
				order.set_placedBy(u);

				Admin admin = new Admin();
				AdminDAO adao = new AdminDAO();
				admin = adao.getUser(this._rs.getInt("approvedBy"));
				order.set_approvedBy(admin);
				
				order.set_approvalStatus(this._rs.getBoolean("approvalStatus"));

				currList.add(order);

			}
			// after executing , close the connection
			this._conn = daoAgent.disconnectDB(this._conn);

		} catch (SQLException sx) {
			daoAgent.displayException(sx);
		}

		return currList;
	}
	
	public ArrayList<Order> getAllUserOrders(int currentUserId) {
		// create mySQL query to get all
		String sql = "SELECT * FROM orders WHERE placedBy=?;";
		// define an arrayList to store orders
		ArrayList<Order> currList = new ArrayList<Order>();
		try {
			// connect the database
			this._conn = daoAgent.connectDB(this._conn, databaseName);
			
			this._pstmt = this._conn.prepareStatement(sql);
			this._pstmt.setInt(1, currentUserId);
			this._rs = this._pstmt.executeQuery();


			while (this._rs.next()) {
				// instantiate new order
				Order order = new Order();

				order.set_id(this._rs.getInt("orderId"));
				order.set_quantity(this._rs.getInt("quantity"));
				order.set_transactionDate(this._rs.getDate("transactionDate"));

				Product p = new Product();
				ProductDAO pdao = new ProductDAO();
				p = pdao.getProduct(this._rs.getInt("productId"));
				order.set_productOrdered(p);

				User u = new User();
				CommuterDAO udao = new CommuterDAO();
				u = udao.getUser(this._rs.getInt("placedBy"));
				order.set_placedBy(u);

				Admin admin = new Admin();
				AdminDAO adao = new AdminDAO();
				admin = adao.getUser(this._rs.getInt("approvedBy"));
				order.set_approvedBy(admin);
				
				order.set_approvalStatus(this._rs.getBoolean("approvalStatus"));

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
		String sql = "SELECT * FROM orders WHERE orderId=?;";
		// instantiate new Order
		Order currOrder = new Order();
		try {
			// connect the database
			this._conn = daoAgent.connectDB(this._conn, databaseName);

			this._pstmt = this._conn.prepareStatement(sql);
			this._pstmt.setInt(1, orderId);
			this._rs = this._pstmt.executeQuery();
			while (this._rs.next()) {
				currOrder.set_id(this._rs.getInt("orderId"));
				currOrder.set_quantity(this._rs.getInt("quantity"));
				currOrder.set_transactionDate(this._rs.getDate("transactionDate"));

				Product p = new Product();
				ProductDAO pdao = new ProductDAO();
				p = pdao.getProduct(this._rs.getInt("productId"));
				currOrder.set_productOrdered(p);

				User u = new User();
				CommuterDAO udao = new CommuterDAO();
				u = udao.getUser(this._rs.getInt("placedBy"));
				currOrder.set_placedBy(u);

				Admin admin = new Admin();
				AdminDAO adao = new AdminDAO();
				admin = adao.getUser(this._rs.getInt("approvedBy"));
				currOrder.set_approvedBy(admin);
				
				currOrder.set_approvalStatus(this._rs.getBoolean("approvalStatus"));
			

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
		String sql = "INSERT INTO orders (quantity,transactionDate,productId,placedBy,approvalStatus) VALUES(?,?,?,?,?);";
		// sentinel
		int effectRow = 0;
		try {
			// connect the database
			this._conn = daoAgent.connectDB(this._conn, databaseName);
			// set the variables
			this._pstmt = this._conn.prepareStatement(sql);

			this._pstmt.setInt(1, newOrder.get_quantity());
			this._pstmt.setDate(2, newOrder.get_transactionDate());
			this._pstmt.setInt(3, newOrder.get_productOrdered().get_id());
			this._pstmt.setInt(4, newOrder.get_placedBy().get_id());
			this._pstmt.setBoolean(5, newOrder.is_approvalStatus());
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
		String sql = "UPDATE orders SET quantity=?, transactionDate=?, productId=?, placedBy=? WHERE orderId=?;";
		// sentinel
		int effectRow = 0;
		try {
			// connect the database
			this._conn = daoAgent.connectDB(this._conn, databaseName);
			// set the variables
			this._pstmt = this._conn.prepareStatement(sql);

			this._pstmt.setInt(1, updatedOrder.get_quantity());
			this._pstmt.setDate(2, updatedOrder.get_transactionDate());
			this._pstmt.setInt(3, updatedOrder.get_productOrdered().get_id());
			this._pstmt.setInt(4, updatedOrder.get_placedBy().get_id());
//			this._pstmt.setInt(5, updatedOrder.get_approvedBy().get_id());

			this._pstmt.setInt(5, updatedOrder.get_id());

			// execute and get effect row it should be 1 if execute successfully
			effectRow = this._pstmt.executeUpdate();

			// disconnect the database
			this._conn = daoAgent.disconnectDB(this._conn);

		} catch (SQLException sx) {
			daoAgent.displayException(sx);
		}

		return effectRow > 0;
	}
	
	public boolean updateApproval(Order updatedOrder) {
		// Create mySql query to update current one on database
		String sql = "UPDATE orders SET quantity=?, transactionDate=?, productId=?, placedBy=?, approvedBy=?, approvalStatus=? WHERE orderId=?;";
		// sentinel
		int effectRow = 0;
		try {
			// connect the database
			this._conn = daoAgent.connectDB(this._conn, databaseName);
			// set the variables
			this._pstmt = this._conn.prepareStatement(sql);

			this._pstmt.setInt(1, updatedOrder.get_quantity());
			this._pstmt.setDate(2, updatedOrder.get_transactionDate());
			this._pstmt.setInt(3, updatedOrder.get_productOrdered().get_id());
			this._pstmt.setInt(4, updatedOrder.get_placedBy().get_id());
			this._pstmt.setInt(5, updatedOrder.get_approvedBy().get_id());
			this._pstmt.setBoolean(6, updatedOrder.is_approvalStatus());
			this._pstmt.setInt(7, updatedOrder.get_id());

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
		String sql = "DELETE FROM orders WHERE orderId=?;";
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
