package dao;

import bankAccount.BankAccount;

public abstract class BankAccountDAO {
	public abstract BankAccount getBankAccount(int bankAccountID) throws Exception;
	public abstract void savePerson(BankAccount bankAccount) throws Exception;
}
