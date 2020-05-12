package dao.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.neovisionaries.i18n.CountryCode;

import customExceptions.BadDateFormat;
import customExceptions.DatabaseException;
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
	private static String[] fieldTypes = { MySqlCommFun.varchar(40) + MySqlCommFun.NOT_NULL,
			MySqlCommFun.varchar(2) + MySqlCommFun.NOT_NULL, MySqlCommFun.varchar(30) + MySqlCommFun.NOT_NULL,
			MySqlCommFun.varchar(30), MySqlCommFun.varchar(30) + MySqlCommFun.NOT_NULL,
			MySqlCommFun.varchar(10) + MySqlCommFun.NOT_NULL };
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

	/**
	 * Returns null if no entry for specific personID is found.
	 * 
	 * @param id
	 * @throws DatabaseException
	 */
	@Override
	public Person getInstance(String id, CountryCode country) throws DatabaseException {
		String dateString = "";
		try {
			ResultSet rs = stmt.executeQuery(MySqlCommFun.SELECT + this.tableName + " where " + fieldNames[0] + " = "
					+ addParentheses(id) + " and " + fieldNames[1] + " = " + addParentheses(country.toString()));
			if (rs.next()) {
				dateString = rs.getString(6);
				Person p = Person.getInstance(rs.getString(1), CountryCode.valueOf(rs.getString(2)), rs.getString(3),
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
	public void saveInstance(Person instance) throws DatabaseException {
		try {
			stmt.executeUpdate(MySqlCommFun.INSERT + this.tableName + " values( '" + instance.getId() + "', '"
					+ instance.getCountry().toString() + "', '" + instance.getFirstName() + "', "
					+ addParentheses(instance.getMiddleName()) + ", '" + instance.getLastName() + "', '"
					+ dateFormat.format(instance.getBirthdate()) + "')");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
	}

	/**
	 * Adds parentheses if string is different from null.
	 * 
	 * @param s
	 * @return
	 */
	private String addParentheses(String s) {
		if (s == null) {
			return s;
		} else {
			return "'" + s + "'";
		}
	}

	@Override
	public void updateEntry(Person instance) throws DatabaseException {
		try {
			stmt.execute(MySqlCommFun.UPDATE + this.tableName + " set " + createPartOfUpdateStatement(instance)
					+ " where " + fieldNames[0] + " = " + addParentheses(instance.getId()) + " and " + fieldNames[1]
					+ " = " + addParentheses(instance.getCountry().toString()));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
	}

	private String createPartOfUpdateStatement(Person person) {
		String equals = " = ";
		String str = fieldNames[0] + equals + addParentheses(person.getId()) + ", " + fieldNames[1] + equals
				+ addParentheses(person.getCountry().toString()) + ", " + fieldNames[2] + equals
				+ addParentheses(person.getFirstName()) + ", " + fieldNames[3] + equals
				+ addParentheses(person.getMiddleName()) + ", " + fieldNames[4] + equals
				+ addParentheses(person.getLastName()) + ", " + fieldNames[5] + equals
				+ addParentheses(this.dateFormat.format(person.getBirthdate()));
		return str;
	}

	@Override
	public void setUpDatabase(boolean forced) throws DatabaseException {
		try {
			String createCMDSuffix = " (" + MySqlCommFun.createTableType(fieldNames, fieldTypes) + ", primary key ("
					+ fieldNames[0] + ", " + fieldNames[1] + "))";
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
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
	}

	@Override
	public boolean isIdFree(String id, CountryCode country) throws DatabaseException {
		try {
			ResultSet rs = stmt.executeQuery(MySqlCommFun.SELECT + this.tableName + " where " + fieldNames[0] + " = "
					+ addParentheses(id) + " and " + fieldNames[1] + " = " + addParentheses(country.toString()));
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

}
