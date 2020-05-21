package integration_tests.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.neovisionaries.i18n.CountryCode;

import custom_exceptions.DatabaseException;
import custom_exceptions.InstanceAlreadyExistsException;
import dao.GenericDAO;
import dao.PersonDAO;
import dao.mysql.MySqlPersonDAO;
import database.DatabaseSetup;
import database.MySqlDatabaseConnectionManager;
import domain.Person;

class PersonDAOTest {

	private Person[] people = new Person[2];
	private int[] ids = {1, 2};
	private CountryCode[] countries = {CountryCode.US, CountryCode.GB};
	private String[] firstNames = {"Helen", "Adam"};
	private String[] middleNames = {"Amalie", null};
	private String[] lastNames = {"Miowic", "Green"};
	private Date[] birthdays = new Date[2];
	MySqlDatabaseConnectionManager dcm;
	private PersonDAO pDAO;
	
	@BeforeAll
	public void prepare() throws ClassNotFoundException, DatabaseException, ParseException, InstanceAlreadyExistsException {
		dcm = new MySqlDatabaseConnectionManager();
		List<GenericDAO<?>> daos = new ArrayList<>();
		pDAO = new MySqlPersonDAO(dcm);
		daos.add(pDAO);
		DatabaseSetup.setUpDatabse(daos, true);

		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		birthdays[0] = dateFormat.parse("03/09/1984");
		birthdays[1] = dateFormat.parse("05/20/1990");

		people[0] = Person.getInstance(ids[0], countries[0], firstNames[0], middleNames[0], lastNames[0], birthdays[0]);
		people[1] = Person.getInstance(ids[1], countries[1], firstNames[1], middleNames[1], lastNames[1], birthdays[1]);
	}

	@Test
	public void testSaving() {
		
	}

	@AfterAll
	public void cleanUp() throws DatabaseException {
		pDAO.dispose();
	}
}
