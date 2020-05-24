package database;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import custom_exceptions.DatabaseException;
import dao.GenericDAO;

public class DatabaseSetup {

	/**
	 * Calls DAOs' method setUpDatabase().
	 * @param daos list of Data Access Objects
	 * @throws DatabaseException
	 */
	public static void setUpDatabase(List<GenericDAO<?>> daos) throws DatabaseException {
		setUpDatabase(daos, null);
	}

	
	/**
	 * Calls DAOs' method setUpDatabase() but before that gets DAOs' tableNames and drop these
	 * tables if td != null.
	 * @param daos list of Data Access Objects
	 * @param td table dropper
	 * @throws DatabaseException
	 */
	public static void setUpDatabase(List<GenericDAO<?>> daos, TableDropper td)
			throws DatabaseException {
		if(td != null) {
			List<String[]> listOfArraysOfTableNames = daos.stream().map(dao -> dao.getTableNames()).collect(Collectors.toList());
			td.dropTables(concatIntoOneArray(listOfArraysOfTableNames));
		}
		for(GenericDAO<?> dao : daos) {
			dao.setUpDatabase();
		}
	}
	
	private static String[] concatIntoOneArray(List<String[]> list) {
		String[][] array = list.toArray(new String[0][0]);
		List<String> listForAppending = new LinkedList<String>();
		for(String[] strs: array) {
			listForAppending.addAll(Arrays.asList(strs));
		}
		return listForAppending.toArray(new String[0]);
	}
}
