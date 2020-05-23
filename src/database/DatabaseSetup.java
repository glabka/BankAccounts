package database;

import java.util.List;
import java.util.stream.Collectors;

import custom_exceptions.DatabaseException;
import dao.GenericDAO;

public class DatabaseSetup {

	public static void setUpDatabase(List<GenericDAO<?>> daos) throws DatabaseException {
		setUpDatabase(daos, false, null);
	}

	
	public static void setUpDatabase(List<GenericDAO<?>> daos, boolean forced, TableDropper td)
			throws DatabaseException {
		if(forced) {
			List<String> listOfTableNames = daos.stream().map(dao -> dao.getTableName()).collect(Collectors.toList());
			td.dropTables(listOfTableNames.toArray(new String[listOfTableNames.size()]));
		}
		for(GenericDAO<?> dao : daos) {
			dao.setUpDatabase();
		}
	}	
}
