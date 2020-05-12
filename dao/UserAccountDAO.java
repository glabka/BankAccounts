package dao;

import customExceptions.DatabaseException;
import userAccount.UserAccount;

public abstract class UserAccountDAO extends GenericDAO<UserAccount>{
	public abstract UserAccount getInstance(int id) throws DatabaseException;
	// TODO - sha256 should in practice create unique hashes. Think about how to solve if that wouldn't be true
	// TODO - also I don't enforce that people get unique usernames. Maybe I should	and then I can make checker that checks whether hash of this username is available.
	// TODO - in this scenario I have to think what would happen if someone forgets their username. Because of that maybe unique int id could be handy.
	public abstract UserAccount getInstance(byte[] usernameHash) throws DatabaseException;
}
