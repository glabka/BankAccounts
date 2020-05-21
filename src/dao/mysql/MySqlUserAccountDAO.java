package dao.mysql;

import custom_exceptions.DatabaseException;
import custom_exceptions.InstanceAlreadyExistsException;
import custom_exceptions.InstanceAlreadySavedException;
import dao.UserAccountDAO;
import user_account.UserAccount;

public class MySqlUserAccountDAO extends UserAccountDAO{

	@Override
	public void deleteEntry(UserAccount instance) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public UserAccount loadInstance(int id) throws DatabaseException, InstanceAlreadyExistsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserAccount loadInstance(byte[] usernameHash) throws DatabaseException, InstanceAlreadyExistsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveInstance(UserAccount instance) throws DatabaseException, InstanceAlreadySavedException {
		if (!this.isIdFree(instance.getId())) {
			throw new InstanceAlreadySavedException("Instance with id = " + instance.getId() + "has already been saved. For update of fields use updateEntry method.");
		}
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
