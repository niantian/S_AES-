public class SAEScbc {
	public static void SCBC(String string1, String string2, String keyString, String cbctext, int state) {
		if (state == 0) {
			System.out.println("明文1：" + string1);
			System.out.println("明文2：" + string2);
			System.out.println("初始向量：" + cbctext);

			String Cipher1 = Encryptbinary.Encryd(XOR(string1, cbctext), keyString);
			String Cipher2 = Encryptbinary.Encryd(XOR(string2, Cipher1), keyString);
			System.out.println("密文1：" + Cipher1);
			System.out.println("密文2：" + Cipher2);
		} else {
			System.out.println("密文1：" + string1);
			System.out.println("密文2：" + string2);
			System.out.println("初始向量：" + cbctext);

			String Plain1 = XOR(Decryptbinary.Decryd(string1, keyString), cbctext);
			String Plain2 = XOR(Decryptbinary.Decryd(string2, keyString), string1);

			System.out.println("明文1：" + Plain1);
			System.out.println("明文2：" + Plain2);
		}
	}

	public static String XOR(String s1, String s2) {
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) == s2.charAt(i)) {
				output.append('0');
			} else {
				output.append('1');
			}
		}
		return output.toString();
	}

	public static void main(String args[]) {

		System.out.println("CBC加密过程");
		SCBC("1010101010101010", "1101101101101101", "0010110110101100", "1000110110100101", 0);

		System.out.println("CBC解密过程");
		SCBC("0111010101110000", "0111010101110000", "0010110110101100", "1000110110100101", 1);

		System.out.println("篡改密文后的解密过程");
		SCBC("1110101101101000", "0110111101101001", "0010110110101100", "1000110110100101", 1);
	}

}