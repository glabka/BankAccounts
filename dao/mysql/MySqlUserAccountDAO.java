package dao.mysql;

import customExceptions.DatabaseException;
import dao.UserAccountDAO;
import userAccount.UserAccount;

public class MySqlUserAccountDAO extends UserAccountDAO{

	@Override
	public UserAccount getUserAccount(int id) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserAccount getUserAccount(byte[] usernameHash) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveUserAccount(UserAccount userAccount) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUserAccount(UserAccount userAccount) throws DatabaseException {
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
