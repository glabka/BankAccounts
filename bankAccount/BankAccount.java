package bankAccount;

import java.util.HashMap;
import java.util.Map;

import customExceptions.AccountNumTakenException;
import domain.BankCode;
import idManagers.BankAccountNumChecker;
import idManagers.StaticIDAssigner;
import idManagers.TrivialBankAccountNumChecker;

public class BankAccount {

	/**
	 * Key is string combination of bankCode and accountNum
	 */
	private static Map<String, BankAccount> bankAccounts = new HashMap<String, BankAccount>();

	private BankCode bankCode;
	private String accountNum;
	private int accountBalace;

	private BankAccount(BankCode bankCode, String accountNum) {
		this.bankCode = bankCode;
		this.accountNum = accountNum;
		this.accountBalace = 0;
		bankAccounts.put(createKeyForMap(bankCode, accountNum), this);
	}
	
	private static String createKeyForMap(BankCode bankCode, String accountNum) {
		return bankCode.toString() + accountNum;
	}
	
	public static BankAccount getInstance(BankCode bankCode, String accountNum) {
		BankAccount bankAccount = bankAccounts.get(createKeyForMap(bankCode, accountNum));
		if(bankAccount == null) {
			return new BankAccount(bankCode, accountNum);
		} else {
			return bankAccount;
		}
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
	
	@Override
	public void finalize() throws Throwable {
		bankAccounts.remove(createKeyForMap(this.bankCode, this.accountNum));
		super.finalize();
	}

}
