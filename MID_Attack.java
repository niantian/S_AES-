import java.util.HashMap;
import java.util.Map;

public class MID_Attack {
    public static String attack(String plaintext, String ciphertext) {
        int counter = 0;

        Map<String, String> encryptionCache = new HashMap<>();
        Map<String, String> decryptionCache = new HashMap<>();

        for (int i = 0; i < Math.pow(2, 16); i++) {
            String key_1 = String.format("%16s", Integer.toBinaryString(i)).replace(' ', '0');
            String mid_text_en = encryptionCache.getOrDefault(key_1, Encryptbinary.Encryd(plaintext, key_1));

            for (int j = 0; j < Math.pow(2, 16); j++) {
                String key_2 = String.format("%16s", Integer.toBinaryString(j)).replace(' ', '0');
                String mid_text_de = decryptionCache.getOrDefault(key_2, Decryptbinary.Decryd(ciphertext, key_2));

                if (mid_text_en.equals(mid_text_de)) {
                    counter++;
                    System.out.println("找到第" + counter + "个密钥：" + key_1 + key_2);
                }

                decryptionCache.put(key_2, mid_text_de); // 缓存解密结果
            }

            encryptionCache.put(key_1, mid_text_en); // 缓存加密结果
        }

        return "0";
    }

    public static void main(String[] args) {
        attack("0111011100011100", "1010101110101100");
    }
}