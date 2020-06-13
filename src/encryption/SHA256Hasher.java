package encryption;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import custom_exceptions.HashFailureException;

public class SHA256Hasher extends Hasher {

	private MessageDigest digest;
	
	public SHA256Hasher() {
		try {
			digest = MessageDigest.getInstance("SHA-256");		
		} catch (NoSuchAlgorithmException e) {
			throw new HashFailureException(e.getMessage());
		}
	}
	
	@Override
	public int getHashLength() {
		return digest.getDigestLength();
	}
	
	@Override
	public byte[] hash(String password) {
		return digest.digest(password.getBytes(StandardCharsets.UTF_8));		
	}

}
