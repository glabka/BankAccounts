package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import bank_account.BankAccount;
import commons.Constants;
import custom_exceptions.DatabaseException;
import custom_exceptions.InstanceAlreadyExistsException;
import custom_exceptions.InstanceAlreadySavedException;
import custom_exceptions.UnsupportedClassException;
import dao.UserAccountDAO;
import database.MySqlDatabaseConnectionManager;
import domain.BankAccountOwner;
import domain.Company;
import domain.Person;
import user_account.UserAccount;

public class MySqlUserAccountDAO extends UserAccountDAO {

	private MySqlDatabaseConnectionManager dcm;
	private Connection con;
	private Statement stmt;
	private MySqlBankAccountDAO baDAO;
	private MySqlPersonDAO pDAO;
	private MySqlCompanyDAO cDAO;

	private static String[] fieldNames0 = { "user_account_id", "bank_code", "account_owner_type", "account_owner_id",
			"username_hash", "password_hash", "salt", "email_address", "phone_number" };
	private static String[] fieldTypes0 = { MySqlCommFun.integer(11) + MySqlCommFun.NOT_NULL,
			MySqlCommFun.varchar(5) + MySqlCommFun.NOT_NULL, MySqlCommFun.varchar(30) + MySqlCommFun.NOT_NULL,
			MySqlCommFun.integer(11) + MySqlCommFun.NOT_NULL,
			MySqlCommFun.binary(Constants.usernameHashNumOfBytes()) + MySqlCommFun.NOT_NULL,
			MySqlCommFun.binary(Constants.passwordHashNumOfBytes()) + MySqlCommFun.NOT_NULL,
			MySqlCommFun.characters(Constants.SALT_LENGTH) + MySqlCommFun.NOT_NULL,
			MySqlCommFun.varchar(Constants.EMAIL_ADDRESS_MAX_LENGTH) + MySqlCommFun.NOT_NULL,
			MySqlCommFun.varchar(Constants.PHONE_NUMBER_MAX_LENGTH) + MySqlCommFun.NOT_NULL };
	private static String[] fieldNames1 = { "user_acccount_id", "bank_code", "account_num" };
	private static String[] fieldTypes1 = { MySqlCommFun.integer(11) + MySqlCommFun.NOT_NULL,
			MySqlCommFun.characters(5) + MySqlCommFun.NOT_NULL, MySqlCommFun.varchar(12) + MySqlCommFun.NOT_NULL };
	private String[] tableNames = { "user_accounts", "user_and_bank_accounts" };

	public MySqlUserAccountDAO(MySqlDatabaseConnectionManager mySqlDcm, MySqlBankAccountDAO baDAO, MySqlPersonDAO pDAO,
			MySqlCompanyDAO cDAO) throws ClassNotFoundException, DatabaseException {
		this.dcm = mySqlDcm;
		this.baDAO = baDAO;
		this.pDAO = pDAO;
		this.cDAO = cDAO;

		con = dcm.getConnection();
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
	}

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
					+ " has already been saved. For update of fields use updateEntry method.");
		}
		saveUserAccountPart(instance);
		saveBankAccountsPart(instance);
	}

	private void saveUserAccountPart(UserAccount instance) throws DatabaseException {
		try {
			PreparedStatement pstmt = con.prepareStatement(MySqlCommFun.INSERT + tableNames[0] + " values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			pstmt.setInt(1, instance.getId());
			pstmt.setString(2, instance.getBankCode().toString());
			pstmt.setString(3, getAccountOwnerType(instance.getAccountOwner()));
			pstmt.setInt(4, instance.getAccountOwner().getId());
			pstmt.setBytes(5, instance.getUsernameHash());
			pstmt.setBytes(6, instance.getPasswordHash());
			pstmt.setString(7, instance.getSalt());
			pstmt.setString(8, instance.getEmailAddress());
			pstmt.setString(9, instance.getPhoneNumber());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}

	}

	private enum AccountOwnerType {
		PERSON, COMPANY
	}

	private String getAccountOwnerType(BankAccountOwner accountOwner) {
		if (accountOwner instanceof Person) {
			return AccountOwnerType.PERSON.toString();
		} else if (accountOwner instanceof Company) {
			return AccountOwnerType.COMPANY.toString();
		} else {
			throw new UnsupportedClassException("Class " + this.getClass().getName()
					+ " does not support saving of UserAccount's class " + accountOwner.getClass().getName());
		}
	}

	private BankAccountOwner loadBankAccountOwner(AccountOwnerType aoType, int id)
			throws DatabaseException, InstanceAlreadyExistsException {
		BankAccountOwner bao;
		if (aoType.equals(AccountOwnerType.PERSON)) {
			bao = Person.getInstance(id);
			if (bao != null) {
				return bao;
			} else {
				return pDAO.loadInstance(id);
			}
		} else {
			// COMPANY
			bao = Company.getInstance(id);
			if (bao != null) {
				return bao;
			} else {
				return cDAO.loadInstance(id);
			}
		}
	}

	private void saveBankAccountsPart(UserAccount instance) throws DatabaseException {
		BankAccount[] bankAccounts = instance.getBankAccounts();
		int user_account_id = instance.getId();
		for(BankAccount bankAccount : bankAccounts) {
			try {
				stmt.executeUpdate(MySqlCommFun.INSERT + this.tableNames[1] + " values("
						+ user_account_id + ", "
						+ MySqlCommFun.addParentheses(bankAccount.getBankCode().toString()) + ", "
						+ MySqlCommFun.addParentheses(bankAccount.getAccountNum()) + ")");
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DatabaseException(e.getMessage());
			}
		}
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
		setUpUserAccountTable();
		setUpBankAccountsTable();
	}

	private void setUpUserAccountTable() throws DatabaseException {
		try {
			String createCMDSuffix = " (" + MySqlCommFun.createTableType(fieldNames0, fieldTypes0) + ", primary key ("
					+ fieldNames0[0] + "))";
			stmt.executeUpdate(MySqlCommFun.CREATE_TABLE_INE + this.tableNames[0] + createCMDSuffix);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
	}

	private void setUpBankAccountsTable() throws DatabaseException {
		try {
			String createCMDSuffix = " (" + MySqlCommFun.createTableType(fieldNames1, fieldTypes1) + ")";
			stmt.executeUpdate(MySqlCommFun.CREATE_TABLE_INE + this.tableNames[1] + createCMDSuffix);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
	}

	@Override
	public void dispose() throws DatabaseException {
		try {
			stmt.close();
			dcm.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
	}

	@Override
	public boolean isIdFree(int id) throws DatabaseException {
		// TODO Auto-generated method stub
		return true;
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
