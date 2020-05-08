package idManagers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import customExceptions.DatabaseException;
import database.MySqlDatabaseConnectionManager;

public class MySqlBiggestIDGetter {

	MySqlDatabaseConnectionManager dcm;
	Connection con;

	public MySqlBiggestIDGetter(MySqlDatabaseConnectionManager dcm) throws ClassNotFoundException, DatabaseException {
		this.dcm = dcm;
		con = dcm.getConnection();
	}

	public int getBiggestPersonID() throws DatabaseException {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select max(person_id) from people");
			if (rs.next()) {
				// TODO is not int anymore bur rather String. Probably delete this whole class.
				return rs.getInt(1);
			} else {
				return 0;
			}
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

}
