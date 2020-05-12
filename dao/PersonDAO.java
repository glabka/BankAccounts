package dao;

import com.neovisionaries.i18n.CountryCode;

import customExceptions.DatabaseException;
import domain.Person;

public abstract class PersonDAO extends GenericDAO<Person>{
	public abstract Person getInstance(String id, CountryCode country) throws DatabaseException;
	public abstract boolean isIdFree(String id, CountryCode country) throws DatabaseException;
}
