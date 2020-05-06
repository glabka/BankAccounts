package dao;

import bankAccount.BankAccount;
import customExceptions.DatabaseException;

public abstract class BankAccountDAO {
	public abstract BankAccount getBankAccount(int bankAccountID) throws DatabaseException;
	public abstract void savePerson(BankAccount bankAccount) throws DatabaseException;
}
