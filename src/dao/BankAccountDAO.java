package dao;

import bank_account.BankAccount;
import custom_exceptions.DatabaseException;
import custom_exceptions.InstanceAlreadyExistsException;
import domain.BankCode;

public abstract class BankAccountDAO extends GenericDAO<BankAccount>{
	public abstract BankAccount getInstance(BankCode bankCode, String accountNum) throws DatabaseException, InstanceAlreadyExistsException;
	public abstract boolean isAccountNumberFree(BankCode bankCode, String accountNum) throws DatabaseException;
}
