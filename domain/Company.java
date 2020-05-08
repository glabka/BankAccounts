package domain;

import java.util.HashMap;
import java.util.Map;

public class Company implements BankAccountOwner{

	/**
	 * key is name of company
	 */
	private Map<String, Company> companies = new HashMap<String, Company>();
	
	private String name;
	private String headquarters;
	
	private Company(String name, String headquarters) {
		this.name = name;
		this.headquarters = headquarters;
		this.companies.put(name, this);
	}
	
	/**
	 * Creates new instance of company, if not already instantiated.s
	 * @param name
	 * @param headquarters
	 * @return
	 */
	public Company getInstance(String name, String headquarters) {
		Company company = companies.get(name);
		if(company != null) {
			company.setHeadquarters(headquarters);
			return company;
		} else {
			return new Company(name, headquarters);
		}
	}
	
	/**
	 * Might return null in case company is not instantiated
	 * @param name
	 */
	public Company getInstance(String name) {
		return companies.get(name);
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
	
}
