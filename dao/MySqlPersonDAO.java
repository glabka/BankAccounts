package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
			// TODO change to current identification
			ResultSet rs = stmt.executeQuery("select * from people where person_id = '" + personID + "' and country = '" + country.toString() + "'");
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
			stmt.executeUpdate("insert into people values( '" + person.getId() + "', '" + person.getCountry().toString() + "', '" + person.getFirstName() + "', "
					+ getParenthesesIfNotNull(person.getMiddleName()) + ", '" + person.getLastName() + "', '" + dateFormat.format(person.getBirthdate()) + "')");
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	private String getParenthesesIfNotNull(String s) {
		if (s == null) {
			return s;
		} else {
			return "'" + s + "'";
		}
	}

	@Override
	public void updatePerson(Person person) throws DatabaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUpDatabase(boolean forced) throws DatabaseException {
		try {
			String createCMDPrefix = "create table if not exists ";
			String createCMDSuffixPeople = " (person_id varchar(30) not null, country varchar(2) not null,"
					+ " first_name varchar(30) not null, middle_name varchar(30), "
					+ "last_name varchar(30) not null, brithdate varchar(10), primary key (person_id, country))";
			String tableName = "people";
			if (forced) {
				String dropCMD = "drop table if exists ";
				stmt.executeUpdate(dropCMD + tableName);
				stmt.executeUpdate(createCMDPrefix + tableName + createCMDSuffixPeople);
			} else {
				stmt.executeUpdate(createCMDPrefix + tableName + createCMDSuffixPeople);
			}
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public void disposeDAO() throws DatabaseException {
		dcm.disconnect();
	}

}
