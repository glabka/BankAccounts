package database;

import customExceptions.DatabaseException;
import dao.BankAccountDAO;
import dao.CompanyDAO;
import dao.PersonDAO;
import dao.UserAccountDAO;

public class DatabaseSetup {

	public static void setUpDatabase(BankAccountDAO baDAO, CompanyDAO cDAO, PersonDAO pDAO, UserAccountDAO uaDAO) throws ClassNotFoundException, DatabaseException {
		setUpDatabse(baDAO, cDAO, pDAO, uaDAO, false);
	}

	
	public static void setUpDatabse(BankAccountDAO baDAO, CompanyDAO cDAO, PersonDAO pDAO, UserAccountDAO uaDAO, boolean forced)
			throws ClassNotFoundException, DatabaseException {
		baDAO.setUpDatabase(forced);
		cDAO.setUpDatabase(forced);
		pDAO.setUpDatabase(forced);
		uaDAO.setUpDatabase(forced);
	}
}
