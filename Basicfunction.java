public class Basicfunction {

	// 创建一个空的字节矩阵（nibbles），每个矩阵包含两个字节
	public static byte[][] Emptynibbles() {
		byte[][] cols = new byte[2][];
		cols[0] = new byte[2];
		cols[1] = new byte[2];
		return cols;
	}

	// 将字符串转换为字节数组
	public static byte[] stoByte(String s) {
		int[] Input = new int[s.length()];
		for (int i = 0; i < s.length(); i++) {
			Input[i] = Integer.parseInt(s.substring(i, i + 1));
		}
		byte[] output = new byte[s.length()];
		for (int i = 0; i < s.length(); i++) {
			output[i] = (byte) Input[i];
		}
		return output;
	}

	// 将字符串转换为整数数组
	public static int[] StringToArray(String s) {
		int[] Input = new int[s.length()];
		for (int i = 0; i < s.length(); i++) {
			Input[i] = Integer.parseInt(s.substring(i, i + 1));
		}
		return Input;
	}

	// 在字符串前添加零，使其达到指定长度
	public static String Addzero(String input) {
		while (true) {
			if (input.length() < 8) {
				input = '0' + input;
			} else {
				return input;
			}
		}
	}

	// 在字符串前添加零，使其达到指定长度
	public static String Addzero4(String input) {
		while (true) {
			if (input.length() < 4) {
				input = '0' + input;
			} else {
				return input;
			}
		}
	}

	// 将字符串转换为两个字节的二维数组
	public static byte[][] stoByte2(String s) {
		int[] Input = new int[s.length()];
		for (int i = 0; i < s.length(); i++) {
			Input[i] = Integer.parseInt(s.substring(i, i + 1));
		}
		byte[][] output = new byte[2][8];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 8; j++) {
				output[i][j] = (byte) Input[i * 8 + j];
			}
		}
		return output;
	}

	// 将二进制表示的整数数组转换为整数
	public static int Solve(int[] input) {
		int output = 0;
		for (int i = 0; i < input.length; i++) {
			output += input[i] * Math.pow(2, input.length - 1 - i);
		}
		return output;
	}

	// 将字节数组转换为整数数组
	public static int[] bytoInt(byte[] input) {
		String temp_string1 = new String();
		String temp_string2 = new String();
		for (int i = 0; i < input.length / 2; i++) {
			temp_string1 += Integer.toBinaryString((int) input[i]);
			temp_string2 += Integer.toBinaryString((int) input[i + 1]);
		}
		int[] output = StringToArray(temp_string1 + temp_string2);
		return output;
	}

	// 将字节数组转换为整数数组
	public static int[] bytoInt2(byte[] input) {
		String temp_string1 = new String();
		String temp_string2 = new String();
		for (int i = 0; i < input.length / 2; i++) {
			temp_string1 += Addzero4(Integer.toBinaryString((int) input[i]));
			temp_string2 += Addzero4(Integer.toBinaryString((int) input[i + 2]));
		}
		int[] output = StringToArray(temp_string1 + temp_string2);
		return output;
	}

	// 打印字节矩阵的内容
	public static void printNibbles(byte[][] nibble) {
		byte[] array = ntoArray(nibble);
		for (int i = 0; i < 2; i++) {
			System.out.print(' ');
			String output0 = Integer.toBinaryString(array[i]);
			if (output0.length() < 4) {
				for (int p = 0; p < 4 - output0.length(); p++) {
					System.out.print('0');
				}
			}
			System.out.print(output0);
			System.out.print(' ');
			String output1 = Integer.toBinaryString(array[i + 2]);
			if (output1.length() < 4) {
				for (int p = 0; p < 4 - output1.length(); p++) {
					System.out.print('0');
				}
			}
			System.out.print(output1);
			System.out.println();
		}
	}

	// 将字节矩阵转换为二进制字符串
	public static String ntoString(byte[][] nibble) {
		byte[] array = ntoArray(nibble);
		String output = new String();
		for (int i = 0; i < 2; i++) {
			String output0 = Integer.toBinaryString(array[i]);
			if (output0.length() < 4) {
				for (int p = 0; p < 4 - output0.length(); p++) {
					output += '0';
				}
			}
			output += output0;
			String output1 = Integer.toBinaryString(array[i + 2]);
			if (output1.length() < 4) {
				for (int p = 0; p < 4 - output1.length(); p++) {
					output += '0';
				}
			}
			output += output1;
		}
		return output;
	}

	// 将二进制字符串转换为短整数
	public static short btoNum(String bits) {
		try {
			return Short.parseShort(bits, 2);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return 0;
	}

	// 将字节数组转换为字节矩阵
	public static byte[][] atoNibbles(byte[] array) {
		byte[][] nibble = Emptynibbles();
		nibble[0][0] = array[0];
		nibble[1][0] = array[1];
		nibble[0][1] = array[2];
		nibble[1][1] = array[3];
		return nibble;
	}

	// 将字节矩阵转换为字节数组
	public static byte[] ntoArray(byte[][] nibble) {
		byte[] array = new byte[4];
		array[0] = nibble[0][0];
		array[1] = nibble[1][0];
		array[2] = nibble[0][1];
		array[3] = nibble[1][1];
		return array;
	}

	// 将二进制字符串转换为字节
	public static byte btoBytes(String bits) {
		try {
			return Byte.parseByte(bits, 2);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static void main(String[] args) {
		byte[] test = {11, 12, 13, 14};
		int[] output = bytoInt(test);
		for (int i = 0; i < 16; i++) {
			System.out.println(output[i]);
		}
	}
}
