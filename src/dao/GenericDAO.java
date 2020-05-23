package dao;

import custom_exceptions.DatabaseException;
import custom_exceptions.InstanceAlreadySavedException;

public abstract class GenericDAO<T> {

	public abstract String getTableName();
	public abstract void deleteEntry(T instance) throws DatabaseException;
	public abstract void saveInstance(T instance) throws DatabaseException, InstanceAlreadySavedException;
	public abstract void updateEntry(T instance) throws DatabaseException;
	/**
	 * Creates table(s) needed for storing of specific class T if this/these
	 * table(s) do(es) not exist yet.
	 * @throws DatabaseException
	 */
	public abstract void setUpDatabase() throws DatabaseException;
	public abstract void dispose() throws DatabaseException;
}
