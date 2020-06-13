package commons;

import encryption.Hasher;

// TODO - add all constants of length of strings of fields saved into database for example
public class Constants {

	public static int passwordHashNumOfBytes() {
		return Hasher.getHasherInUse().getHashLength();
	}
	public static int usernameHashNumOfBytes() {
		return Hasher.getHasherInUse().getHashLength();
	}
	
	public static int SALT_LENGTH = 10;
	public static int EMAIL_ADDRESS_MAX_LENGTH = 320;
	public static int PHONE_NUMBER_MAX_LENGTH = 16;
	
}
