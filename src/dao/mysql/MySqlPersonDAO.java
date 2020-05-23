package dao.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.neovisionaries.i18n.CountryCode;

import custom_exceptions.BadDateFormat;
import custom_exceptions.DatabaseException;
import custom_exceptions.InstanceAlreadyExistsException;
import custom_exceptions.InstanceAlreadySavedException;
import dao.PersonDAO;
import database.MySqlDatabaseConnectionManager;
import domain.Person;

public class MySqlPersonDAO extends PersonDAO {

	private MySqlDatabaseConnectionManager dcm;
	private Connection con;
	private Statement stmt;
	private SimpleDateFormat dateFormat;

	private static String[] fieldNames = { "person_id", "country", "first_name", "middle_name", "last_name",
			"brithdate" };
	private static String[] fieldTypes = { MySqlCommFun.integer(11), MySqlCommFun.varchar(2) + MySqlCommFun.NOT_NULL,
			MySqlCommFun.varchar(30) + MySqlCommFun.NOT_NULL, MySqlCommFun.varchar(30),
			MySqlCommFun.varchar(30) + MySqlCommFun.NOT_NULL, MySqlCommFun.varchar(10) + MySqlCommFun.NOT_NULL };
	private String tableName = "people";

	public MySqlPersonDAO(MySqlDatabaseConnectionManager mySqlDcm) throws ClassNotFoundException, DatabaseException {
		this.dcm = mySqlDcm;
		con = dcm.getConnection();
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
		dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	}

	@Override
	public void deleteEntry(Person instance) throws DatabaseException {
		try {
			stmt.executeUpdate(
					MySqlCommFun.DELETE + this.tableName + " where " + fieldNames[0] + " = " + instance.getId());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
	}

	/**
	 * Returns null if no entry for specific id is found.
	 * 
	 * @param id
	 * @throws DatabaseException
	 * @throws InstanceAlreadyExistsException 
	 */
	@Override
	public Person loadInstance(int id) throws DatabaseException, InstanceAlreadyExistsException {
		String dateString = "";
		try {
			ResultSet rs = stmt
					.executeQuery(MySqlCommFun.SELECT + this.tableName + " where " + fieldNames[0] + " = " + id);
			if (rs.next()) {
				dateString = rs.getString(6);
				Person p = Person.createNewInstance(rs.getInt(1), CountryCode.valueOf(rs.getString(2)), rs.getString(3),
						rs.getString(4), rs.getString(5), dateFormat.parse(dateString));
				rs.close();
				return p;
			} else {
				rs.close();
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		} catch (ParseException e) {
			e.printStackTrace();
			throw new BadDateFormat("Bad date format in mysql database: " + dateString);
		}
	}

	@Override
	public void saveInstance(Person instance) throws DatabaseException, InstanceAlreadySavedException {
		if (!this.isIdFree(instance.getId())) {
			throw new InstanceAlreadySavedException("Instance with id = " + instance.getId() + "has already been saved. For update of fields use updateEntry method.");
		}
		try {
			stmt.executeUpdate(MySqlCommFun.INSERT + this.tableName + " values(" + instance.getId() + ", "
					+ MySqlCommFun.addParentheses(instance.getCountry().toString()) + ", "
					+ MySqlCommFun.addParentheses(instance.getFirstName()) + ", "
					+ MySqlCommFun.addParentheses(instance.getMiddleName()) + ", "
					+ MySqlCommFun.addParentheses(instance.getLastName()) + ", "
					+ MySqlCommFun.addParentheses(dateFormat.format(instance.getBirthdate())) + ")");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
	}

	@Override
	public void updateEntry(Person instance) throws DatabaseException {
		try {
			stmt.execute(MySqlCommFun.UPDATE + this.tableName + " set " + createPartOfUpdateStatement(instance)
					+ " where " + fieldNames[0] + " = " + instance.getId());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
	}

	private String createPartOfUpdateStatement(Person person) {
		String equals = " = ";
		String str = fieldNames[0] + equals + person.getId() + ", " + fieldNames[1] + equals
				+ MySqlCommFun.addParentheses(person.getCountry().toString()) + ", " + fieldNames[2] + equals
				+ MySqlCommFun.addParentheses(person.getFirstName()) + ", " + fieldNames[3] + equals
				+ MySqlCommFun.addParentheses(person.getMiddleName()) + ", " + fieldNames[4] + equals
				+ MySqlCommFun.addParentheses(person.getLastName()) + ", " + fieldNames[5] + equals
				+ MySqlCommFun.addParentheses(this.dateFormat.format(person.getBirthdate()));
		return str;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setUpDatabase() throws DatabaseException {
		try {
			String createCMDSuffix = " (" + MySqlCommFun.createTableType(fieldNames, fieldTypes) + ", primary key ("
					+ fieldNames[0] + "))";
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
	public String getTableName() {
		return this.tableName;
	}

}
