public class Decryptbinary {
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
		byte[][] step1 = Applyroundkey.add(nibble, key3);

		// 对 step1 执行ShiftRows操作
		byte[][] step2 = ShiftRows.shift(step1);

		// 对 step2 执行SubNibbles操作
		byte[][] step3 = SubNibbles.insertSub(step2);

		// 从密钥生成器获取第二轮的轮密钥 key2
		byte[][] key2 = generator.keyNibs(2, 3);
		byte[][] step4 = Applyroundkey.add(step3, key2);

		// 对 step4 执行MixColumns操作
		byte[][] step5 = MixColumns.invertMix(step4);

		// 再次执行ShiftRows操作
		byte[][] step6 = ShiftRows.shift(step5);

		// 再次执行SubNibbles操作
		byte[][] step7 = SubNibbles.insertSub(step6);

		// 从密钥生成器获取第一轮的轮密钥 key1
		byte[][] key1 = generator.keyNibs(0, 1);
		byte[][] step8 = Applyroundkey.add(step7, key1);

		// 最终的解密结果存储在 cipherNibble 中
		byte[][] cipherNibble = step8;
		return cipherNibble;
	}

	// 解密二进制字符串的静态方法
	public static String Decryd(String Plain_string, String key_string) {
		String output = new String();
		Decryptbinary saes = new Decryptbinary();
		byte[] key = Basicfunction.stoByte(key_string);
		GeneratorKey newKey = new GeneratorKey(key);
		saes.setGenerator(newKey);
		newKey.generate();

		// 将输入的二进制字符串切分成四个字节，然后解密
		byte[][] messageASNibbles = {{ Basicfunction.btoBytes(Plain_string.substring(0, 4)), Basicfunction.btoBytes(Plain_string.substring(8, 12))},
				{ Basicfunction.btoBytes(Plain_string.substring(4, 8)), Basicfunction.btoBytes(Plain_string.substring(12, 16)) }};
		byte[][] encrypted = saes.decrypt(messageASNibbles);
		byte[] eencryptedarr = Basicfunction.ntoArray(encrypted);
		int[] Scipherarray = Basicfunction.bytoInt2(eencryptedarr);

		// 将解密后的整数数组转换为字符串
		for (int i = 0; i < Scipherarray.length; i++) {
			output += String.valueOf(Scipherarray[i]);
		}
		return output;
	}
}
