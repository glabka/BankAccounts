package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.MySqlDatabaseConnectionManager;
import domain.Person;

public class MySqlPersonDAO extends PersonDAO {

	private Connection con;
	private MySqlDatabaseConnectionManager dcm;
	private Statement stmt;

	public MySqlPersonDAO(MySqlDatabaseConnectionManager mySqlDcm) throws ClassNotFoundException, SQLException {
		this.dcm = mySqlDcm;
		con = dcm.getConnection();
		stmt = con.createStatement();
	}


	/**
	 * Returns null if no entry for specific personID is found.
	 * @param personID 
	 */
	@Override
	public Person getPerson(int personID) throws SQLException {
		ResultSet rs = stmt.executeQuery("select * from people where person_id = " + personID);
		if (rs.next()) {
			return Person.getIstance(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
		} else {
			return null;
		}
	}

	@Override
	public void savePerson(Person person) throws SQLException {
		stmt.executeUpdate("insert into people values(" + person.getId() + ", '" + person.getFirstName() + "', "
				+ getParenthesesIfNotNull(person.getMiddleName()) + ", '" + person.getLastName() + "')");
	}

	private String getParenthesesIfNotNull(String s) {
		if (s == null) {
			return s;
		} else {
			return "'" + s + "'";
		}
	}
	
	@Override
	public void updatePerson(Person person) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void disposeDAO() throws SQLException {
		dcm.disconnect();
	}
}
