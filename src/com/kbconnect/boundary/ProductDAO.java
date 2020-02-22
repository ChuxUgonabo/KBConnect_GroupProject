package com.kbconnect.boundary;

import java.sql.*;
import java.util.ArrayList;

import com.kbconnect.entity.Product;

/**
 * 
 * @author 300108357 Weijun Shi
 *
 */

public class ProductDAO implements ProductDAOInterface {

	private Connection _conn = null;
	private Statement _stmt = null;
	private PreparedStatement _pstmt = null;
	private ResultSet _rs = null;

	// instantiate DAOAgent to get the connection and disconnection
	DAOAgent daoAgent = new DAOAgent();
	private String databaseName = "kbconnect";

	@Override
	public ArrayList<Product> getAllProducts() {
		// create mySQL query to get all
		String sql = "SELECT * FROM products;";
		// define an arrayList to store products
		ArrayList<Product> currList = new ArrayList<Product>();
		try {
			// connect the database
			this._conn = daoAgent.connectDB(this._conn, databaseName);

			this._stmt = this._conn.createStatement();
			this._rs = this._stmt.executeQuery(sql);
			while (this._rs.next()) {
				// instantiate new product
				Product p = new Product();
				p.set_id(this._rs.getInt("id"));
				p.set_description(this._rs.getString("description"));
				p.set_price(this._rs.getDouble("price"));
				p.set_type(this._rs.getString("type"));

				currList.add(p);

			}
			// after executing , close the connection
			this._conn = daoAgent.disconnectDB(this._conn);

		} catch (SQLException sx) {
			daoAgent.displayException(sx);
		}

		return currList;
	}

	@Override
	public Product getProduct(int productId) {
		// create mySQL query to get one by ID
		String sql = "SELECT * FROM products WHERE id=" + productId;
		// define new product
		Product currProduct = new Product();
		try {
			// connect the database
			this._conn = daoAgent.connectDB(this._conn, databaseName);

			this._stmt = this._conn.createStatement();
			this._rs = this._stmt.executeQuery(sql);
			while (this._rs.next()) {
				currProduct.set_id(this._rs.getInt("id"));
				currProduct.set_description(this._rs.getString("description"));
				currProduct.set_price(this._rs.getDouble("price"));
				currProduct.set_type(this._rs.getString("type"));

			}
			// after executing , close the connection
			this._conn = daoAgent.disconnectDB(this._conn);

		} catch (SQLException sx) {
			daoAgent.displayException(sx);
		}

		return currProduct;
	}

	@Override
	public boolean createProduct(Product newProduct) {
		// Create mySql query to insert new one to database
		String sql = "INSERT INTO products (description,price,type) VALUES(?,?,?);";
		// sentinel
		int effectRow = 0;
		try {
			// connect the database
			this._conn = daoAgent.connectDB(this._conn, databaseName);
			// set the variables
			this._pstmt = this._conn.prepareStatement(sql);
			this._pstmt.setString(1, newProduct.get_description());
			this._pstmt.setDouble(2, newProduct.get_price());
			this._pstmt.setString(3, newProduct.get_type());

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
	public boolean updateProduct(Product updatedProduct) {
		// Create mySql query to update current one on database
		String sql = "UPDATE products SET description=?, price=?, type=? WHERE id=?;";
		// sentinel
		int effectRow = 0;
		try {
			// connect the database
			this._conn = daoAgent.connectDB(this._conn, databaseName);
			// set the variables
			this._pstmt = this._conn.prepareStatement(sql);
			this._pstmt.setString(1, updatedProduct.get_description());
			this._pstmt.setDouble(2, updatedProduct.get_price());
			this._pstmt.setString(3, updatedProduct.get_type());
			this._pstmt.setInt(4, updatedProduct.get_id());

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
	public boolean deleteProduct(Product deletedProduct) {
		// Create mySql query to delete current one on database
		String sql = "DELETE FROM products WHERE id=?;";
		// sentinel
		int effectRow = 0;
		try {
			// connect the database
			this._conn = daoAgent.connectDB(this._conn, databaseName);
			// set the variables
			this._pstmt = this._conn.prepareStatement(sql);

			this._pstmt.setInt(1, deletedProduct.get_id());

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
