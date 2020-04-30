package idAssigners;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.MySqlDatabaseConnectionManager;

public class MySqlBiggestIDGetter{

	MySqlDatabaseConnectionManager dcm;
	Connection con;
	
	public MySqlBiggestIDGetter(MySqlDatabaseConnectionManager dcm) throws ClassNotFoundException, SQLException {
		this.dcm = dcm;
		con = dcm.getConnection();
	}
	
	public int getBiggestPersonID() throws SQLException {
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select max(person_id) from people");
		if(rs.next()) {
			return rs.getInt(1);
		} else {
			return 0;
		}
	}
	
}
