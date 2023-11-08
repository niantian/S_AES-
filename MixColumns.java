public class MixColumns {

	static byte[][] resultsTable = new byte[16][];

	
	static {
		byte[] results0 = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		byte[] results1 = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		byte[] results2 = {0,2,4,6,8,10,12,14,3,1,7,5,11,9,15,13};
		byte[] results3 = {0,3,6,5,12,15,10,9,11,8,13,14,7,4,1,2};
		byte[] results4 = {0,4,8,12,3,7,11,15,6,2,14,10,5,1,13,9};
		byte[] results5 = {0,5,10,15,7,2,13,8,14,11,4,1,9,12,3,6};
		byte[] results6 = {0,6,12,10,11,13,7,1,5,3,9,15,14,8,2,4};
		byte[] results7 = {0,7,14,9,15,8,1,6,13,10,3,4,2,5,12,11};
		byte[] results8 = {0,8,3,11,6,14,5,13,12,4,15,7,10,2,9,1};
		byte[] results9 = {0,9,1,8,2,11,3,10,4,13,5,12,6,15,7,14};
		byte[] results10 = {0,10,7,13,14,4,9,3,15,5,8,2,1,11,6,12};
		byte[] results11 = {0,11,5,14,10,1,15,4,7,12,2,9,13,6,8,3};
		byte[] results12 = {0,12,11,7,5,9,14,2,10,6,1,13,15,3,4,8};
		byte[] results13 = {0,13,9,4,1,12,8,5,2,15,11,6,3,14,10,7};
		byte[] results14 = {0,14,15,1,13,3,2,12,9,7,6,8,4,10,11,5};
		byte[] results15 = {0,15,13,2,9,6,4,11,1,14,12,3,8,7,5,10};
		resultsTable[0] = results0;
		resultsTable[1] = results1;
		resultsTable[2] = results2;
		resultsTable[3] = results3;
		resultsTable[4] = results4;
		resultsTable[5] = results5;
		resultsTable[6] = results6;
		resultsTable[7] = results7;
		resultsTable[8] = results8;
		resultsTable[9] = results9;
		resultsTable[10] = results10;
		resultsTable[11] = results11;
		resultsTable[12] = results12;
		resultsTable[13] = results13;
		resultsTable[14] = results14;
		resultsTable[15] = results15;
		
	}
	
	public static byte yihuo(byte a, byte b)
	{
		return (byte)(a ^ b);
	}
	
	public static byte resultsiply(byte a, byte b)
	{
		return resultsTable[a][b];
	}


	public static byte[][] mix(byte[][] nibble) 
	{
		byte[][] result = Basicfunction.Emptynibbles();
		result[0][0] = yihuo(nibble[0][0], resultsiply((byte)4, nibble[1][0]));
		result[1][0] = yihuo(resultsiply((byte)4, nibble[0][0]), nibble[1][0]);
		result[0][1] = yihuo(nibble[0][1], resultsiply((byte)4, nibble[1][1]));
		result[1][1] = yihuo(resultsiply((byte)4, nibble[0][1]), nibble[1][1]);
		return result;
	}

	public static byte[][] invertMix(byte[][] nibble)
	{
		byte[][] result = Basicfunction.Emptynibbles();
		result[0][0] = yihuo(resultsiply((byte)9, nibble[0][0]), resultsiply((byte)2, nibble[1][0]));
		result[1][0] = yihuo(resultsiply((byte)2, nibble[0][0]), resultsiply((byte)9, nibble[1][0]));
		result[0][1] = yihuo(resultsiply((byte)9, nibble[0][1]), resultsiply((byte)2, nibble[1][1]));
		result[1][1] = yihuo(resultsiply((byte)2, nibble[0][1]), resultsiply((byte)9, nibble[1][1]));
		return result;
	}

	
}
