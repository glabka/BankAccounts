package dao.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import custom_exceptions.DatabaseException;
import custom_exceptions.InstanceAlreadyExistsException;
import custom_exceptions.InstanceAlreadySavedException;
import dao.CompanyDAO;
import dao.PersonDAO;
import database.MySqlDatabaseConnectionManager;
import domain.Company;
import domain.Person;

public class MySqlCompanyDAO extends CompanyDAO {

	private MySqlDatabaseConnectionManager dcm;
	private MySqlPersonDAO pDAO;
	private Connection con;
	private Statement stmt;

	private static String[] fieldNames = { "company_id", "company_name", "headquarters", "person_id" };
	private static String[] fieldTypes = { MySqlCommFun.integer(11),
			MySqlCommFun.varchar(30) + MySqlCommFun.NOT_NULL, MySqlCommFun.varchar(30) + MySqlCommFun.NOT_NULL,
			MySqlCommFun.integer(11) };
	private String tableName = "companies";

	public MySqlCompanyDAO(MySqlDatabaseConnectionManager mySqlDcm, MySqlPersonDAO pDAO)
			throws ClassNotFoundException, DatabaseException {
		this.dcm = mySqlDcm;
		this.pDAO = pDAO;

		this.con = dcm.getConnection();
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
	}

	@Override
	public void deleteEntry(Company instance) throws DatabaseException {
		try {
			stmt.executeUpdate(
					MySqlCommFun.DELETE + this.tableName + " where " + fieldNames[0] + " = " + instance.getId());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
	}

	@Override
	public Company loadInstance(int id) throws DatabaseException, InstanceAlreadyExistsException {
		try {
			ResultSet rs = stmt
					.executeQuery(MySqlCommFun.SELECT + this.tableName + " where " + fieldNames[0] + " = " + id);
			if (rs.next()) {
				int person_id = rs.getInt(4);
				Person p = Person.getInstance(person_id);
				if (p == null) {
					p = pDAO.loadInstance(person_id);
				}
				Company c = Company.createNewInstance(rs.getInt(1), rs.getString(2), rs.getString(3), p);
				rs.close();
				return c;
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
	public void saveInstance(Company instance) throws DatabaseException, InstanceAlreadySavedException {
		if (!this.isIdFree(instance.getId())) {
			throw new InstanceAlreadySavedException("Instance with id = " + instance.getId() + "has already been saved. For update of fields use updateEntry method.");
		}
		try {
			stmt.executeUpdate(MySqlCommFun.INSERT + this.tableName + " values(" + instance.getId() + ", "
					+ MySqlCommFun.addParentheses(instance.getName()) + ", "
					+ MySqlCommFun.addParentheses(instance.getHeadquarters()) + ", "
					+ instance.getExecutiveDirector().getId() + ")");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
	}

	@Override
	public void updateEntry(Company instance) throws DatabaseException {
		try {
			stmt.execute(MySqlCommFun.UPDATE + this.tableName + " set " + createPartOfUpdateStatement(instance)
					+ " where " + fieldNames[0] + " = " + instance.getId());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
	}

	private String createPartOfUpdateStatement(Company instance) {
		String equals = " = ";
		String str = fieldNames[0] + equals + instance.getId() + ", " + fieldNames[1] + equals
				+ MySqlCommFun.addParentheses(instance.getName()) + ", " + fieldNames[2] + equals
				+ MySqlCommFun.addParentheses(instance.getHeadquarters()) + ", " + fieldNames[3] + equals
				+ instance.getExecutiveDirector().getId();
		return str;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setUpDatabase() throws DatabaseException {
		try {
			String createCMDSuffix = " (" + MySqlCommFun.createTableType(fieldNames, fieldTypes) + ", primary key ("
					+ fieldNames[0] + "), foreign key (" + fieldNames[3] + ") references " + pDAO.getTableNames()[0] + "("
					+ fieldNames[3] + "), unique key(" + fieldNames[1] + "))";
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
	public boolean isIdFree(int id) throws DatabaseException {
		try {
			ResultSet rs = stmt
					.executeQuery(MySqlCommFun.SELECT + this.tableName + " where " + fieldNames[0] + " = " + id);
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
	public boolean isNameFree(String name) throws DatabaseException {
		try {
			ResultSet rs = stmt.executeQuery(MySqlCommFun.SELECT + this.tableName + " where " + fieldNames[1] + " = "
					+ MySqlCommFun.addParentheses(name));
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String[] getTableNames() {
		String[] tablenames = {this.tableName};
		return tablenames;
	}

}
