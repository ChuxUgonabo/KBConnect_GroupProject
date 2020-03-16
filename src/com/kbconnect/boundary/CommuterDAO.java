package com.kbconnect.boundary;

import java.sql.*;
import java.util.ArrayList;

import com.kbconnect.entity.User;

/**
 * 
 * @author 300108357 Weijun Shi
 *
 */

public class CommuterDAO implements UserDaoInterface {


	private Connection conn = null;
	private ResultSet rs = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private String databaseName = "kbconnect";
	private DAOAgent daoAgent = new DAOAgent();

	
	@Override
	public ArrayList<User> getAllUsers() {
		// list to store all the users
		ArrayList<User> allUsers = new ArrayList<User>();

		// list all the Users
		String sql = "SELECT * FROM users;";
		try {

			// connecting the connectDB
			this.conn = daoAgent.connectDB(this.conn, databaseName);

			// create the statement
			this.stmt = this.conn.createStatement();

			// Execute and store
			this.rs = this.stmt.executeQuery(sql);

			// convert all the results into java objects
			while (rs.next()) {
				// instantiate a new User
				User user = new User();

				// populate the properties of the object from the database
				user.set_id(rs.getInt("id"));
				user.set_fullName(rs.getString("fullName"));
				user.set_username(rs.getString("username"));
				user.set_email(rs.getString("email"));
				user.set_password(rs.getString("password"));
				user.set_address(rs.getString("address"));
				user.set_DOB(rs.getDate("DOB"));
				user.set_cardNumber(rs.getString("cardNumber"));

				// add the object to the list
				allUsers.add(user);
			}

			// disconnect from the database
			this.conn = daoAgent.disconnectDB(conn);
		} catch (SQLException sx) {
			daoAgent.displayException(sx);

		}
		return allUsers;
	}

	@Override
	public User getUser(int id) {
		// Instantiate the object of student
		User user = new User();

		// get one object of student by condition of email
		String sql = "SELECT * FROM users where id=?;";
		try {

			// connecting the connectDB
//			this.conn = doaAgent.connectDB(conn, databaseName);
			this.conn = daoAgent.connectDB(this.conn, databaseName);
			// create the prepared statement
			this.pstmt = this.conn.prepareStatement(sql);
			// set the parameter for the query
			this.pstmt.setInt(1, id);
			// Execute
			this.rs = this.pstmt.executeQuery();
			while (rs.next()) {
				// populate the properties of the object from the database
				user.set_id(rs.getInt("id"));
				user.set_fullName(rs.getString("fullName"));
				user.set_username(rs.getString("username"));
				user.set_email(rs.getString("email"));
				user.set_password(rs.getString("password"));
				user.set_address(rs.getString("address"));
				user.set_DOB(rs.getDate("DOB"));
				user.set_cardNumber(rs.getString("cardNumber"));

			}

			// disconnect from the database
			this.conn = daoAgent.disconnectDB(this.conn);

		} catch (SQLException sx) {
			daoAgent.displayException(sx);
		}
		return user;
	}

	public User getUser(String username) {
		// Instantiate the object of student
		User user = new User();
		boolean successful = false;

		// get one object of student by condition of email
		String sql = "SELECT * FROM users where username=?;";
		try {

			// connecting the connectDB
			this.conn = daoAgent.connectDB(this.conn, databaseName);
			// create the prepared statement
			this.pstmt = this.conn.prepareStatement(sql);
			// set the parameter for the query
			this.pstmt.setString(1, username);
			// Execute
			this.pstmt.execute();

			this.rs = this.pstmt.getResultSet();
			while (rs.next()) {
				successful = true;
				// populate the properties of the object from the database
				user.set_id(rs.getInt("id"));
				user.set_fullName(rs.getString("fullName"));
				user.set_username(rs.getString("username"));
				user.set_email(rs.getString("email"));

				user.set_password(rs.getString("password"));
				user.set_address(rs.getString("address"));
				user.set_DOB(rs.getDate("DOB"));
//				user.set_cardNumber(rs.getString("cardNumber"));
			}
			if (!successful) {

				user = null;
			}

			// disconnect from the database
//			this.conn = doaAgent.disconnectDB(conn);
			this.conn = daoAgent.disconnectDB(this.conn);

		} catch (SQLException sx) {
			daoAgent.displayException(sx);
		}

		return user;
	}

	@Override
	public boolean updateUser(User newUser) {
		String sql = "UPDATE users set  fullName=?, username=?, email=?, password=?, address=?, DOB=?, cardNumber=? WHERE id=?;";
		int count = -1;
		;
		try {
			// connect to the database
//			this.conn = doaAgent.connectDB(conn, databaseName);
			this.conn = daoAgent.connectDB(this.conn, databaseName);
			// create the prepare statement
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, newUser.get_fullName());
			this.pstmt.setString(2, newUser.get_username());
			this.pstmt.setString(3, newUser.get_email());

			this.pstmt.setString(4, newUser.get_password());
			this.pstmt.setString(5, newUser.get_address());
			this.pstmt.setDate(6, newUser.get_DOB());
			this.pstmt.setString(7, newUser.get_cardNumber());
			this.pstmt.setString(8, String.valueOf(newUser.get_id()));

			this.pstmt.execute();

			count = this.pstmt.getUpdateCount();

			// disconnect from the database
//			this.conn = doaAgent.disconnectDB(conn);
			this.conn = daoAgent.disconnectDB(this.conn);

		} catch (SQLException sx) {
			daoAgent.displayException(sx);
		}

		return count > 0;
	}

	@Override
	public void deleteUser(User currUser) {
		// create a query to delete one
		String sql = "DELETE FROM users WHERE id=?;";
		try {
			// connect to the database
//			this.conn = doaAgent.connectDB(conn, databaseName);
			this.conn = daoAgent.connectDB(this.conn, databaseName);
			// create a prepare statement
			this.pstmt = this.conn.prepareStatement(sql);
			// set the parameter
			this.pstmt.setString(1, String.valueOf(currUser.get_id()));
			// execute
			this.pstmt.execute();

			// disconnect
//			this.conn = doaAgent.disconnectDB(conn);
			this.conn = daoAgent.disconnectDB(this.conn);

		} catch (SQLException sx) {
			daoAgent.displayException(sx);
		}
	}

	@Override
	public boolean createUser(User newUser) {
		// create a query to insert one
		String sql = "INSERT INTO users (fullName,username, email, password, address, DOB, cardNumber) values (?,?,?,?,?,?,?);";
		int count = -1;
		try {
			// get connect to database
//			this.conn = doaAgent.connectDB(conn, databaseName);
			this.conn = daoAgent.connectDB(this.conn, databaseName);
			// create the prepare statement
			this.pstmt = this.conn.prepareStatement(sql);
			// set parameters
			this.pstmt.setString(1, newUser.get_fullName());
			this.pstmt.setString(2, newUser.get_username());
			this.pstmt.setString(3, newUser.get_email());

			this.pstmt.setString(4, newUser.get_password());
			this.pstmt.setString(5, newUser.get_address());
			this.pstmt.setDate(6, newUser.get_DOB());
			this.pstmt.setString(7, newUser.get_cardNumber());
			// execute
			this.pstmt.execute();
			count = this.pstmt.getResultSetType();

			// disconnect
//			this.conn = doaAgent.disconnectDB(conn);
			this.conn = daoAgent.disconnectDB(this.conn);

		} catch (SQLException sx) {
			daoAgent.displayException(sx);
		}

		return count > 0;

	}

}
