package dao;

import com.neovisionaries.i18n.CountryCode;

import customExceptions.DatabaseException;
import domain.Person;

public abstract class PersonDAO {
	
	public abstract Person getPerson(String personID, CountryCode country) throws DatabaseException;
	public abstract void savePerson(Person person) throws DatabaseException;
	public abstract void updatePerson(Person person) throws DatabaseException;
	public abstract void setUpDatabase(boolean forced) throws DatabaseException;
	public abstract void dispose() throws DatabaseException;
	
	public void setUpDatabase() throws DatabaseException {
		setUpDatabase(false);
	}
}
