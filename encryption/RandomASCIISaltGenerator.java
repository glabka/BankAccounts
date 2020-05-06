package encryption;

import java.util.Random;

public class RandomASCIISaltGenerator extends SaltGenerator {

	@Override
	public String generateSalt(int length) {
		String str = "";
		Random rand = new Random();

		for (int i = 0; i < length; i++) {
			int randInt = rand.nextInt(256);
			str = str + String.valueOf((char) randInt);
		}
		return str;
	}

}
