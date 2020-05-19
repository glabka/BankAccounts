package customExceptions;

import domain.BankCode;

public class AccountNumTakenException extends Exception {
	
	public AccountNumTakenException() {
		super("Account number is already taken.");
	}
	
	public AccountNumTakenException(BankCode bankCode, String accountNum) {
		super("Account number " + accountNum + "for bank " + bankCode + " is already taken.");
	}
}
