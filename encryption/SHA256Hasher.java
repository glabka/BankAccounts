	package encryption;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import customExceptions.NoSuchAlgorithmFoundException;

public class SHA256Hasher extends Hasher {

	@Override
	public byte[] hash(String password) throws NoSuchAlgorithmFoundException {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			return digest.digest(password.getBytes(StandardCharsets.UTF_8));		
		} catch (NoSuchAlgorithmException e) {
			throw new NoSuchAlgorithmFoundException(e.getMessage());
		}
		
	}

}
