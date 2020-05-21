package dao;

import custom_exceptions.DatabaseException;
import custom_exceptions.InstanceAlreadyExistsException;
import domain.Person;

public abstract class PersonDAO extends GenericDAO<Person>{
	public abstract Person getInstance(int id) throws DatabaseException, InstanceAlreadyExistsException;
	public abstract boolean isIdFree(int id) throws DatabaseException;
}
