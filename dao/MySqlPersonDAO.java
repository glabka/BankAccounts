package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.neovisionaries.i18n.CountryCode;

import customExceptions.BadDateFormat;
import customExceptions.DatabaseException;
import database.MySqlDatabaseConnectionManager;
import domain.Person;

public class MySqlPersonDAO extends PersonDAO {

	private Connection con;
	private MySqlDatabaseConnectionManager dcm;
	private Statement stmt;
	private SimpleDateFormat dateFormat;
	
	private static String notNull = " not null";
	private static String[] fieldNames = {"person_id", "country", "first_name", "middle_name", "last_name", "brithdate"};
	private static String[] fieldTypes = {varchar(40) + notNull, varchar(2) + notNull, varchar(30) + notNull, varchar(30), varchar(30) + notNull, varchar(10) + notNull};
	private String tableName = "people";
	
	private static String varchar(int i) {
		return "varchar(" + i + ")";
	}
	
	public MySqlPersonDAO(MySqlDatabaseConnectionManager mySqlDcm) throws ClassNotFoundException, DatabaseException {
		this.dcm = mySqlDcm;
		con = dcm.getConnection();
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		}
		dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	}

	/**
	 * Returns null if no entry for specific personID is found.
	 * 
	 * @param personID
	 * @throws DatabaseException 
	 */
	@Override
	public Person getPerson(String personID, CountryCode country) throws DatabaseException {
		String dateString = "";
		try {
			ResultSet rs = stmt.executeQuery("select * from " + this.tableName + " where person_id = '" + personID + "' and country = '" + country.toString() + "'");
			if (rs.next()) {
				dateString = rs.getString(6);
				return Person.getInstance(rs.getString(1), CountryCode.valueOf(rs.getString(2)), rs.getString(3),
						rs.getString(4), rs.getString(5), dateFormat.parse(dateString));
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		} catch (ParseException e) {
			throw new BadDateFormat("Bad date format in mysql database: " + dateString);
		}
	}

	@Override
	public void savePerson(Person person) throws DatabaseException {
		try {
			stmt.executeUpdate("insert into " + this.tableName + " values( '" + person.getId() + "', '" + person.getCountry().toString() + "', '" + person.getFirstName() + "', "
					+ addParentheses(person.getMiddleName()) + ", '" + person.getLastName() + "', '" + dateFormat.format(person.getBirthdate()) + "')");
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/**
	 * Adds parentheses if string is different fromo null.
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
	public void updatePerson(Person person) throws DatabaseException {
		try {
			stmt.execute("update " + this.tableName + " set " + createPartOfUpdateStatement(person)
				+ " where " + fieldNames[0] + " = " + addParentheses(person.getId()) + " and " + fieldNames[1] + " = " + addParentheses(person.getCountry().toString()));
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	private String createPartOfUpdateStatement(Person person) {
		String equals = " = ";
		String str = fieldNames[0] + equals + addParentheses(person.getId()) + ", "+ fieldNames[1] + equals + addParentheses(person.getCountry().toString()) + ", "+ 
				fieldNames[2] + equals + addParentheses(person.getFirstName()) + ", "+ fieldNames[3] + equals + addParentheses(person.getMiddleName()) +", "+ 
				fieldNames[4] + equals + addParentheses(person.getLastName()) + ", "+ fieldNames[5] + equals + addParentheses(this.dateFormat.format(person.getBirthdate()));
		return str;
	}

	@Override
	public void setUpDatabase(boolean forced) throws DatabaseException {
		try {
			String createCMDPrefix = "create table if not exists ";
			String createCMDSuffixPeople = " (" + createTableType(fieldNames, fieldTypes)
				+ ", primary key ("+ fieldNames[0] +", " + fieldNames[1]+ "))";
			if (forced) {
				String dropCMD = "drop table if exists ";
				stmt.executeUpdate(dropCMD + this.tableName);
				stmt.executeUpdate(createCMDPrefix + this.tableName + createCMDSuffixPeople);
			} else {
				stmt.executeUpdate(createCMDPrefix + this.tableName + createCMDSuffixPeople);
			}
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public void disposeDAO() throws DatabaseException {
		dcm.disconnect();
	}

	private String createTableType(String[] fieldNames, String[] fieldTypes) {
		if(fieldNames.length != fieldTypes.length) {
			throw new IllegalArgumentException("input parameters fieldNames and fieldTypes must be of same length.");
		}
		String str = fieldNames[0] + " " + fieldTypes[0];
		for (int i = 1; i < fieldNames.length; i++) {
			str += ", " + fieldNames[i] + " " + fieldTypes[i];
		}
		return str;
	}
	
}
