package bankAccount.numCheckers;
import domain.BankCode;

public abstract class BankAccountNumChecker {
	public abstract boolean isAccountNumFree(BankCode bankCode, String accountNum);
}
