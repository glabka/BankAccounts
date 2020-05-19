package id_managers;

public class StaticIDGenerator extends IDGenerator {

	private static int userAccountIDCounter = 0;
	
	private static StaticIDGenerator staticIDGenerator = new StaticIDGenerator();
	
	private StaticIDGenerator() {
		
	}
	
	public static IDGenerator getIDGenerator() {
		return staticIDGenerator;
		
	}

	@Override
	public int generateUserAccountID() {
		return userAccountIDCounter++;
	}
}
