package bankAccount;
import java.util.HashMap;
import java.util.Map;

import bankAccount.numCheckers.BankAccountNumChecker;
import bankAccount.numCheckers.TrivialBankAccountNumChecker;
import customExceptions.AccountNumTakenException;
import domain.BankCode;
import idAssigners.StaticIDAssigner;

public class BankAccount {

	/**
	 * Key is string combination of bankCode and accountNum
	 */
	private static Map<String, BankAccount> bankAccounts = new HashMap<String, BankAccount>();
	
	private BankCode bankCode;
	private String accountNum;
	private int accountBalace;
	
	
	public BankAccount(BankCode bankCode, String accountNum) throws AccountNumTakenException {
		BankAccountNumChecker checker = new TrivialBankAccountNumChecker();
		if(checker.isAccountNumFree(bankCode, accountNum)) {
			this.bankCode = bankCode;
			this.accountNum = accountNum;
		} else {
			throw new AccountNumTakenException(bankCode, accountNum);
		}
		this.accountBalace = 0;
		bankAccounts.put(bankCode.toString() + accountNum, this);
	}
	
	


	public BankCode getBankCode() {
		return bankCode;
	}


	public void setBankCode(BankCode bankCode) {
		this.bankCode = bankCode;
	}


	public String getAccountNum() {
		return accountNum;
	}


	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	
}
