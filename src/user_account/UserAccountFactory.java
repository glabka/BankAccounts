package user_account;

import domain.BankAccountOwner;
import domain.BankCode;
import encryption.CredentialsEncrypter;
import encryption.RandomASCIISaltGenerator;
import encryption.SaltGenerator;

public class UserAccountFactory {

	private static final int SALT_LENGTH= 10;
	
	public UserAccount getInstance(BankCode bc, BankAccountOwner accountOwner, String username,
			String password, String emailAddress, int phoneNumber) {
		SaltGenerator saltGenerator = new RandomASCIISaltGenerator();
		String salt = saltGenerator.generateSalt(SALT_LENGTH);
		byte[] usernameHash = CredentialsEncrypter.hashUsername(username); // TODO check if hash is not yet in database
		byte[] passwordHash = CredentialsEncrypter.hashPassword(password, salt);
		int userAccountID = 0; // TODO - get free ID from database
		return UserAccount.getInstance(userAccountID, bc, accountOwner, usernameHash, passwordHash, salt, emailAddress, phoneNumber);
	}
	
}
