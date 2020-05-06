package encryption;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Hasher extends Hasher {

	@Override
	public byte[] hash(String password) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		return digest.digest(password.getBytes(StandardCharsets.UTF_8));		
	}

}
