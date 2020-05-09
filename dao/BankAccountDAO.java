package dao;

import bankAccount.BankAccount;
import customExceptions.DatabaseException;
import domain.BankCode;
import domain.Person;

public abstract class BankAccountDAO {
	public abstract BankAccount getBankAccount(BankCode bankCode, String accountNum) throws DatabaseException;
	public abstract void saveBankAccount(BankAccount bankAccount) throws DatabaseException;
	public abstract void updateBankAccount(BankAccount bankAccount) throws DatabaseException;
	public abstract void setUpDatabase(boolean forced) throws DatabaseException;
	public abstract void dispose() throws DatabaseException;
	
	public void setUpDatabase() throws DatabaseException {
		setUpDatabase(false);
	}
}
