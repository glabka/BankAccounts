package database;

import custom_exceptions.DatabaseException;

public abstract class TableDropper {
	public abstract void dropTable(String tableName) throws DatabaseException;
	public abstract void dropTables(String[] tableNames) throws DatabaseException;
}
