package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlDatabaseConnectionManager {

	Connection con;
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb?characterEncoding=utf8", "root", "root");
			return con;
	}
	
	public void disconnect() throws SQLException{
		if(con != null && !con.isClosed()) {
			con.close();
		}
	}
}
