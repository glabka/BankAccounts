package dao.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bank_account.BankAccount;
import custom_exceptions.DatabaseException;
import custom_exceptions.InstanceAlreadyExistsException;
import custom_exceptions.InstanceAlreadySavedException;
import dao.BankAccountDAO;
import database.MySqlDatabaseConnectionManager;
import domain.BankCode;

public class MySqlBankAccountDAO extends BankAccountDAO {

	private MySqlDatabaseConnectionManager dcm;
	private Connection con;
	private Statement stmt;

	private static String[] fieldNames = { "bank_code", "account_num", "account_balace" };
	private static String[] fieldTypes = { MySqlCommFun.varchar(5) + MySqlCommFun.NOT_NULL,
			MySqlCommFun.varchar(12) + MySqlCommFun.NOT_NULL, MySqlCommFun.bigInt(20) };
	private String tableName = "bank_accounts";

	public MySqlBankAccountDAO(MySqlDatabaseConnectionManager mySqlDcm)
			throws ClassNotFoundException, DatabaseException {
		this.dcm = mySqlDcm;

		this.con = dcm.getConnection();
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
	}

	@Override
	public void deleteEntry(BankAccount instance) throws DatabaseException {
		try {
			stmt.executeUpdate(MySqlCommFun.DELETE + this.tableName + " where " + fieldNames[0] + " = "
					+ MySqlCommFun.addParentheses(instance.getBankCode().toString()) + " and " + fieldNames[1] + " = "
					+ MySqlCommFun.addParentheses(instance.getAccountNum()));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}

	}

	@Override
	public BankAccount loadInstance(BankCode bankCode, String accountNum) throws DatabaseException, InstanceAlreadyExistsException {
		try {
			ResultSet rs = stmt.executeQuery(MySqlCommFun.SELECT + this.tableName + " where " + fieldNames[0] + " = "
					+ MySqlCommFun.addParentheses(bankCode.toString()) + " and " + fieldNames[1] + " = "
					+ MySqlCommFun.addParentheses(accountNum));
			if (rs.next()) {
				BankAccount ba = BankAccount.createNewInstance(BankCode.valueOf(rs.getString(1)), rs.getString(2),
						Long.valueOf(rs.getString(3)));
				rs.close();
				return ba;
			} else {
				rs.close();
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
	}

	@Override
	public void saveInstance(BankAccount instance) throws DatabaseException, InstanceAlreadySavedException {
		if (!this.isAccountNumberFree(instance.getBankCode(), instance.getAccountNum())) {
			throw new InstanceAlreadySavedException("Instance with id = [" + instance.getBankCode() + ", "+ instance.getAccountNum() + "] has already been saved. For update of fields use updateEntry method.");
		}
		try {
			stmt.executeUpdate(MySqlCommFun.INSERT + this.tableName + " values("
					+ MySqlCommFun.addParentheses(instance.getBankCode().name()) + ","
					+ MySqlCommFun.addParentheses(instance.getAccountNum()) + ", " + instance.getAccountBalace() + ")");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
	}

	@Override
	public void updateEntry(BankAccount instance) throws DatabaseException {
		try {
			stmt.execute(MySqlCommFun.UPDATE + this.tableName + " set " + createPartOfUpdateStatement(instance)
					+ " where " + fieldNames[0] + " = " + MySqlCommFun.addParentheses(instance.getBankCode().toString())
					+ " and " + fieldNames[1] + " = " + MySqlCommFun.addParentheses(instance.getAccountNum()));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
	}

	private String createPartOfUpdateStatement(BankAccount instance) {
		String equals = " = ";
		String str = fieldNames[0] + equals + MySqlCommFun.addParentheses(instance.getBankCode().toString()) + ", "
				+ fieldNames[1] + equals + MySqlCommFun.addParentheses(instance.getAccountNum()) + ", " + fieldNames[2]
				+ equals + instance.getAccountBalace();
		return str;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setUpDatabase() throws DatabaseException {
		try {
			String createCMDSuffix = " (" + MySqlCommFun.createTableType(fieldNames, fieldTypes) + ", primary key ("
					+ fieldNames[0] + ", " + fieldNames[1] + "))";
			stmt.executeUpdate(MySqlCommFun.CREATE_TABLE_INE + this.tableName + createCMDSuffix);
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
	public boolean isAccountNumberFree(BankCode bankCode, String accountNum) throws DatabaseException {
		try {
			ResultSet rs = stmt.executeQuery(MySqlCommFun.SELECT + this.tableName + " where " + fieldNames[0] + " = "
					+ MySqlCommFun.addParentheses(bankCode.toString()) + " and " + fieldNames[1] + " = "
					+ MySqlCommFun.addParentheses(accountNum));
			if (rs.next()) {
				rs.close();
				return false;
			} else {
				rs.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
	}

	@Override
	public String getTableName() {
		return this.tableName;
	}

}
