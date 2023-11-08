
public class SubNibbles {

	// 代替矩阵
	public static String[][] sBox = {
			{"1001", "0100", "1010", "1011"},
			{"1101", "0001", "1000", "0101"},
			{"0110", "0010", "0000", "0011"},
			{"1100", "1110", "1111", "0111"}
	};

	// 逆代替矩阵
	public static String[][] inverseSBox = {
			{"1010", "0101", "1001", "1011"},
			{"0001", "0111", "1000", "1111"},
			{"0110", "0000", "0010", "0011"},
			{"1100", "0100", "1101", "1110"}
	};

	// 半字节代替
	public static byte[][] substitute(byte[][] nibble) {
		byte[][] substitution = Basicfunction.Emptynibbles();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				// 获取代替矩阵中的对应值，并将其转换为字节
				String value = sBox[nibble[i][j] >> 2][nibble[i][j] & 0x03];
				byte nibbleFound = Basicfunction.btoBytes(value);
				substitution[i][j] = nibbleFound;
			}
		}
		return substitution;
	}

	// 逆半字节代替
	public static byte[][] insertSub(byte[][] nibble) {
		byte[][] substitution = Basicfunction.Emptynibbles();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				// 获取逆代替矩阵中的对应值，并将其转换为字节
				String value = inverseSBox[nibble[i][j] >> 2][nibble[i][j] & 0x03];
				byte nibbleFound = Basicfunction.btoBytes(value);
				substitution[i][j] = nibbleFound;
			}
		}
		return substitution;
	}

	public static byte subNib(byte nibble) {
		// 获取代替矩阵中的对应值，并将其转换为字节
		String value = sBox[nibble >> 2][nibble & 0x03];
		return Basicfunction.btoBytes(value);
	}

	public static byte invertSub(byte nibble) {
		// 获取逆代替矩阵中的对应值，并将其转换为字节
		String value = inverseSBox[nibble >> 2][nibble & 0x03];
		return Basicfunction.btoBytes(value);
	}
}