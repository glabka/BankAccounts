package clients;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.neovisionaries.i18n.CountryCode;

import dao.MySqlPersonDAO;
import dao.PersonDAO;
import database.MySqlDatabaseConnectionManager;
import database.MySqlDatabaseSetup;
import domain.Person;

public class TestMain {

	public static void main(String[] args) throws Exception {
//		Person p1 = Person.getIstance(0, "Helen", null, "Miowic");
//		Person p2 =  Person.getIstance(1,"Adam", null, "Green");
//		System.out.println("p1.id = " + p1.getId());
//		System.out.println("p2.id = " + p2.getId());
//		MySqlDatabaseConnectionManager dcm = new MySqlDatabaseConnectionManager();
//		PersonDAO pDAO = new MySqlPersonDAO(dcm);
//		pDAO.savePerson(p1);
//		pDAO.savePerson(p2);

//		Person p = Person.getInstance(0);
//		if(p == null) {
//			System.out.println("p == null");
//		} else {
//			System.out.println(p);
//		}

//		MySqlDatabaseConnectionManager dcm = new MySqlDatabaseConnectionManager();
//		PersonDAO pDAO = new MySqlPersonDAO(dcm);
//		Person p = pDAO.getPerson(0);
//		System.out.println(p);
//		p = Person.getInstance(0);
//		System.out.println(p);
		
//		MySqlDatabaseConnectionManager dcm = new MySqlDatabaseConnectionManager();
//		MySqlDatabaseSetup.setUpDatabse(dcm, false);
		
//		SaltGenerator saltGen = new RandomASCIISaltGenerator1();
//		System.out.println(saltGen.generateSalt(10));
		
//		PasswordSalter ps = new BasicPasswordSalter();
//		System.out.println(ps.saltPassword("abcde","12"));
//		System.out.println(ps.saltPassword("abcde","12345"));
//		System.out.println(ps.saltPassword("abcde","1234567"));
		
		
		// New Person fields testing
//		MySqlDatabaseConnectionManager dcm = new MySqlDatabaseConnectionManager();
////		MySqlDatabaseSetup.setUpDatabse(dcm, true);
//		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//		Date date1 = dateFormat.parse("03/09/1984");
//		
////		Person p1 = Person.getInstance("1", CountryCode.US, "Helen", null, "Miowic", date1);
////		Person p2 = Person.getInstance("2", CountryCode.US, "Adam", null, "Green", dateFormat.parse("05/20/1990"));
//		
//		PersonDAO pDAO = new MySqlPersonDAO(dcm);
////		pDAO.savePerson(p1);
////		pDAO.savePerson(p2);
//		
//		System.out.println(pDAO.getPerson("1", CountryCode.US));
		
		
		
	}

}
