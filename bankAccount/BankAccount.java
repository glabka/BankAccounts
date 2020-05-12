package bankAccount;

import java.util.HashMap;
import java.util.Map;

import domain.BankCode;

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
	
	public int getAccountBalace() {
		return accountBalace;
	}
	
	/**
	 * Should be used after the end of usage of certain instance of BankAccount to free allocated
	 * memory associated with this object. 
	 * 
	 * BE AWARE that when used in any other way then right before loosing all references to instance of BankAccount
	 * or its end of use can lead to existence of two BankAccount instances of one actual person.
	 * @param person
	 */
	public void dispose(BankAccount bankAccount) {
		bankAccounts.remove(createKeyForMap(bankAccount.getBankCode(), bankAccount.getAccountNum()));
	}
	
	/**
	 * Should be used after the end of usage of all instances of BankAccount class to free allocated
	 * memory associated with these objects. 
	 * 
	 * BE AWARE that when used in any other way then right before loosing all references to all instances of BankAccount
	 * or end of use of all instance can lead to existence of two BankAccount instances of one actual person.
	 * @param person
	 */
	public void disposeAll() {
		bankAccounts.clear();
	}

}
