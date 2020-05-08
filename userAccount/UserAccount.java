package userAccount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bankAccount.BankAccount;
import customExceptions.NoSuchAlgorithmFoundException;
import domain.BankAccountOwner;
import domain.BankCode;
import encryption.BasicPasswordSalter;
import encryption.Hasher;
import encryption.PasswordSalter;
import encryption.RandomASCIISaltGenerator;
import encryption.SaltGenerator;

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

	// other fields not needed to be saved in database
	PasswordSalter passwordSalter = new BasicPasswordSalter();
	SaltGenerator saltGenerator = new RandomASCIISaltGenerator();

	private UserAccount(int userAccountID, BankCode bc, BankAccountOwner accountOwner, String username, String password,
			String emailAddress, int phoneNumber) throws NoSuchAlgorithmFoundException {
		prepareFileds(userAccountID, bc, accountOwner, hashUsername(username), hashNewPassword(password), emailAddress,
				phoneNumber);
	}

	private UserAccount(int userAccountID, BankCode bc, BankAccountOwner accountOwner, byte[] usernameHash,
			byte[] passwordHash, String emailAddress, int phoneNumber) throws NoSuchAlgorithmFoundException {
		prepareFileds(userAccountID, bc, accountOwner, usernameHash, passwordHash, emailAddress, phoneNumber);
	}

	private void prepareFileds(int userAccountID, BankCode bc, BankAccountOwner accountOwner, byte[] usernameHash,
			byte[] passwordHash, String emailAddress, int phoneNumber) {
		this.userAccountID = userAccountID;
		this.bankCode = bc;
		this.accountOwner = accountOwner;

		this.usernameHash = usernameHash;
		this.passwordHash = passwordHash;

		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;

		bankAccounts = new ArrayList<BankAccount>();

		userAccounts.put(userAccountID, this);
	}

	/**
	 * Method for creating instance of new UserAccount.
	 * 
	 * If the UserAccount is already instantiated other input parameters will be
	 * ignored in favor of fields of already instantiated UserAccount including
	 * username and password.
	 * 
	 * @param userAccountID
	 * @param bc
	 * @param accountOwner
	 * @param username
	 * @param password
	 * @param emailAddress
	 * @param phoneNumber
	 * @return
	 * @throws NoSuchAlgorithmFoundException is thrown in case an appropriate hash
	 *                                       algorithm was not found when hashing
	 *                                       password/username
	 */
	public UserAccount getInstance(int userAccountID, BankCode bc, BankAccountOwner accountOwner, String username,
			String password, String emailAddress, int phoneNumber) throws NoSuchAlgorithmFoundException {
		UserAccount userAccount = userAccounts.get(userAccountID);
		if (userAccount == null) {
			return new UserAccount(userAccountID, bc, accountOwner, username, password, emailAddress, phoneNumber);
		} else {
			return userAccount;
		}
	}

	/**
     * Method for creating instance of UserAccount from information retrieved from database.
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
	 * @throws NoSuchAlgorithmFoundException
	 */
	public UserAccount getInstance(int userAccountID, BankCode bc, BankAccountOwner accountOwner, byte[] usernameHash,
			byte[] passwordHash, String emailAddress, int phoneNumber) throws NoSuchAlgorithmFoundException {
		UserAccount userAccount = userAccounts.get(userAccountID);
		if (userAccount == null) {
			return new UserAccount(userAccountID, bc, accountOwner, usernameHash, passwordHash, emailAddress,
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

	public void changeUsername(String username) throws NoSuchAlgorithmFoundException {
		this.usernameHash = hashUsername(username);
	}

	private byte[] hashUsername(String username) throws NoSuchAlgorithmFoundException {
		return Hasher.getHasherInUse().hash(username);
	}

	public boolean verifyUsername(String username) throws NoSuchAlgorithmFoundException {
		byte[] usernameHash = hashUsername(username);
		return Arrays.equals(usernameHash, this.usernameHash);
	}

	public void changePassword(String password) throws NoSuchAlgorithmFoundException {
		this.passwordHash = hashNewPassword(password);
	}

	/**
	 * Hashes password. Salt is automatically created. This method is only for use
	 * when new account is created or new password is entered. For password
	 * verification use hashPassword(String password)
	 * 
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmFoundException
	 */
	private byte[] hashNewPassword(String password) throws NoSuchAlgorithmFoundException {
		this.salt = this.saltGenerator.generateSalt(10);
		return Hasher.getHasherInUse().hash(this.passwordSalter.saltPassword(password, this.salt));
	}

	private byte[] hashPassword(String password) throws NoSuchAlgorithmFoundException {
		return Hasher.getHasherInUse().hash(this.passwordSalter.saltPassword(password, this.salt));
	}

	public boolean verifyPassword(String password) throws NoSuchAlgorithmFoundException {
		byte[] passwordHash = hashPassword(password);
		return Arrays.equals(passwordHash, this.passwordHash);
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

	@Override
	public void finalize() throws Throwable {
		userAccounts.remove(this.userAccountID);
		super.finalize();
	}

}
