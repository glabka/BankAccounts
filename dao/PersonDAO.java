package dao;

import customExceptions.DatabaseException;
import domain.Person;

public abstract class PersonDAO {
	
	public abstract Person getPerson(int personID) throws DatabaseException;
	public abstract void savePerson(Person person) throws DatabaseException;
	public abstract void updatePerson(Person person) throws DatabaseException;

}
