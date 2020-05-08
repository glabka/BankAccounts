package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neovisionaries.i18n.CountryCode;

public class Person implements BankAccountOwner {

	private static Map<String, Person> people = new HashMap<String, Person>();

	/**
	 * identification card/passport number depending on CountryCode country
	 */
	private String id;
	private CountryCode country;
	private String firstName;
	private String middleName;
	private String lastName;
	private Date birthdate;

	private Person(String id, CountryCode country, String firstName, String middleName, String lastName, Date birthdate) {
		this.id = id;
		this.country = country;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		people.put(createKeyForMap(id, country), this);
	}

	private static String createKeyForMap(String id, CountryCode country) {
		return id + country.toString();
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
	 */
	public static Person getInstance(String id, CountryCode country, String firstName, String middleName, String lastName,
			Date birthdate) {
		// TODO check fields are not null
		Person p = people.get(createKeyForMap(id, country));
		if (p == null) {
			return new Person(id, country, firstName, middleName, lastName, birthdate);
		} else {
			return p;
		}
	}

	/**
	 * Method for getting already instantiated Person objects in programs runtime.
	 * Can return null if person wasn't instantiated yet.
	 * 
	 * @param id
	 * @param country
	 * @return
	 */
	public static Person getInstance(String id, CountryCode country) {
		return people.get(createKeyForMap(id, country));
	}

	public static List<Person> getAllInstances() {
		return new ArrayList<Person>(people.values());
	}

	public String getId() {
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

	@Override
	public void finalize() throws Throwable {
		people.remove(createKeyForMap(this.id, this.country));
		super.finalize();
	}

}
