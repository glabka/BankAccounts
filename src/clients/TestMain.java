package clients;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.neovisionaries.i18n.CountryCode;

import bank_account.BankAccount;
import dao.GenericDAO;
import dao.UserAccountDAO;
import dao.mysql.MySqlBankAccountDAO;
import dao.mysql.MySqlCompanyDAO;
import dao.mysql.MySqlPersonDAO;
import dao.mysql.MySqlUserAccountDAO;
import database.DatabaseSetup;
import database.MySqlDatabaseConnectionManager;
import database.MySqlTableDropper;
import database.TableDropper;
import domain.BankCode;
import domain.Person;
import user_account.UserAccount;
import user_account.UserAccountFactory;

public class TestMain {
	// TODO input verification in Person, Company, BankAccount, UserAccount...
	// TODO change arrays in DAO classes into LinkedHashMap

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
		
		
		
//		// MySqlPersonDAO testing
//		MySqlDatabaseConnectionManager dcm = new MySqlDatabaseConnectionManager();
//		MySqlPersonDAO pDAO = new MySqlPersonDAO(dcm);
//		pDAO.test1();
		
		
//		// New Person fields testing
//		MySqlDatabaseConnectionManager dcm = new MySqlDatabaseConnectionManager();
////		MySqlDatabaseSetup.setUpDatabse(dcm, true);
////		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
////		Date date1 = dateFormat.parse("03/09/1984");
//		
////		Person p1 = Person.getInstance("1", CountryCode.US, "Helen", null, "Miowic", date1);
////		Person p2 = Person.getInstance("2", CountryCode.US, "Adam", null, "Green", dateFormat.parse("05/20/1990"));
//		
//		PersonDAO pDAO = new MySqlPersonDAO(dcm);
////		pDAO.savePerson(p1);
////		pDAO.savePerson(p2);
//		
//		System.out.println(pDAO.getPerson("1", CountryCode.US));
		
		
//		// MySqlPersonDAO update method
//		MySqlDatabaseConnectionManager dcm = new MySqlDatabaseConnectionManager();
//		PersonDAO pDAO = new MySqlPersonDAO(dcm);
//		Person p = pDAO.getInstance(1);
//		p.setFirstName("Ailian");
//		pDAO.updateEntry(p);
		
		
//		// New Person fields testing 2
//		MySqlDatabaseConnectionManager dcm = new MySqlDatabaseConnectionManager();
//		PersonDAO pDAO = new MySqlPersonDAO(dcm);
////		List<GenericDAO<?>> daos = new ArrayList<>();
////		daos.add(new MySqlPersonDAO(dcm));
////		DatabaseSetup.setUpDatabse(daos, true);
////		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
////	Date date1 = dateFormat.parse("03/09/1984");
////		
////		Person p1 = Person.getInstance(1, CountryCode.US, "Helen", null, "Miowic", date1);
////		Person p2 = Person.getInstance(2, CountryCode.US, "Adam", null, "Green", dateFormat.parse("05/20/1990"));
////		
////		pDAO.saveInstance(p1);
////		pDAO.saveInstance(p2);
//		
//		Person p = pDAO.getInstance(1);
//		System.out.println(p);
//		pDAO.deleteEntry(p);
//		p = pDAO.getInstance(1);
//		System.out.println(p);
////		System.out.println(pDAO.isIdFree(1));
////		System.out.println(pDAO.isIdFree(3));
		
		
//		// New Company fields testing
//		MySqlDatabaseConnectionManager dcm = new MySqlDatabaseConnectionManager();
//		List<GenericDAO<?>> daos = new ArrayList<>();
//		PersonDAO pDAO = new MySqlPersonDAO(dcm);
//		daos.add(pDAO);
//		CompanyDAO cDAO = new MySqlCompanyDAO(dcm,pDAO);
//		daos.add(cDAO);
//		DatabaseSetup.setUpDatabse(daos, false);
//		
//		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//		Date date1 = dateFormat.parse("05/04/1985");
//		
//		Person p1 = Person.getInstance(3, CountryCode.US, "Curtis", null, "Jackson", date1);
////		pDAO.saveInstance(p1);
////		Company c1 = Company.getInstance(1, "Abeco Inc.", "New York City", p1);
////		cDAO.saveInstance(c1);
////		Company.dispose(c1);
////		c1 = null;
//		Company c = cDAO.getInstance(1);
//		System.out.println(c);
//		c.setHeadquarters("Los Angeles");
//		cDAO.updateEntry(c);
//		Company.dispose(c);
//		c = null;
//		c = cDAO.getInstance(1);
//		System.out.println(c);
//		
////		cDAO.deleteEntry(c);
////		c = cDAO.getInstance(1);
////		System.out.println(c);
		
		
		// MySqlBankAccountDAO testing
//		MySqlDatabaseConnectionManager dcm = new MySqlDatabaseConnectionManager();
//		List<GenericDAO<?>> daos = new ArrayList<>();
//		PersonDAO pDAO = new MySqlPersonDAO(dcm);
//		daos.add(pDAO);
//		CompanyDAO cDAO = new MySqlCompanyDAO(dcm,pDAO);
//		daos.add(cDAO);
//		BankAccountDAO baDAO = new MySqlBankAccountDAO(dcm);
//		daos.add(baDAO);
//		DatabaseSetup.setUpDatabase(daos);
//		
//		String accountNum = "123456789012";
//		BankAccount ba = BankAccount.createNewInstance(BankCode.C1000, accountNum, 5);
//		baDAO.saveInstance(ba);
//		ba.dispose(ba);
//		ba = baDAO.loadInstance(BankCode.C1000, accountNum);
//		System.out.println(ba);
//		
//		ba.setAccountBalance(10);
//		baDAO.updateEntry(ba);
//		ba.dispose(ba);
//		ba = baDAO.loadInstance(BankCode.C1000, accountNum);
//		System.out.println(ba);
//		
//		baDAO.deleteEntry(ba);
//		ba = baDAO.loadInstance(BankCode.C1000, accountNum);
//		System.out.println("should be null: " + ba);
		
		
		MySqlDatabaseConnectionManager dcm = new MySqlDatabaseConnectionManager();
		List<GenericDAO<?>> daos = new ArrayList<>();
		MySqlPersonDAO pDAO = new MySqlPersonDAO(dcm);
		daos.add(pDAO);
		MySqlCompanyDAO cDAO = new MySqlCompanyDAO(dcm, pDAO);
		daos.add(cDAO);
		MySqlBankAccountDAO baDAO = new MySqlBankAccountDAO(dcm);
		daos.add(baDAO);
		UserAccountDAO uaDAO = new MySqlUserAccountDAO(dcm, baDAO, pDAO, cDAO);
		daos.add(uaDAO);
		TableDropper td = new MySqlTableDropper(dcm);
		DatabaseSetup.setUpDatabase(daos, td);
		
		// creating user account
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Person p = Person.createNewInstance(1, CountryCode.CZ, "Řehoř", null, "Novák", formatter.parse("12/03/1998"));
		UserAccountFactory uaf = new UserAccountFactory();
		UserAccount ua = uaf.createInstance(BankCode.C3030, p, "rehor", "root", "rehor.novak@seznam.cz", "+420732195755");
		
		BankAccount ba = BankAccount.createNewInstance(BankCode.C3030, "11111111111", 0);
		ua.addBankAccount(ba);
		BankAccount ba2 = BankAccount.createNewInstance(BankCode.C3030, "0123456789", 10);
		ua.addBankAccount(ba2);
		
		uaDAO.saveInstance(ua);
		
	}

}
