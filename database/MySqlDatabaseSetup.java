package database;

import customExceptions.DatabaseException;
import dao.MySqlPersonDAO;
import dao.PersonDAO;

public class MySqlDatabaseSetup {

	public static void setUpDatabase(MySqlDatabaseConnectionManager dcm) throws ClassNotFoundException, DatabaseException {
		setUpDatabse(dcm, false);
	}

	
	public static void setUpDatabse(MySqlDatabaseConnectionManager dcm, boolean forced)
			throws ClassNotFoundException, DatabaseException {
		PersonDAO personDAO = new MySqlPersonDAO(dcm);
		personDAO.setUpDatabase(forced);
	}
}
