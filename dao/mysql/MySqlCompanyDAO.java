package dao.mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import customExceptions.DatabaseException;
import dao.CompanyDAO;
import database.MySqlDatabaseConnectionManager;
import domain.Company;

public class MySqlCompanyDAO extends CompanyDAO {

	private MySqlDatabaseConnectionManager dcm;
	private Connection con;
	private Statement stmt;
	
	private static String[] fieldNames = { "company_id", "company_name", "headquarters", "person_id"};
	private static String[] fieldTypes = { MySqlCommFun.varchar(30) + MySqlCommFun.NOT_NULL,
			MySqlCommFun.varchar(30) + MySqlCommFun.NOT_NULL, MySqlCommFun.varchar(30) + MySqlCommFun.NOT_NULL,
			MySqlCommFun.varchar(10) + MySqlCommFun.NOT_NULL };
	private String tableName = "people";

	public MySqlCompanyDAO(MySqlDatabaseConnectionManager mySqlDcm) throws ClassNotFoundException, DatabaseException {
		this.dcm = mySqlDcm;
		con = dcm.getConnection();
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
	}

	@Override
	public Company getInstance(String id) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveInstance(Company instance) throws DatabaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateEntry(Company instance) throws DatabaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUpDatabase(boolean forced) throws DatabaseException {
		try {
			String createCMDSuffix = " (" + MySqlCommFun.createTableType(fieldNames, fieldTypes) + ", primary key ("
					+ fieldNames[0] + "))";
			if (forced) {
				stmt.executeUpdate(MySqlCommFun.DROP_TABLE_IE + this.tableName);
			}
			stmt.executeUpdate(MySqlCommFun.CREATE_TABLE_INE + this.tableName + createCMDSuffix);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
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
	public boolean isNameFree(String name) throws DatabaseException {
		// TODO Auto-generated method stub
		return false;
	}

}
