package dao;

import bankAccount.BankAccount;
import customExceptions.DatabaseException;
import domain.BankCode;

public abstract class BankAccountDAO extends GenericDAO<BankAccount>{
	public abstract BankAccount getInstance(BankCode bankCode, String accountNum) throws DatabaseException;
}
