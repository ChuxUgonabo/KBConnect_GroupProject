/**
 * 
 */
package com.kbconnect.boundary;

import java.sql.*;
import java.util.ArrayList;

import com.kbconnect.entity.TravelPass;

/**
 * @author Gursewak Singh
 *
 */
public class TravelPassDAO implements TravelPassDAOInterface {

	private Connection conn = null;
	private ResultSet rs = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private String databaseName = "kbconnect";
	private DAOAgent doaAgent = new DAOAgent();

	@Override
	public ArrayList<TravelPass> getAllPasses() {
		ArrayList<TravelPass> allPasses = new ArrayList<TravelPass>();

		// connect to the database
		this.conn = doaAgent.connectDB(conn, databaseName);

		// sql query to get all passes
		String sqlQuery = "Select * from travelPass";

		try {

			// create a statement
			this.stmt = this.conn.createStatement();

			// execute the statement and store the results as a sql result set
			this.rs = this.stmt.executeQuery(sqlQuery);

			// turn all the results into java objects
			while (this.rs.next()) {

				// instantiate a new TravelPass
				TravelPass ts = new TravelPass();
				ts.set_id(rs.getInt("id"));
				ts.set_passDuration(rs.getString("passDuration"));
				ts.set_price(rs.getDouble("price"));
				ts.set_passType(rs.getString("passType"));

				// add the pass to the array list
				allPasses.add(ts);
			}
		} catch (SQLException sx) {
			System.out.println("Database Error");
			System.out.println(sx.getMessage());
			System.out.println(sx.getErrorCode());
			System.out.println(sx.getSQLState());
		}
		// disconnect the database connection
		this.conn = doaAgent.disconnectDB(conn);
		return allPasses;
	}

	@Override
	public TravelPass getPass(int id) {

		TravelPass requestedPass = null;

		// connect to the database
		this.conn = this.doaAgent.connectDB(conn, databaseName);

		// sql query to get all passes
		String sqlQuery = "Select * FROM travelPass WHERE id = ?";

		try {

			// create a statement
			this.pstmt = this.conn.prepareStatement(sqlQuery);

			this.pstmt.setInt(1, id);

			// execute the statement and store the results as a sql result set
			this.rs = this.pstmt.executeQuery(sqlQuery);

			// turn all the results into java objects
			while (this.rs.next()) {

				// instantiate a new TravelPass
				requestedPass = new TravelPass();
				requestedPass.set_id(rs.getInt("id"));
				requestedPass.set_passDuration(rs.getString("passDuration"));
				requestedPass.set_price(rs.getDouble("price"));
				requestedPass.set_passType(rs.getString("passType"));

			}
		} catch (SQLException sx) {
			System.out.println("Database Error");
			System.out.println(sx.getMessage());
			System.out.println(sx.getErrorCode());
			System.out.println(sx.getSQLState());
		}

		// disconnect the database connection
		this.conn = doaAgent.disconnectDB(conn);

		return requestedPass;
	}

	@Override
	public boolean deletePass(int id) {

		// connect to the database
		this.conn = this.doaAgent.connectDB(conn, databaseName);

		// sql query to get all passes
		String sqlQuery = "DELETE FROM travelPass WHERE id=" + id;
		boolean successful = false;

		try {

			// create a statement
			this.stmt = this.conn.createStatement();

			// execute the statement and store the results as a sql result set
			successful = this.stmt.execute(sqlQuery);

		} catch (SQLException sx) {
			System.out.println("Database Error");
			System.out.println(sx.getMessage());
			System.out.println(sx.getErrorCode());
			System.out.println(sx.getSQLState());
		}
		
		// disconnect the database connection
		this.conn = doaAgent.disconnectDB(conn);

		return successful;
	}

	@Override
	public boolean createPass(TravelPass pass) {

		// connect to the database
		this.conn = this.doaAgent.connectDB(conn, databaseName);

		// sql query to get all passes
		String sqlInsert = "INSERT INTO travelPass " + "(passDuration, passType, price)" + "VALUES (?, ?, ?)";

		ResultSet generatedId = null;

		try {

			// create a statement
			this.pstmt = this.conn.prepareStatement(sqlInsert);

			// bind the query
			this.pstmt.setString(1, pass.get_passDuration());
			this.pstmt.setString(2, pass.get_passType());
			this.pstmt.setDouble(3, pass.get_price());
			// execute the statement and store the results as a sql result set
			this.rs = this.pstmt.executeQuery();

			generatedId = this.pstmt.getGeneratedKeys();

		} catch (SQLException sx) {
			System.out.println("Database Error");
			System.out.println(sx.getMessage());
			System.out.println(sx.getErrorCode());
			System.out.println(sx.getSQLState());
		}
		
		// disconnect the database connection
		this.conn = doaAgent.disconnectDB(conn);

		return false;
	}

	@Override
	public boolean updatePrice(int passId, int price) {
		// connect to the database
		this.conn = this.doaAgent.connectDB(conn, databaseName);

		// TODO Auto-generated method stub
		
		// disconnect the database connection
		this.conn = this.doaAgent.disconnectDB(conn);

		return false;
	}

    public boolean updatePass( TravelPass editedPass) {
        // connect to the database
        this.conn = this.doaAgent.connectDB(conn, databaseName);

        String sqlUpdate = "UPDATE travelPass SET passDuration=?, passType=?, price=? WHERE id=?";

        int updateCount = 0;

        try {
            // create a statement
            this.pstmt = this.conn.prepareStatement(sqlUpdate);

            // bind the query
            this.pstmt.setString(1, editedPass.get_passDuration());
            this.pstmt.setString(2, editedPass.get_passType());
            this.pstmt.setDouble(3, editedPass.get_price());

            updateCount = this.pstmt.executeUpdate();

        } catch (SQLException sx) {
            this.doaAgent.displayException(sx);
        }

        // disconnect from the database
        this.conn = this.doaAgent.disconnectDB(conn);

        return updateCount > 0;
    }

}
