package overall_tests.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.neovisionaries.i18n.CountryCode;

import customExceptions.DatabaseException;
import dao.GenericDAO;
import dao.PersonDAO;
import dao.mysql.MySqlPersonDAO;
import database.DatabaseSetup;
import database.MySqlDatabaseConnectionManager;
import domain.Person;

class PersonDAOTest {

	@BeforeAll
	public void prepare() throws ClassNotFoundException, DatabaseException, ParseException {
		MySqlDatabaseConnectionManager dcm = new MySqlDatabaseConnectionManager();
		List<GenericDAO<?>> daos = new ArrayList<>();
		PersonDAO pDAO = new MySqlPersonDAO(dcm);
		daos.add(pDAO);
		DatabaseSetup.setUpDatabse(daos, true);

		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		Person p1 = Person.getInstance(1, CountryCode.US, "Helen", null, "Miowic", dateFormat.parse("03/09/1984"));
		Person p2 = Person.getInstance(2, CountryCode.US, "Adam", null, "Green", dateFormat.parse("05/20/1990"));
	}

	@Test
	public void test() {

	}

}
