/**
 * 
 */
package com.kbconnect.boundary;

import java.util.ArrayList;
import java.sql.*;

import com.kbconnect.entity.CompassCard;

/**
 * @author Gursewak Singh
 *
 */
public class CompassCardDAO implements CompassCardDAOInterface {

	private Connection conn = null;
	private ResultSet rs = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private String databaseName = "kbconnect";
	private DAOAgent daoAgent = new DAOAgent();

	@Override
	public ArrayList<CompassCard> getAllCards() {
		ArrayList<CompassCard> allCards = new ArrayList<CompassCard>();

		// the query to get all the cards
		String sqlQuery = "SELECT * FROM compassCards";

		try {

			// connect to the database
			this.conn = this.daoAgent.connectDB(this.conn, databaseName);

			// create the statement
			this.stmt = this.conn.createStatement();

			// execute and store
			this.rs = this.stmt.executeQuery(sqlQuery);

			while (rs.next()) {
				//instantiate a new compass card
				CompassCard card = new CompassCard();

				// populate the card
				card.set_id(rs.getInt("id"));
				card.set_cardNumber(rs.getString("cardNumber"));
				card.set_cvn(rs.getString("cvn"));
				card.set_isActive(rs.getBoolean("isActive"));
				card.set_loadedBalance(rs.getDouble("balance"));
                
                // add the card to the results list
                allCards.add(card);
			}

            // disconnect from the database
            this.conn = this.daoAgent.disconnectDB(this.conn);
		} catch (SQLException sx) {
            displayErrors(sx);
		}
		return allCards;
	}

	@Override
	public CompassCard getCompassCard(String cardNumber) {
        CompassCard card = new CompassCard();

		// the query to get all the cards
		String sqlQuery = "SELECT * FROM compassCards WHERE cardNumber=?";

		try {

			// connect to the database
			this.conn = this.daoAgent.connectDB(conn, databaseName);

			// create the statement
			this.pstmt = this.conn.prepareStatement(sqlQuery);

            // set the parameters
            this.pstmt.setString(1, cardNumber);

			// execute and store
			this.rs = this.pstmt.executeQuery();

			while (rs.next()) {

				// populate the card
				card.set_id(rs.getInt("id"));
				card.set_cardNumber(rs.getString("cardNumber"));
				card.set_cvn(rs.getString("cvn"));
				card.set_isActive(rs.getBoolean("isActive"));
				card.set_loadedBalance(rs.getDouble("balance"));
                
			}

            // disconnect from the database
            this.conn = daoAgent.disconnectDB( this.conn );
		} catch (SQLException sx) {
            displayErrors(sx);
		}

		return card;
	}

	@Override
	public boolean deleteCard(CompassCard card) {

		// create a query to delete one
		String sqlDelete = "DELETE FROM compassCards WHERE cardNumber=?;";

        int rowCount = 0;
		try {
			// connect to the database
			this.conn = daoAgent.connectDB(this.conn, databaseName);
			// create a prepare statement
			this.pstmt = this.conn.prepareStatement(sqlDelete);
			// set the parameter
			this.pstmt.setString(1, card.get_cardNumber());
			// execute
			rowCount = this.pstmt.executeUpdate();

			// disconnect
			this.conn = daoAgent.disconnectDB(this.conn);

		} catch (SQLException sx) {
			System.out.println("Error connectint to the database.");
			System.out.println(sx.getMessage());
			System.out.println(sx.getErrorCode());
			System.out.println(sx.getSQLState());
		}

        if ( rowCount > 0 ) {
            return true;
        } else {
            return false;
        }
	}

	@Override
	public boolean updateCard(CompassCard card) {
		String sqlUpdate = "UPDATE compassCards set  cardNumber=?, cvn=?, isActive=?, balance=? WHERE id=?;";
		int count = -1;

		try {
			// connect to the database
			this.conn = daoAgent.connectDB(this.conn, databaseName);

			// create the prepare statement
			this.pstmt = this.conn.prepareStatement(sqlUpdate);
			this.pstmt.setString(1, card.get_cardNumber());
			this.pstmt.setString(2, card.get_cvn());
			this.pstmt.setBoolean(3, card.is_isActive());
			this.pstmt.setDouble(4, card.get_loadedBalance());
			this.pstmt.setInt(5, card.get_id());

			this.pstmt.execute();

			count = this.pstmt.getUpdateCount();

			// disconnect from the database
			this.conn = daoAgent.disconnectDB(this.conn);

		} catch (SQLException sx) {
            displayErrors(sx);
		}

		return count > 0;
	}

	@Override
	public boolean createCard(CompassCard card) {
		String sqlInsert = "INSERT INTO compassCards (cardNumber, cvn, isActive, balance) Values (?,?,?,?);";
		int count = -1;

		try {
			// connect to the database
			this.conn = daoAgent.connectDB(this.conn, databaseName);

			// create the prepare statement
			this.pstmt = this.conn.prepareStatement(sqlInsert);
			this.pstmt.setString(1, card.get_cardNumber());
			this.pstmt.setString(2, card.get_cvn());
			this.pstmt.setBoolean(3, card.is_isActive());
			this.pstmt.setDouble(4, card.get_loadedBalance());

			this.pstmt.execute();

			count = this.pstmt.getUpdateCount();

			// disconnect from the database
			this.conn = daoAgent.disconnectDB(this.conn);

		} catch (SQLException sx) {
            displayErrors(sx);
		}

		return count > 0;
	}

    private void displayErrors(SQLException sx) {
			System.out.println("Database Error");
			System.out.println(sx.getMessage());
			System.out.println(sx.getErrorCode());
			System.out.println(sx.getSQLState());
    }

}
