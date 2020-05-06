package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlDatabaseSetup {

	public static void setUpDatabase(MySqlDatabaseConnectionManager dcm) throws ClassNotFoundException, SQLException {
		setUpDatabse(dcm, false);
	}

	public static void setUpDatabse(MySqlDatabaseConnectionManager dcm, boolean forced)
			throws ClassNotFoundException, SQLException {
		Connection con = dcm.getConnection();
		Statement stmt = con.createStatement();

		String createCMDPrefix = "create table if not exists ";
		String createCMDSuffixPeople = " (person_id int(11), first_name varchar(30) not null, middle_name varchar(30), "
				+ "last_name varchar(30) not null, primary key (person_id))";
		String tableName1 = "people";
		String tableName2 = "bank_accounts";
		if (forced) {
			String dropCMD = "drop table if exists ";
			stmt.executeUpdate(dropCMD + tableName1);
			stmt.executeUpdate(createCMDPrefix + tableName1 + createCMDSuffixPeople);
		} else {
			stmt.executeUpdate(createCMDPrefix + tableName1 + createCMDSuffixPeople);
		}
	}
}
