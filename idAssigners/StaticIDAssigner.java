package idAssigners;

public class StaticIDAssigner extends IDAssigner {

	private static int bankAccountIDCounter = 0;
	private static int userAccountIDCounter = 0;
	private static int personIDCounter = 0;
	
	private static StaticIDAssigner staticIDAssigner = new StaticIDAssigner();
	
	private StaticIDAssigner() {
		
	}
	
	public static IDAssigner getIDAssigner() {
		return staticIDAssigner;
		
	}
	
	@Override
	public int getBankAccountID() {
		return bankAccountIDCounter++;
	}

	@Override
	public int getUserAccountID() {
		return userAccountIDCounter++;
	}

	@Override
	public int getPersonID() {
		return personIDCounter++;
	}

}
