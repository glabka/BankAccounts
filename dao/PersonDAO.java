package dao;

import com.neovisionaries.i18n.CountryCode;

import customExceptions.DatabaseException;
import domain.Person;

public abstract class PersonDAO extends GenericDAO<Person>{
	public abstract Person getInstance(String personID, CountryCode country) throws DatabaseException;
}
