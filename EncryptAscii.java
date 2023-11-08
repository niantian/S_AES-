public class EncryptAscii {

	private GeneratorKey generator;
	
	public EncryptAscii() {
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
		Basicfunction.printNibbles(cipheranswer);
		return cipheranswer;
	}
	
	public static String Encrypt_Ascii(String Plain_string, String key_string) {
		String Ciphertexts =new String();
		byte[] key = Basicfunction.stoByte(key_string);
		EncryptAscii saes = new EncryptAscii();
		GeneratorKey newKey = new GeneratorKey(key);
		saes.setGenerator(newKey);
		newKey.generate();
		for(int i=0;i<Plain_string.length();i+=2) {
			
			char ch1 = Plain_string.charAt(i);
			char ch2 = Plain_string.charAt(i+1);
			int cnum1 = (int)ch1;
			int cnum2 = (int)ch2;
			String Splaintests1 = Basicfunction.Addzero(Integer.toBinaryString(cnum1));
			String Splaintests2 = Basicfunction.Addzero(Integer.toBinaryString(cnum2));
			System.out.println(Splaintests1+Splaintests2);
			byte[][] messageASNibbles = {{ Basicfunction.btoBytes(Splaintests1.substring(0,4)), Basicfunction.btoBytes(Splaintests2.substring(0,4))},
					 { Basicfunction.btoBytes(Splaintests1.substring(4,8)), Basicfunction.btoBytes(Splaintests2.substring(4,8)) }};
			
			byte[][] encrypted = saes.encrypt(messageASNibbles);
			byte[] eencryptedarr = Basicfunction.ntoArray(encrypted);
			int[] Scipherarray = Basicfunction.bytoInt2(eencryptedarr);
			for(int k=0;k<Scipherarray.length;k++) {
				System.out.print(Scipherarray[k]);
			}
			int[] Scipherarray1 = new int[8];
			int[] Scipherarray2 = new int[8];
			for(int k=0;k<8;k++) {
				Scipherarray1[k]=Scipherarray[k];
				Scipherarray2[k]=Scipherarray[k+8];
			}
			System.out.println();
			Ciphertexts+=(char)Basicfunction.Solve(Scipherarray1);
			Ciphertexts+=(char)Basicfunction.Solve(Scipherarray2);
		}
		return Ciphertexts;
	}
}