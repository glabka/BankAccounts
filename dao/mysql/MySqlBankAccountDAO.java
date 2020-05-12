package dao.mysql;

import bankAccount.BankAccount;
import customExceptions.DatabaseException;
import dao.BankAccountDAO;
import domain.BankCode;

public class MySqlBankAccountDAO extends BankAccountDAO{

	@Override
	public BankAccount getInstance(BankCode bankCode, String accountNum) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveInstance(BankAccount instance) throws DatabaseException{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateEntry(BankAccount instance) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUpDatabase(boolean forced) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAccountNumberFree(BankCode bankCode, String accountNum) throws DatabaseException {
		// TODO Auto-generated method stub
		return false;
	}

}
