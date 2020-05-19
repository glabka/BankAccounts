package dao;

import bank_account.BankAccount;
import custom_exceptions.DatabaseException;
import domain.BankCode;

public abstract class BankAccountDAO extends GenericDAO<BankAccount>{
	public abstract BankAccount getInstance(BankCode bankCode, String accountNum) throws DatabaseException;
	public abstract boolean isAccountNumberFree(BankCode bankCode, String accountNum) throws DatabaseException;
}
