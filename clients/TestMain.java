package clients;

import dao.MySqlPersonDAO;
import dao.PersonDAO;
import database.MySqlDatabaseConnectionManager;
import domain.Person;

public class TestMain {

	public static void main(String[] args) throws Exception {
		Person p1 = Person.getIstance(0, "Helen", null, "Miowic");
		Person p2 =  Person.getIstance(1,"Adam", null, "Green");
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
	}

}
