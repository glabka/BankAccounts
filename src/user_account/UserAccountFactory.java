package user_account;

import commons.Constants;
import custom_exceptions.InstanceAlreadyExistsException;
import domain.BankAccountOwner;
import domain.BankCode;
import encryption.CredentialsEncrypter;
import encryption.RandomASCIISaltGenerator;
import encryption.SaltGenerator;

public class UserAccountFactory {
	
	public UserAccount createInstance(BankCode bc, BankAccountOwner accountOwner, String username,
			String password, String emailAddress, String phoneNumber) throws InstanceAlreadyExistsException {
		SaltGenerator saltGenerator = new RandomASCIISaltGenerator();
		String salt = saltGenerator.generateSalt(Constants.SALT_LENGTH);
		byte[] usernameHash = CredentialsEncrypter.hashUsername(username); // TODO check if hash is not yet in database
		byte[] passwordHash = CredentialsEncrypter.hashPassword(password, salt);
		int userAccountID = 0; // TODO - get free ID from database
		return UserAccount.createInstance(userAccountID, bc, accountOwner, usernameHash, passwordHash, salt, emailAddress, phoneNumber);
	}
	
}
