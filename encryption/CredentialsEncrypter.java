package encryption;

public class CredentialsEncrypter {
	
	private static Hasher hasher = new SHA256Hasher();
	private static PasswordSalter passwordSalter = new BasicPasswordSalter();	
	
	public static byte[] hashUsername(String username) {
		return hasher.hash(username);
	}
	
	public static byte[] hashPassword(String password, String salt) {
		return hasher.hash(passwordSalter.saltPassword(password, salt));
	}
	
}
