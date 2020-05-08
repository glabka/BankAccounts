package encryption;

public abstract class Hasher {
	
	private static Hasher hasher = new SHA256Hasher();
	public static Hasher getHasherInUse() {
		return hasher;
	}
	
	public abstract byte[] hash(String stringForHashing);
}
