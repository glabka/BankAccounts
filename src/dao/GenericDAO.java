package dao;

import custom_exceptions.DatabaseException;

public abstract class GenericDAO<T> {

	public abstract String getTableName();
	public abstract void deleteEntry(T instance) throws DatabaseException;
	public abstract void saveInstance(T instance) throws DatabaseException;
	public abstract void updateEntry(T instance) throws DatabaseException;
	public abstract void setUpDatabase(boolean forced) throws DatabaseException;
	public abstract void dispose() throws DatabaseException;
	
	public void setUpDatabase() throws DatabaseException {
		setUpDatabase(false);
	}
}
