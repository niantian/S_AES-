public class Encryptbinary {

	private GeneratorKey generator;
	
	public Encryptbinary() {
	}

	public GeneratorKey getGenerator() {
		return generator;
	}

	public void setGenerator(GeneratorKey generator) {
		this.generator = generator;
	}


	public byte[][] encrypt(byte[][] message)
	{
		byte[][] nibble = message;
		byte[][] key1 = generator.keyNibs(0,1);
		byte[][] ss1 = Applyroundkey.add(nibble, key1);
		byte[][] ss2 = SubNibbles.substitute(ss1);
		byte[][] ss3 = ShiftRows.shift(ss2);
		byte[][] ss4 = MixColumns.mix(ss3);
		byte[][] key2 = generator.keyNibs(2,3);
		byte[][] ss5 = Applyroundkey.add(ss4, key2);
		byte[][] ss6 = SubNibbles.substitute(ss5);
		byte[][] ss7 = ShiftRows.shift(ss6);
		byte[][] key3 = generator.keyNibs(4,5);
		byte[][] ss8 = Applyroundkey.add(ss7, key3);
		byte[][] cipheranswer = ss8;
		return cipheranswer;
	}

	public static String Encryd(String Plain_string, String key_string) {
		String output = new String();
		Encryptbinary saes = new Encryptbinary();
		byte[] key = Basicfunction.stoByte(key_string);
		GeneratorKey newKey = new GeneratorKey(key);
		saes.setGenerator(newKey);
		newKey.generate();
		byte[][] messageASNibbles = {{ Basicfunction.btoBytes(Plain_string.substring(0,4)), Basicfunction.btoBytes(Plain_string.substring(8,12))},
				{ Basicfunction.btoBytes(Plain_string.substring(4,8)), Basicfunction.btoBytes(Plain_string.substring(12,16)) }};
		byte[][] encrypted = saes.encrypt(messageASNibbles);
		byte[] eencryptedarr = Basicfunction.ntoArray(encrypted);
		int[] Scipherarray = Basicfunction.bytoInt2(eencryptedarr);
		for(int i=0;i<Scipherarray.length;i++) {
			output+=String.valueOf(Scipherarray[i]);
		}
		return output;


	}
}