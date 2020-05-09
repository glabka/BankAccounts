package encryption;

public class BasicPasswordSalter extends PasswordSalter{

	@Override
	public String saltPassword(String password, String salt) {
		int minLen = Math.min(password.length(), salt.length());
		String saltedPassword = "";
		
		// salting password
		for (int i = 0; i < minLen; i++) {
			for (int j = 0; j < 2; j++) {
				if(j == 0) {
					saltedPassword += String.valueOf(password.charAt(i));
				} else {
					saltedPassword += String.valueOf(salt.charAt(i));
				}
			}
		}
		
		// adding rest of password/salt
		String rest = password.length() > salt.length() ? password : salt;
		saltedPassword += rest.substring(minLen);
		return saltedPassword;
	}

}
