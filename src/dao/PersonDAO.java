package dao;

import customExceptions.DatabaseException;
import domain.Person;

public abstract class PersonDAO extends GenericDAO<Person>{
	public abstract Person getInstance(int id) throws DatabaseException;
	public abstract boolean isIdFree(int id) throws DatabaseException;
}
