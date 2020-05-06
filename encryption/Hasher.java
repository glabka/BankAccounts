package encryption;

import customExceptions.NoSuchAlgorithmFoundException;

public abstract class Hasher {
	
	private static Hasher hasher = new SHA256Hasher();
	public static Hasher getHasherInUse() {
		return hasher;
	}
	
	public abstract byte[] hash(String stringForHashing) throws NoSuchAlgorithmFoundException;
}
