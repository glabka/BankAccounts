package dao;

import custom_exceptions.DatabaseException;
import custom_exceptions.InstanceAlreadyExistsException;
import user_account.UserAccount;

public abstract class UserAccountDAO extends GenericDAO<UserAccount>{
	public abstract UserAccount loadInstance(int id) throws DatabaseException, InstanceAlreadyExistsException;
	public abstract UserAccount loadInstance(byte[] usernameHash) throws DatabaseException, InstanceAlreadyExistsException;
	public abstract boolean isIdFree(int id) throws DatabaseException;
	public abstract boolean isUsernameHashFree(byte[] hash) throws DatabaseException;
}
