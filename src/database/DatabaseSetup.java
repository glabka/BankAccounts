package database;

import java.util.List;

import custom_exceptions.DatabaseException;
import dao.GenericDAO;

public class DatabaseSetup {

	public static void setUpDatabase(List<GenericDAO<?>> daos) throws DatabaseException {
		setUpDatabse(daos, false);
	}

	
	public static void setUpDatabse(List<GenericDAO<?>> daos, boolean forced)
			throws DatabaseException {
		for(GenericDAO<?> dao : daos) {
			dao.setUpDatabase(forced);
		}
	}
}
