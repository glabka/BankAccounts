package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import customExceptions.DatabaseException;

public class MySqlDatabaseConnectionManager {

	Connection con;
	
	public Connection getConnection() throws ClassNotFoundException, DatabaseException {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb?characterEncoding=utf8", "root", "root");
			} catch (SQLException e) {
				throw new DatabaseException(e.getMessage());
			}
			return con;
	}
	
	public void disconnect() throws DatabaseException {
		try {
			if(con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
}
