/**
 * 
 */
package com.kbconnect.boundary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Gursewak Singh
 *
 */
public class DAOAgent {

	private String _dsn;
	private String _username = "root";
	private String _password = "11ne7l13rP";

	public Connection connectDB(Connection conn, String databaseName) {
		
		_dsn = "jdbc:mysql://localhost/"+ databaseName + "?useLegacyDatetimeCode=false&serverTimezone=UTC";

		try {
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			conn = DriverManager.getConnection(this._dsn, this._username, this._password);
			if (conn.isClosed()) {
				System.out.println("Database Connection not established");
			} else {
				System.out.println("Database Connection established");
			}
		} catch (SQLException sx) {
			displayException(sx);
		}
		return conn;
	}

	public Connection disconnectDB(Connection conn) {
		try {
			conn.close();

			if (conn.isClosed()) {
				System.out.println("Database Connection closed");
			} else {
				System.out.println("Database Connection is STILL OPEN!");
			}
		} catch (SQLException sx) {
			displayException(sx);
		}
		return conn;
	}

	public void displayException(SQLException sx) {
		System.out.println("Database connection error");
		System.out.println(sx.getMessage());
		System.out.println(sx.getErrorCode());
		System.out.println(sx.getSQLState());
	}

}
