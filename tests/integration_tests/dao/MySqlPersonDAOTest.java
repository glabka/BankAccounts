package integration_tests.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.neovisionaries.i18n.CountryCode;

import custom_exceptions.DatabaseException;
import custom_exceptions.InstanceAlreadyExistsException;
import custom_exceptions.InstanceAlreadySavedException;
import dao.GenericDAO;
import dao.PersonDAO;
import dao.mysql.MySqlBankAccountDAO;
import dao.mysql.MySqlCompanyDAO;
import dao.mysql.MySqlPersonDAO;
import dao.mysql.MySqlUserAccountDAO;
import database.DatabaseSetup;
import database.MySqlDatabaseConnectionManager;
import database.MySqlTableDropper;
import domain.Person;

// TODO test rest of the methods - isIdFree() etc.
/**
 * Be aware that the test violates the proper use of multiton Person as described in Person class
 * for reason of convenience.
 *
 */
@TestInstance(Lifecycle.PER_CLASS)
class PersonDAOTest {

	private Person[] people = new Person[2];
	private int[] ids = { 1, 2 };
	private CountryCode[] countries = { CountryCode.US, CountryCode.GB };
	private String[] firstNames = { "Helen", "Adam" };
	private String[] middleNames = { "Amalie", null };
	private String[] lastNames = { "Miowic", "Green" };
	private Date[] birthdays = new Date[2];
	private MySqlDatabaseConnectionManager dcm;
	private MySqlPersonDAO pDAO;
	private MySqlCompanyDAO cDAO;
	private MySqlBankAccountDAO baDAO;
	private MySqlUserAccountDAO uaDAO;
	private SimpleDateFormat dateFormat;

	@BeforeAll
	public void prepare()
			throws ClassNotFoundException, DatabaseException, ParseException, InstanceAlreadyExistsException {
		dcm = new MySqlDatabaseConnectionManager();
		List<GenericDAO<?>> daos = new ArrayList<>();
		pDAO = new MySqlPersonDAO(dcm);
		daos.add(pDAO);
		daos.add(baDAO = new MySqlBankAccountDAO(dcm));
		daos.add(cDAO = new MySqlCompanyDAO(dcm, pDAO));
		daos.add(uaDAO = new MySqlUserAccountDAO(dcm, baDAO, pDAO, cDAO));
		DatabaseSetup.setUpDatabase(daos, new MySqlTableDropper(dcm));

		dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		birthdays[0] = dateFormat.parse("03/09/1984");
		birthdays[1] = dateFormat.parse("05/20/1990");

		people[0] = Person.createNewInstance(ids[0], countries[0], firstNames[0], middleNames[0], lastNames[0],
				birthdays[0]);
		people[1] = Person.createNewInstance(ids[1], countries[1], firstNames[1], middleNames[1], lastNames[1],
				birthdays[1]);
	}

	@Test
	public void testSavingAndLoading()
			throws DatabaseException, InstanceAlreadySavedException, InstanceAlreadyExistsException {
		// saving instances
		pDAO.saveInstance(people[0]);
		pDAO.saveInstance(people[1]);
		Assertions.assertThrows(InstanceAlreadySavedException.class, () -> pDAO.saveInstance(people[1]));

		// cleaning multiton instances so new instances can be loaded from database
		// people is not freed therefore the proper use of multiton implementation is
		// violated -> two instances of same object can be created (without
		// InstanceAlreadyExistException).
		people[0].dispose();
		people[1].dispose();
		Assert.assertTrue(Person.getInstance(0) == null);
		Assert.assertTrue(Person.getInstance(1) == null);

		// loading instances
		Person[] loadedPeople = new Person[2];
		loadedPeople[0] = pDAO.loadInstance(ids[0]);
		loadedPeople[1] = pDAO.loadInstance(ids[1]);

		for (int i = 0; i < people.length; i++) {
			assertPersonFields(people[i], loadedPeople[i]);
		}

	}
	
	private void assertPersonFields(Person expected, Person underTest) {
		Assert.assertTrue(expected.getId() == underTest.getId());
		Assert.assertTrue(expected.getFirstName().equals(underTest.getFirstName()));
		if (expected.getMiddleName() == null) {
			Assert.assertTrue(expected.getMiddleName() == underTest.getMiddleName());
		} else {
			Assert.assertTrue(expected.getMiddleName().equals(underTest.getMiddleName()));
		}
		Assert.assertTrue(expected.getLastName().equals(underTest.getLastName()));
		Assert.assertTrue(expected.getBirthdate().equals(underTest.getBirthdate()));
	}
	
	@Test
	public void testLoading() throws InstanceAlreadyExistsException, ParseException, DatabaseException, InstanceAlreadySavedException {
		int id = 3;
		Person person = Person.createNewInstance(id, CountryCode.FR, "Jean", null, "Picard", dateFormat.parse("03/22/2201"));
		pDAO.saveInstance(person);
		person.dispose();
		Assert.assertTrue(Person.getInstance(id) == null);
		
		// verifying that instance was properly saved
		Person loadedPerson = pDAO.loadInstance(id);
		assertPersonFields(person, loadedPerson);
		
		// updating entry in database
		loadedPerson.setFirstName("Jean-Luc");
		pDAO.updateEntry(loadedPerson);
		
		
		loadedPerson.dispose();
		Assert.assertTrue(Person.getInstance(id) == null);
		
		// verifying that updated information was saved
		Person loadedUpdatedPerson = pDAO.loadInstance(id);
		assertPersonFields(loadedPerson, loadedUpdatedPerson);
	}
	
	@Test
	public void testDelete() throws InstanceAlreadyExistsException, ParseException, DatabaseException, InstanceAlreadySavedException {
		int id = 4;
		Person person = Person.createNewInstance(id, CountryCode.CZ, "Tomáš", null, "Smetana", dateFormat.parse("2/20/1990"));
		pDAO.saveInstance(person);
		person.dispose();
		Assert.assertTrue(Person.getInstance(id) == null);
		
		// deleting the entry and verifying the the entry was deleted
		pDAO.deleteEntry(person);
		Assert.assertTrue(pDAO.loadInstance(id) == null);
		
		// no exception is thrown when deleting already deleted entry
		pDAO.deleteEntry(person);
		
	}

	@AfterAll
	public void cleanUp() throws DatabaseException {
		pDAO.dispose();
	}
}
