package dao;

import custom_exceptions.DatabaseException;
import custom_exceptions.InstanceAlreadyExistsException;
import domain.Company;

public abstract class CompanyDAO extends GenericDAO<Company>{
	public abstract Company getInstance(int id) throws DatabaseException, InstanceAlreadyExistsException;
	public abstract boolean isIdFree(int id) throws DatabaseException;
	public abstract boolean isNameFree(String name) throws DatabaseException;
}
