public class GeneratorKey {

	private byte Keytext[];
	 
	protected short [] keyWords;
	
	
	
	static short rcon = Basicfunction.btoNum("10000000");
	static short rcon2 = Basicfunction.btoNum("00110000");
	
	public GeneratorKey(byte[] Keytext) {
		this.Keytext = Keytext;
		keyWords = new short[6];
	}

	public short rroundkey0()
	{
		short rkey0 = 0;
		String wString = "";
		for (int i = 0; i < 8; i++)
		{
			wString = wString + Keytext[i];
		}
		rkey0 = Basicfunction.btoNum(wString);
		keyWords[0] = rkey0;
		return rkey0;
	}
	
	public short rroundkey1()
	{
		short rkey1 = 0;
		String wString = "";
		for (int i = 8; i < 16; i++)
		{
			wString = wString + Keytext[i];
		}
		rkey1 = Basicfunction.btoNum(wString);
		keyWords[1] = rkey1;
		return rkey1;
	}
	
	public short rroundkey2()
	{
		int round1 = 1;
		short rkey2 = (short) (keyWords[0] ^ geng(keyWords[1], round1));
		keyWords[2] = rkey2;
		return rkey2;
	}
	
	public short rroundkey3()
	{
		short rkey3 = (short) (keyWords[2] ^ keyWords[1]);
		keyWords[3] = rkey3;
		return rkey3;
	}
	
	public short rroundkey4()
	{
		int round2 = 2;
		short rkey4 = (short) (keyWords[2] ^ geng(keyWords[3], round2));
		keyWords[4] = rkey4;
		return rkey4;
	}
	
	public short rroundkey5()
	{
		short rkey5 = (short) (keyWords[4] ^ keyWords[3]);
		keyWords[5] = rkey5;
		return rkey5;
	}
	
	public static String to8Bits(String wordString)
	{
		while (wordString.length() < 8)
		{
			wordString = "0" + wordString;
		}
		return wordString;
	}
	
	public static String to4Bits(String nibbleString)
	{
		while (nibbleString.length() < 4)
		{
			nibbleString = "0" + nibbleString;
		}
		return nibbleString;
	}
	
	public short[] split(short word)
	{
		short[] twoNibbles = new short[2];
		String word2String = to8Bits(Integer.toBinaryString(word) );
		twoNibbles[0] = Basicfunction.btoNum(word2String.substring(0, 4));
		twoNibbles[1] = Basicfunction.btoNum(word2String.substring(4));
		return twoNibbles;
	}
	
	public byte[][] keyNibs(int round0, int round1)
	{
		short[] keyNibble1 = split(keyWords[round0]);
		short[] keyNibble2 = split(keyWords[round1]);
		byte[][] key = Basicfunction.Emptynibbles();
		key[0][0] = (byte)keyNibble1[0];
		key[1][0] = (byte)keyNibble1[1];
		key[0][1] = (byte)keyNibble2[0];
		key[1][1] = (byte)keyNibble2[1];
		return key;
	}
	
	public short[] Rword(short[] wordNibbles)
	{
		short[] newNibbles = new short[2];
		newNibbles[0] = wordNibbles[1];
		newNibbles[1] = wordNibbles[0];
		return newNibbles;
	}
	
	public short subWord(short [] wordNibbles)
	{
		short newWord;
		short[] subWord = new short[2];
		subWord[0] = SubNibbles.subNib((byte)wordNibbles[0]);
		subWord[1] = SubNibbles.subNib((byte)wordNibbles[1]);
		String nibble0 = to4Bits(Integer.toBinaryString(subWord[0]));
		String nibble1 = to4Bits(Integer.toBinaryString(subWord[1]));
		newWord = (short) (Short.parseShort(nibble0 + nibble1, 2));
		return newWord;
	}

	
	public short geng(short word, int round)
	{
		int rconst = 0;
		if (round == 1)
		{
			rconst = rcon;
		}
		if (round == 2)
		{
			rconst = rcon2;
		}
		return (short)(rconst ^ subWord(Rword( split(word) ) ) );
	}
	
	public void generate()
	{
		rroundkey0();
		rroundkey1();
		rroundkey2();
		rroundkey3();
		rroundkey4();
		rroundkey5();
	}

	
}
