package bankAccount;

import java.util.HashMap;
import java.util.Map;

import customExceptions.InstanceAlreadyExistsException;
import domain.BankCode;
import domain.Multiton;

public class BankAccount implements Multiton{

	/**
	 * Key is string combination of bankCode and accountNum
	 */
	private static Map<String, BankAccount> bankAccounts = new HashMap<String, BankAccount>();

	private BankCode bankCode;
	private String accountNum;
	private long accountBalace;

	private BankAccount(BankCode bankCode, String accountNum, long accountBalance) {
		this.bankCode = bankCode;
		this.accountNum = accountNum;
		this.accountBalace = accountBalance;
		bankAccounts.put(createKeyForMap(bankCode, accountNum), this);
	}

	private static String createKeyForMap(BankCode bankCode, String accountNum) {
		return bankCode.toString() + accountNum;
	}

	public static BankAccount createNewInstance(BankCode bankCode, String accountNum, long accountBalance) throws InstanceAlreadyExistsException {
		BankAccount bankAccount = bankAccounts.get(createKeyForMap(bankCode, accountNum));
		if (bankAccount == null) {
			return new BankAccount(bankCode, accountNum, accountBalance);
		} else {
			throw new InstanceAlreadyExistsException(bankAccount);
		}
	}

	/**
	 * Method for getting already instantiated BankAccount objects in programs
	 * runtime. Can return null if bankAccount wasn't instantiated yet.
	 * 
	 * @param bankCode
	 * @param accountNum
	 * @return
	 */
	public static BankAccount getInstance(BankCode bankCode, String accountNum) {
		return bankAccounts.get(createKeyForMap(bankCode, accountNum));
	}

	public BankCode getBankCode() {
		return bankCode;
	}
	public String getAccountNum() {
		return accountNum;
	}

	public long getAccountBalace() {
		return accountBalace;
	}
	
	public void setAccountBalance(int accountBalace) {
		this.accountBalace = accountBalace;
	}
	
	@Override
	public String toString() {
		return "[" + this.getBankCode() + ", " + this.getAccountNum() + ", " + this.getAccountBalace() + "]";
	}

	/**
	 * Should be used after the end of usage of certain instance of BankAccount to
	 * free allocated memory associated with this object.
	 * 
	 * BE AWARE that when used in any other way then right before loosing all
	 * references to instance of BankAccount or its end of use can lead to existence
	 * of two BankAccount instances of one actual person.
	 * 
	 * @param bankAccount
	 */
	public void dispose(BankAccount bankAccount) {
		bankAccounts.remove(createKeyForMap(bankAccount.getBankCode(), bankAccount.getAccountNum()));
	}

	/**
	 * Should be used after the end of usage of all instances of BankAccount class
	 * to free allocated memory associated with these objects.
	 * 
	 * BE AWARE that when used in any other way then right before loosing all
	 * references to all instances of BankAccount or end of use of all instance can
	 * lead to existence of two BankAccount instances of one actual person.
	 */
	public void disposeAll() {
		bankAccounts.clear();
	}

	@Override
	public String getStringId() {
		return "[" + this.getBankCode() + ", " + this.getAccountNum()+ "]";
	}

}
