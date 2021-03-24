package easymall.util;

import java.util.Random;

public class number {
	public String getCheckCode() {
		String ZiMu = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGJKLZXCVBNM1234567890";
		String result = "";
		Random random = new Random();
		for (int i = 0; i < 4; i++) {
			int index = random.nextInt(ZiMu.length());
			char c = ZiMu.charAt(index);
			result += c;
		}
		return result;
	}
}
