package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neovisionaries.i18n.CountryCode;

import custom_exceptions.InstanceAlreadyExistsException;

public class Person implements BankAccountOwner, Multiton{

	private static Map<Integer, Person> people = new HashMap<Integer, Person>();
	
	private int id;
	private CountryCode country;
	private String firstName;
	private String middleName;
	private String lastName;
	private Date birthdate;

	private Person(int id, CountryCode country, String firstName, String middleName, String lastName, Date birthdate) {
		this.id = id;
		this.country = country;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		people.put(id, this);
	}

	/**
	 * Method for creating instance of Person.
	 * 
	 * If the Person is already instantiated, it is returned with it's respective
	 * values of fields instead.
	 * 
	 * @param id
	 * @param country
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @param birthdate
	 * @return
	 * @throws InstanceAlreadyExistsException 
	 */
	public static Person getInstance(int id, CountryCode country, String firstName, String middleName, String lastName,
			Date birthdate) throws InstanceAlreadyExistsException {
		// TODO check fields are not null and whether strings are of certain length
		Person p = people.get(id);
		if (p == null) {
			return new Person(id, country, firstName, middleName, lastName, birthdate);
		} else {
			throw new InstanceAlreadyExistsException(p);
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

	public static List<Person> getAllInstances() {
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
	
	public CountryCode getCountry() {
		return country;
	}

	public void setCountry(CountryCode country) {
		this.country = country;
	}

	public Date getBirthdate() {
		return new Date(birthdate.getTime());
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String toString() {
		return "[" + this.getId() + ", " + this.getFirstName() + ", " + this.getMiddleName() + ", " + this.getLastName()
				+ "]";
	}
	
	/**
	 * Should be used after the end of usage of certain instance of Person to free allocated
	 * memory associated with this object. 
	 * 
	 * BE AWARE that when used in any other way then right before loosing all references to instance of Person
	 * or its end of use can lead to existence of two Person instances of one actual person.
	 * @param person
	 */
	public void dispose() {
		people.remove(this.getId());
	}

	/**
	 * Should be used after the end of usage of certain instance of Person to free allocated
	 * memory associated with this object. 
	 * 
	 * BE AWARE that when used in any other way then right before loosing all references to instance of Person
	 * or its end of use can lead to existence of two Person instances of one actual person.
	 * @param person
	 */
	public static void dispose(Person person) {
		people.remove(person.getId());
	}
	
	/**
	 * Should be used after the end of usage of all instances of Person class to free allocated
	 * memory associated with these objects. 
	 * 
	 * BE AWARE that when used in any other way then right before loosing all references to all instances of Person
	 * or end of use of all instance can lead to existence of two Person instances of one actual person.
	 */
	public static void disposeAll() {
		people.clear();
	}

	@Override
	public String getStringId() {
		return String.valueOf(this.getId());
	}
}
