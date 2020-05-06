package dao;

import domain.Person;

public abstract class PersonDAO {
	
	public abstract Person getPerson(int personID) throws Exception;
	public abstract void savePerson(Person person) throws Exception;
	public abstract void updatePerson(Person person) throws Exception;

}
