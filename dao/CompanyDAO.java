package dao;

import customExceptions.DatabaseException;
import domain.Company;

public abstract class CompanyDAO {
	public abstract Company getCompany(String name) throws DatabaseException;
	public abstract void saveCompany(Company company) throws DatabaseException;
	public abstract void updateCompany(Company company) throws DatabaseException;
	public abstract void setUpDatabase(boolean forced) throws DatabaseException;
	public abstract void dispose() throws DatabaseException;
	
	public void setUpDatabase() throws DatabaseException {
		setUpDatabase(false);
	}
}
