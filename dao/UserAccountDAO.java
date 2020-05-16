package dao;

import customExceptions.DatabaseException;
import userAccount.UserAccount;

public abstract class UserAccountDAO extends GenericDAO<UserAccount>{
	public abstract UserAccount getInstance(int id) throws DatabaseException;
	public abstract UserAccount getInstance(byte[] usernameHash) throws DatabaseException;
	public abstract boolean isIdFree(int id) throws DatabaseException;
	public abstract boolean isUsernameHashFree(byte[] hash) throws DatabaseException;
}
