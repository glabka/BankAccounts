package idManagers;
import domain.BankCode;

public class TrivialBankAccountNumChecker extends BankAccountNumChecker{

	@Override
	public boolean isAccountNumFree(BankCode bankCode, String accountNum) {
		return true;
	}

}
