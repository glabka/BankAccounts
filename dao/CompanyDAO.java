package dao;

import customExceptions.DatabaseException;
import domain.Company;

public abstract class CompanyDAO extends GenericDAO<Company>{
	public abstract Company getInstance(String id) throws DatabaseException;
	public abstract boolean isIdFree(int id) throws DatabaseException;
	public abstract boolean isNameFree(String name) throws DatabaseException;
}
