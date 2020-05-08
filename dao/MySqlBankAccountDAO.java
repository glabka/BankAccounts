package dao;

import bankAccount.BankAccount;
import customExceptions.DatabaseException;
import domain.BankCode;
import domain.Person;

public class MySqlBankAccountDAO extends BankAccountDAO{

	@Override
	public BankAccount getBankAccount(BankCode bankCode, String accountNum) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveBankAccount(BankAccount bankAccount) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBankAccount(BankAccount bankAccount) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUpDatabase(boolean forced) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}


}
