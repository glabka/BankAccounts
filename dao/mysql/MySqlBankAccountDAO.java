package dao.mysql;

import bankAccount.BankAccount;
import customExceptions.DatabaseException;
import dao.BankAccountDAO;
import domain.BankCode;

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

	@Override
	public void dispose() throws DatabaseException {
		// TODO Auto-generated method stub
		
	}


}
