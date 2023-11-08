public class DecryptAscii {
	private GeneratorKey generator;

	public GeneratorKey getGenerator() {
		return generator;
	}

	public void setGenerator(GeneratorKey generator) {
		this.generator = generator;
	}

	// 解密方法，接受一个字节矩阵 message
	public byte[][] decrypt(byte[][] message) {
		byte[][] nibble = message;

		// 从密钥生成器获取第四轮的轮密钥 key3
		byte[][] key3 = generator.keyNibs(4, 5);
		byte[][] ss1 = Applyroundkey.add(nibble, key3);

		// 对 ss1 执行ShiftRows操作
		byte[][] ss2 = ShiftRows.shift(ss1);

		// 对 ss2 执行SubNibbles操作
		byte[][] ss3 = SubNibbles.insertSub(ss2);

		// 从密钥生成器获取第二轮的轮密钥 key2
		byte[][] key2 = generator.keyNibs(2, 3);
		byte[][] ss4 = Applyroundkey.add(ss3, key2);

		// 对 ss4 执行MixColumns操作
		byte[][] ss5 = MixColumns.invertMix(ss4);

		// 再次执行ShiftRows操作
		byte[][] ss6 = ShiftRows.shift(ss5);

		// 再次执行SubNibbles操作
		byte[][] ss7 = SubNibbles.insertSub(ss6);

		// 从密钥生成器获取第一轮的轮密钥 key1
		byte[][] key1 = generator.keyNibs(0, 1);
		byte[][] ss8 = Applyroundkey.add(ss7, key1);

		// 最终的解密结果存储在 cipheranswer 中
		byte[][] cipheranswer = ss8;
		return cipheranswer;
	}

	// 解密ASCII字符串的静态方法
	public static String Decrypt_Ascii(String Plain_string, String key_string) {
		String Ciphertexts = new String();
		byte[] key = Basicfunction.stoByte(key_string);
		DecryptAscii saes = new DecryptAscii();
		GeneratorKey newKey = new GeneratorKey(key);
		saes.setGenerator(newKey);
		newKey.generate();

		// 遍历明文字符串并解密
		for (int i = 0; i < Plain_string.length(); i += 2) {
			char ch1 = Plain_string.charAt(i);
			char ch2 = Plain_string.charAt(i + 1);
			int cnum1 = (int) ch1;
			int cnum2 = (int) ch2;

			// 将字符转换为二进制字符串
			String Splaintests1 = Basicfunction.Addzero(Integer.toBinaryString(cnum1));
			String Splaintests2 = Basicfunction.Addzero(Integer.toBinaryString(cnum2));

			// 创建字节矩阵表示明文
			byte[][] messageASNibbles = {
					{ Basicfunction.btoBytes(Splaintests1.substring(0, 4)), Basicfunction.btoBytes(Splaintests2.substring(0, 4)) },
					{ Basicfunction.btoBytes(Splaintests1.substring(4, 8)), Basicfunction.btoBytes(Splaintests2.substring(4, 8)) }
			};

			// 解密明文
			byte[][] encrypted = saes.decrypt(messageASNibbles);

			// 转换解密后的字节矩阵为字符并添加到 Ciphertexts 中
			byte[] eencryptedarr = Basicfunction.ntoArray(encrypted);
			int[] Scipherarray = Basicfunction.bytoInt2(eencryptedarr);
			for (int k = 0; k < Scipherarray.length; k++) {
				System.out.print(Scipherarray[k]);
			}
			int[] Scipherarray1 = new int[8];
			int[] Scipherarray2 = new int[8];
			for (int k = 0; k < 8; k++) {
				Scipherarray1[k] = Scipherarray[k];
				Scipherarray2[k] = Scipherarray[k + 8];
			}
			System.out.println();
			Ciphertexts += (char) Basicfunction.Solve(Scipherarray1);
			Ciphertexts += (char) Basicfunction.Solve(Scipherarray2);
		}
		return Ciphertexts;
	}
}
