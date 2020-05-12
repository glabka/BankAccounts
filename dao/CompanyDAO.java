package dao;

import customExceptions.DatabaseException;
import domain.Company;

public abstract class CompanyDAO extends GenericDAO<Company>{
	public abstract Company getInstance(String id) throws DatabaseException;
}
