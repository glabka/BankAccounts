package encryption;

public abstract class PasswordSalter {
	public abstract String saltPassword(String password, String salt);
}
