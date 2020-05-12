package userAccount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bankAccount.BankAccount;
import domain.BankAccountOwner;
import domain.BankCode;

public class UserAccount {

	private static Map<Integer, UserAccount> userAccounts = new HashMap<Integer, UserAccount>();

	private int userAccountID;
	private BankCode bankCode;
	private BankAccountOwner accountOwner;
	private byte[] usernameHash;
	private byte[] passwordHash;
	private String salt;
	private String emailAddress;
	private int phoneNumber;

	private List<BankAccount> bankAccounts;

	private UserAccount(int userAccountID, BankCode bc, BankAccountOwner accountOwner, byte[] usernameHash,
			byte[] passwordHash, String salt, String emailAddress, int phoneNumber) {
		prepareFileds(userAccountID, bc, accountOwner, usernameHash, passwordHash, salt, emailAddress, phoneNumber);
	}

	private void prepareFileds(int userAccountID, BankCode bc, BankAccountOwner accountOwner, byte[] usernameHash,
			byte[] passwordHash, String salt, String emailAddress, int phoneNumber) {
		this.userAccountID = userAccountID;
		this.bankCode = bc;
		this.accountOwner = accountOwner;

		this.usernameHash = usernameHash;
		this.passwordHash = passwordHash;
		this.salt = salt;

		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;

		bankAccounts = new ArrayList<BankAccount>();

		userAccounts.put(userAccountID, this);
	}


	/**
     * Method for creating instance of UserAccount.
	 * 
	 * If the UserAccount is already instantiated other input parameters will be
	 * ignored in favor of fields of already instantiated UserAccount.
	 * 
	 * @param userAccountID
	 * @param bc
	 * @param accountOwner
	 * @param usernameHash
	 * @param passwordHash
	 * @param emailAddress
	 * @param phoneNumber
	 * @return
	 */
	public static UserAccount getInstance(int userAccountID, BankCode bc, BankAccountOwner accountOwner, byte[] usernameHash,
			byte[] passwordHash, String salt, String emailAddress, int phoneNumber){
		UserAccount userAccount = userAccounts.get(userAccountID);
		if (userAccount == null) {
			return new UserAccount(userAccountID, bc, accountOwner, usernameHash, passwordHash, salt, emailAddress,
					phoneNumber);
		} else {
			return userAccount;
		}
	}

	/**
	 * Method for getting already instantiated UserAccount objects in programs
	 * runtime. Can return null if UserAccount wasn't instantiated yet.
	 * 
	 * @param userAccountID
	 * @return
	 */
	public UserAccount getInstance(int userAccountID) {
		return userAccounts.get(userAccountID);
	}

	public boolean addBankAccount(BankAccount ba) {
		if (this.bankCode == ba.getBankCode()) {
			if (!bankAccounts.contains(ba)) {
				bankAccounts.add(ba);
//				bankAccounts.sort(c); // TODO
			}
			return true;
		} else {
			return false;
		}
	}
	
	public void changeUsernameHash(byte[] usernameHash) {
		this.usernameHash = Arrays.copyOf(usernameHash, usernameHash.length);
	}
	
	public byte[] getUsernameHash() {
		return Arrays.copyOf(this.usernameHash, this.usernameHash.length);
	}
	
	public void changePasswordHashAndSalt(byte[] passwordHash, String salt) {
		this.passwordHash = Arrays.copyOf(passwordHash, passwordHash.length);
		this.salt = salt;
	}

	public byte[] getPasswordHash() {
		return Arrays.copyOf(this.passwordHash, this.passwordHash.length);
	}
	
	public String getSalt() {
		return this.salt;
	}
	
	public BankAccountOwner getAccountOwner() {
		return accountOwner;
	}

	public void setAccountOwner(BankAccountOwner accountOwner) {
		this.accountOwner = accountOwner;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getUserAccountID() {
		return userAccountID;
	}

	public BankCode getBankCode() {
		return bankCode;
	}
	
	/**
	 * Should be used after the end of usage of certain instance of UserAccount to free allocated
	 * memory associated with this object. 
	 * 
	 * BE AWARE that when used in any other way then right before loosing all references to instance of UserAccount 
	 * or its end of use can lead to existence of two UserAccount instances of one actual person.
	 * @param userAccount
	 */
	public void dispose(UserAccount userAccount) {
		userAccounts.remove(userAccount.getUserAccountID());
	}
	
	/**
	 * Should be used after the end of usage of all instances of UserAccount class to free allocated
	 * memory associated with these objects. 
	 * 
	 * BE AWARE that when used in any other way then right before loosing all references to all instances of UserAccount
	 * or end of use of all instance can lead to existence of two UserAccount instances of one actual person.
	 */
	public void disposeAll() {
		userAccounts.clear();
	}

}
