package dao.mysql;

import customExceptions.DatabaseException;
import dao.UserAccountDAO;
import userAccount.UserAccount;

public class MySqlUserAccountDAO extends UserAccountDAO{

	@Override
	public void deleteEntry(UserAccount instance) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public UserAccount getInstance(int id) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserAccount getInstance(byte[] usernameHash) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveInstance(UserAccount instance) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateEntry(UserAccount instance) throws DatabaseException {
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
	public boolean isIdFree(int id) throws DatabaseException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isUsernameHashFree(byte[] hash) throws DatabaseException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return null;
	}

}
