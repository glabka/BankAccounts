package idManagers;
import customExceptions.DatabaseException;
import domain.BankCode;

public abstract class BankAccountNumChecker {
	public abstract boolean isAccountNumFree(BankCode bankCode, String accountNum) throws DatabaseException;
}
