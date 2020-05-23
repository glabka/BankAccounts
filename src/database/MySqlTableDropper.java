package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import custom_exceptions.DatabaseException;
import dao.mysql.MySqlCommFun;

public class MySqlTableDropper extends TableDropper {
	
	private MySqlDatabaseConnectionManager dcm;
	private Connection con;
	private Statement stmt;
	
	public MySqlTableDropper(MySqlDatabaseConnectionManager mySqlDcm) throws ClassNotFoundException, DatabaseException {
		this.dcm = mySqlDcm;
		con = dcm.getConnection();
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
	}
	
	@Override
	public void dropTable(String tableName) throws DatabaseException {
		try {
			stmt.executeUpdate(MySqlCommFun.DROP_TABLE_IE + tableName);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
		
	}

	@Override
	public void dropTables(String[] tableNames) throws DatabaseException {
		try {
			stmt.executeUpdate(MySqlCommFun.DROP_TABLE_IE + separateByCommas(tableNames));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
		
	}

	public static String separateByCommas(String[] strings) {
		String str = strings[0];
		for (int i = 1; i < strings.length; i++) {
			str += ", " + strings[i];
		}
		return str;
	}
}
