package idAssigners;

import java.sql.SQLException;

import database.MySqlDatabaseConnectionManager;

public class MySqlStaticDbIDAssigner extends IDAssigner {

	{
		MySqlDatabaseConnectionManager dcm = new MySqlDatabaseConnectionManager();
		try {
			MySqlBiggestIDGetter idGetter = new MySqlBiggestIDGetter(dcm);
			personId = idGetter.getBiggestPersonID() + 1;
			personIdLoadingSuccesful = true;
		} catch (ClassNotFoundException e) {
			personIdLoadingSuccesful = false;
			e.printStackTrace();
		} catch (SQLException e) {
			personIdLoadingSuccesful = false;
			e.printStackTrace();
		}
	}

	private static int personId;
	private static boolean personIdLoadingSuccesful;
	private static MySqlStaticDbIDAssigner assigner = new MySqlStaticDbIDAssigner(); 
	
	private MySqlStaticDbIDAssigner() {
		
	}
	
	public static MySqlStaticDbIDAssigner getIDAssigner() {
		return assigner;
	}
	
	@Override
	public int getBankAccountID() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getUserAccountID() {
		// TODO implement method
		return 0;
	}
	@Override
	public int getPersonID() {
		return personId++;
	}

	
}