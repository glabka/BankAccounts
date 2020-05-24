package dao.mysql;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import custom_exceptions.DatabaseException;
import custom_exceptions.InstanceAlreadyExistsException;
import custom_exceptions.InstanceAlreadySavedException;
import dao.UserAccountDAO;
import domain.BankAccountOwner;
import domain.BankCode;
import user_account.UserAccount;

public class MySqlUserAccountDAO extends UserAccountDAO {
	/*
	 * private static Map<Integer, UserAccount> userAccounts = new HashMap<Integer,
	 * UserAccount>(); private int userAccountID; private BankCode bankCode; private
	 * BankAccountOwner accountOwner; private byte[] usernameHash; private byte[]
	 * passwordHash; private String salt; private String emailAddress; private int
	 * phoneNumber;
	 */
	private static String[] fieldNames = { "person_id", "country", "first_name", "middle_name", "last_name",
			"brithdate" };
	private static String[] fieldTypes = { MySqlCommFun.integer(11), MySqlCommFun.varchar(2) + MySqlCommFun.NOT_NULL,
			MySqlCommFun.varchar(30) + MySqlCommFun.NOT_NULL, MySqlCommFun.varchar(30),
			MySqlCommFun.varchar(30) + MySqlCommFun.NOT_NULL, MySqlCommFun.varchar(10) + MySqlCommFun.NOT_NULL };
	private String[] tableNames = {"user_accounts","user_and_bank_accounts"};

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
			throw new InstanceAlreadySavedException("Instance with id = " + instance.getId()
					+ "has already been saved. For update of fields use updateEntry method.");
		}
		// TODO Auto-generated method stub

	}

	@Override
	public void updateEntry(UserAccount instance) throws DatabaseException {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setUpDatabase() throws DatabaseException {
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String[] getTableNames() {
		return Arrays.copyOf(tableNames, tableNames.length);
	}

}
