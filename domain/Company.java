package domain;

import java.util.HashMap;
import java.util.Map;

public class Company implements BankAccountOwner{

	/**
	 * key is id of company
	 */
	private Map<Integer, Company> companies = new HashMap<Integer, Company>();
	
	private int id;
	private String name;
	private String headquarters;
	private Person executiveDirector;
	
	private Company(int id, String name, String headquarters, Person executiveDirector) {
		this.name = name;
		this.headquarters = headquarters;
		this.companies.put(id, this);
	}
	
	/**
	 * Creates new instance of company, if not already instantiated - in that case return already instantiated 
	 * object with unchanged field values.
	 * 
	 * @param name
	 * @param headquarters
	 * @return
	 */
	public Company getInstance(int id, String name, String headquarters, Person executiveDirector) {
		Company company = companies.get(id);
		if(company != null) {
			return company;
		} else {
			return new Company(id, name, headquarters, executiveDirector);
		}
	}
	
	/**
	 * Might return null in case company is not instantiated
	 * @param name
	 */
	public Company getInstance(int id) {
		return companies.get(id);
	}
	
	public String getName() {
		return name;
	}

	public String getHeadquarters() {
		return headquarters;
	}

	public void setHeadquarters(String headquarters) {
		this.headquarters = headquarters;
	}

	public Person getExecutiveDirector() {
		return executiveDirector;
	}

	public void setExecutiveDirector(Person executiveDirector) {
		this.executiveDirector = executiveDirector;
	}
	
	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Should be used after the end of usage of certain instance of Company to free allocated
	 * memory associated with this object. 
	 * 
	 * BE AWARE that when used in any other way then right before loosing all references to instance of Company
	 * or its end of use can lead to existence of two Company instances of one actual person.
	 * @param company
	 */
	public void dispose(Company company) {
		companies.remove(company.getId());
	}
	
	/**
	 * Should be used after the end of usage of all instances of Company class to free allocated
	 * memory associated with these objects. 
	 * 
	 * BE AWARE that when used in any other way then right before loosing all references to all instances of Company
	 * or end of use of all instance can lead to existence of two Company instances of one actual person.
	 * @param person
	 */
	public void disposeAll() {
		companies.clear();
	}
}
