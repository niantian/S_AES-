public class Applyroundkey {

	// 输入参数nibble0和nibble1是两个字节矩阵，表示要进行异或操作的数据
	// 返回一个新的字节矩阵，包含了两个输入矩阵按位异或的结果
	public static byte[][] add(byte[][] nibble0, byte[][] nibble1) {
		// 将输入字节矩阵转换成字节数组
		byte[] array0 = Basicfunction.ntoArray(nibble0);
		byte[] array1 = Basicfunction.ntoArray(nibble1);

		// 创建一个长度为4的字节数组来存储异或操作的结果
		byte[] Result = new byte[4];

		// 执行按位异或操作，将两个字节数组的对应元素进行异或并存储到Result数组中
		for (int i = 0; i < 4; i++) {
			Result[i] = (byte)(array0[i] ^ array1[i]);
		}

		// 将Result字节数组转换回字节矩阵的形式并返回结果
		return Basicfunction.atoNibbles(Result);
	}
}
