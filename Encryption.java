import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Encryption {
    public static String[][] sBox = {{"1001", "0100", "1010", "1011"}, {"1101", "0001", "1000", "0101"}, {"0110", "0010", "0000", "0011"}, {"1100", "1110", "1111", "0111"}};
    public static String[][] inverseSBox = {{"1010", "0101", "1001", "1011"}, {"0001", "0111", "1000", "1111"}, {"0110", "0000", "0010", "0011"}, {"1100", "0100", "1101", "1110"}};
    public static int length = 16, blockSize = 4;

    public static void main(String[] args) throws IOException {
        int i = 0, j = 0, x = 0, k = 0, ind = 0, rotateLength = 0, inverseRotateLength = 0;
        String readData = null;
        x = (int) Math.sqrt(length / blockSize);
        char[][][] plainText = new char[x][x][blockSize], cipherText = new char[x][x][blockSize], key0 = new char[x][x][blockSize], key1 = new char[x][x][blockSize], key2 = new char[x][x][blockSize];
        int[] sBoxIndices = new int[2];
        char[] temp;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the plaintext-");
        readData = reader.readLine();
        for (i = 0; i < x; ++i)
            for (j = 0; j < x; ++j)
                for (k = 0; k < blockSize; ++k)
                    plainText[j][i][k] = readData.charAt(ind++);
        ind = 0;
        System.out.println("Enter the key-");
        readData = reader.readLine();
        for (i = 0; i < x; ++i)
            for (j = 0; j < x; ++j)
                for (k = 0; k < blockSize; ++k)
                    key0[j][i][k] = readData.charAt(ind++);
        ind = 0;

        key1 = generateNewKey(key0, new char[][]{{'1', '0', '0', '0'}, {'0', '0', '0', '0'}});
        key2 = generateNewKey(key1, new char[][]{{'0', '0', '1', '1'}, {'0', '0', '0', '0'}});

        cipherText = performXor(plainText, key0);

        for (i = 0; i < x; ++i) {
            for (j = 0; j < x; ++j) {
                sBoxIndices = getSBoxIndices(cipherText[i][j]);
                cipherText[i][j] = sBox[sBoxIndices[0]][sBoxIndices[1]].toCharArray();
            }
        }

        for (k = 1; k < x; ++k) {
            ++rotateLength;
            inverseRotateLength = x - rotateLength;
            for (i = 0, j = inverseRotateLength - 1; i < j; ++i, --j) {
                temp = cipherText[k][i];
                cipherText[k][i] = cipherText[k][j];
                cipherText[k][j] = temp;
            }

            for (i = inverseRotateLength, j = x - 1; i < j; ++i, --j) {
                temp = cipherText[k][i];
                cipherText[k][i] = cipherText[k][j];
                cipherText[k][j] = temp;
            }

            for (i = 0, j = x - 1; i < j; ++i, --j) {
                temp = cipherText[k][i];
                cipherText[k][i] = cipherText[k][j];
                cipherText[k][j] = temp;
            }
        }
        rotateLength = 0;

        for (i = 0; i < x; ++i) {
            for (j = 0; j < x; ++j) {
                sBoxIndices = getSBoxIndices(cipherText[i][j]);
                cipherText[i][j] = sBox[sBoxIndices[0]][sBoxIndices[1]].toCharArray();
            }
        }

        plainText = performXor(cipherText, key1);

        for (k = 1; k < x; ++k) {
            ++rotateLength;
            inverseRotateLength = x - rotateLength;
            for (i = 0, j = inverseRotateLength - 1; i < j; ++i, --j) {
                temp = plainText[k][i];
                plainText[k][i] = plainText[k][j];
                plainText[k][j] = temp;
            }

            for (i = inverseRotateLength, j = x - 1; i < j; ++i, --j) {
                temp = plainText[k][i];
                plainText[k][i] = plainText[k][j];
                plainText[k][j] = temp;
            }

            for (i = 0, j = x - 1; i < j; ++i, --j) {
                temp = plainText[k][i];
                plainText[k][i] = plainText[k][j];
                plainText[k][j] = temp;
            }
        }

        for (i = 0; i < x; ++i) {
            for (j = 0; j < x; ++j) {
                sBoxIndices = getSBoxIndices(plainText[i][j]);
                plainText[i][j] = sBox[sBoxIndices[0]][sBoxIndices[1]].toCharArray();
            }
        }

        plainText = performXor(plainText, key0);

        System.out.println("------------------------------------------------------------------");
        System.out.println("                   Cipher Text (After Encryption)");
        System.out.println("------------------------------------------------------------------");
        for (i = 0; i < x; ++i) {
            for (j = 0; j < x; ++j) {
                for (k = 0; k < blockSize; ++k)
                    System.out.print(cipherText[j][i][k]);
                System.out.print(" ");
            }
            System.out.print(" ");
        }
        System.out.println();
        System.out.println();
    }

    public static char[][][] generateNewKey(char[][][] key, char[][] roundKey) {
        int x = (int) Math.sqrt(length / blockSize), i = 0, j = 0;
        char[][][] newKey = new char[x][x][blockSize];
        char[][] w0 = new char[x][blockSize];
        char[][] w1 = new char[x][blockSize];
        char[][] w2 = new char[x][blockSize];
        char[][] w3 = new char[x][blockSize];

        for (i = 0; i < x; ++i)
            for (j = 0; j < blockSize; ++j)
                w0[i][j] = key[i][0][j];

        for (i = 0; i < x; ++i)
            for (j = 0; j < blockSize; ++j)
                w1[i][j] = key[i][1][j];

        int r1 = 0, r2 = 1;
        for (i = 0; i < blockSize; ++i)
            w2[r1][i] = key[r2][1][i];
        r1 = 1;
        r2 = 0;
        for (i = 0; i < blockSize; ++i)
            w2[r1][i] = key[r2][1][i];

        int[] indices;
        for (i = 0; i < x; ++i) {
            indices = getSBoxIndices(w2[i]);
            w2[i] = sBox[indices[0]][indices[1]].toCharArray();
        }

        w2 = performXor(w2, roundKey);

        w2 = performXor(w0, w2);
        for (i = 0; i < x; ++i)
            for (j = 0; j < blockSize; ++j)
                newKey[i][0][j] = w2[i][j];

        w3 = performXor(w1, w2);

        for (i = 0; i < x; ++i)
            for (j = 0; j < blockSize; ++j)
                newKey[i][1][j] = w3[i][j];

        return newKey;
    }

    public static int[] getSBoxIndices(char[] block) {
        int[] indices = new int[2];
        if (block[0] == '0' && block[1] == '0') indices[0] = 0;
        else if (block[0] == '0' && block[1] == '1') indices[0] = 1;
        else if (block[0] == '1' && block[1] == '0') indices[0] = 2;
        else if (block[0] == '1' && block[1] == '1') indices[0] = 3;
        if (block[2] == '0' && block[3] == '0') indices[1] = 0;
        else if (block[2] == '0' && block[3] == '1') indices[1] = 1;
        else if (block[2] == '1' && block[3] == '0') indices[1] = 2;
        else if (block[2] == '1' && block[3] == '1') indices[1] = 3;
        return indices;
    }

    public static char performXor(char ch1, char ch2) {
        if (ch1 == ch2) return '0';
        return '1';
    }

    public static char performXor(char ch1, char ch2, char ch3) {
        if (ch1 == ch2) {
            if (ch2 == ch3) return ch1;
            else return ch3;
        }
        return ch3 == '0' ? '1' : '0';
    }

    public static char[][] performXor(char[][] data1, char[][] data2) {
        char[][] result = new char[data1.length][data1[0].length];
        int i = 0, j = 0;
        for (i = 0; i < data1.length; ++i) {
            for (j = 0; j < data1[0].length; ++j) {
                if (data1[i][j] == data2[i][j]) result[i][j] = '0';
                else result[i][j] = '1';
            }
        }
        return result;
    }

    public static char[][][] performXor(char[][][] data1, char[][][] data2) {
        char[][][] result = new char[data1.length][data1[0].length][data1[0][0].length];
        int i = 0, j = 0, k = 0;
        for (i = 0; i < data1.length; ++i) {
            for (j = 0; j < data1[0].length; ++j) {
                for (k = 0; k < data1[0][0].length; ++k) {
                    if (data1[i][j][k] == data2[i][j][k]) result[i][j][k] = '0';
                    else result[i][j][k] = '1';
                }
            }
        }
        return result;
    }
}
