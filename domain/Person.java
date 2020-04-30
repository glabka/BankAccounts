package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import idAssigners.MySqlStaticDbIDAssigner;

public class Person {

	private static Map<Integer, Person> people = new HashMap<Integer, Person>();

	private int id;
	private String firstName;
	private String middleName;
	private String lastName;

	public Person(String firstName, String middleName, String lastName) {
		this.id = MySqlStaticDbIDAssigner.getIDAssigner().getPersonID();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		people.put(id, this);
	}

	private Person(int id, String firstName, String middleName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		people.put(id, this);
	}

	/**
	 * Method for creating instance of Person from known data from database. Do NOT
	 * serve for creation of instances of newly added person - for that use
	 * constructor.
	 * 
	 * If the Person is already instantiated, his firstName, middleName and lastName
	 * will be changed to values of input parameters and instance will be returned.
	 * If this behavior is undesired use getInstance(int id) insetead.
	 * 
	 * @param id
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @return
	 */
	public static Person getIstance(int id, String firstName, String middleName, String lastName) {
		Person p = people.get(id);
		if (p == null) {
			return new Person(id, firstName, middleName, lastName);
		} else {
			p.setFirstName(firstName);
			p.setLastName(lastName);
			p.setMiddleName(middleName);
			return p;
		}
	}

	/**
	 * Method for getting already instantiated Person objects in programs runtime.
	 * Can return null if person wasn't instantiated yet.
	 * 
	 * @param id
	 * @return
	 */
	public static Person getInstance(int id) {
		return people.get(id);
	}
	
	public static List<Person> getAllInstances(){
		return new ArrayList<Person>(people.values());
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String toString() {
		return "[" + this.getId() + ", "+ this.getFirstName() +  ", " + this.getMiddleName() +  ", " + this.getLastName() + "]";
	}
}
