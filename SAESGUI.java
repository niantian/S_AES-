import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class SAESGUI extends JFrame {

    private JTextField encrypt_text;
    private JTextField decrypt_text;
    private JTextField encrypt_key_text;
    private JTextField decrypt_key_text;

    private JButton encrypt_button;
    private JButton decrypt_button;
    private JTextArea showtext;

    private JRadioButton binary_button;
    private JRadioButton ascii_button;
    private JComboBox<String> encryptionCountComboBox;
    private Box box1, box2, box3, box4, box5, boxV;

    public SAESGUI() {
        setTitle("S_AES");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new FlowLayout());

        boxV = Box.createVerticalBox();
        box1 = Box.createHorizontalBox();
        box2 = Box.createHorizontalBox();
        box3 = Box.createHorizontalBox();
        box4 = Box.createHorizontalBox();
        box5 = Box.createHorizontalBox();

        JLabel title = new JLabel("S_AES");
        title.setFont(new Font("微软雅黑", Font.BOLD, 24));
        box1.add(title);

        box2.add(new JLabel("明文:"));
        encrypt_text = new JTextField(30);
        box2.add(encrypt_text);
        box2.add(new JLabel("密钥:"));
        encrypt_key_text = new JTextField(20);
        box2.add(encrypt_key_text);
        encrypt_button = new JButton("加密");
        encrypt_button.setFont(new Font("微软雅黑", Font.BOLD, 12));

        String[] encryptionCounts = {"一重", "二重", "三重"};
        encryptionCountComboBox = new JComboBox<>(encryptionCounts);
        box2.add(encryptionCountComboBox);
        box2.add(encrypt_button);

        encrypt_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (binary_button.isSelected()) {
                    System.out.println("二进制加密");
                    String info = new String();
                    String key = new String();
                    boolean flag = true;

                    
                    Pattern pattern = Pattern.compile("[0-1]*");
                    if (!pattern.matcher(encrypt_text.getText()).matches() || encrypt_text.getText().length() != 16) {
                        JOptionPane.showMessageDialog(null, "请输入16bit二进制加密信息！", "警告", JOptionPane.WARNING_MESSAGE);
                        flag = false;
                    } else {
                        info = encrypt_text.getText();
                    }

                    if (!pattern.matcher(encrypt_key_text.getText()).matches() || encrypt_key_text.getText().length() % 16 != 0) {
                        JOptionPane.showMessageDialog(null, "请输入16的整数倍bit的二进制密钥！", "警告", JOptionPane.WARNING_MESSAGE);
                        flag = false;
                    } else {
                        key = encrypt_key_text.getText();
                    }

                    if (flag == true) {
                       

                        int encryptionCount = encryptionCountComboBox.getSelectedIndex() + 1; 

                        String ciphertext = info;
                        for (int i = 0; i < encryptionCount; i++) {
                            System.out.println("第" + (i + 1) + "重加密");
                            ciphertext = Encryptbinary.Encryd(ciphertext, key);
                            info = ciphertext;
                            System.out.println("Message encrypted: ");
                            System.out.println(ciphertext);
                        }

                        if (showtext.getText().length() != 0) {
                            showtext.append("密钥: " + key + "\n密文: " + ciphertext + "\n\n");
                        } else {
                            showtext.setText("密钥: " + key + "\n密文: " + ciphertext + "\n\n");
                        }

                    } else {
                        System.out.println("未加密");
                    }
                } else if (ascii_button.isSelected()) {
                    System.out.println("ascii加密");
                    String info = new String();
                    String key = new String();
                    boolean flag = true;

                
                    Pattern pattern_01 = Pattern.compile("[0-1]*");
                    Pattern pattern_ascii = Pattern.compile("\\A\\p{ASCII}*\\z");
                    if (!pattern_ascii.matcher(encrypt_text.getText()).matches() || encrypt_text.getText().length() == 0) {
                        JOptionPane.showMessageDialog(null, "请输入ASCII加密信息！", "警告", JOptionPane.WARNING_MESSAGE);
                        flag = false;
                    } else {
                        info = encrypt_text.getText();
                        if (info.length() % 2 != 0) {
                            info = encrypt_text.getText();
                            info = info + " ";
                            System.out.println(info);
                        }
                    }

                    if (!pattern_01.matcher(encrypt_key_text.getText()).matches() || encrypt_key_text.getText().length() % 16 != 0) {
                        JOptionPane.showMessageDialog(null, "请输入16的整数倍bit的二进制密钥！", "警告", JOptionPane.WARNING_MESSAGE);
                        flag = false;
                    } else {
                        key = encrypt_key_text.getText();
                    }

                    if (flag == true) {
                      

                        int encryptionCount = encryptionCountComboBox.getSelectedIndex() + 1; 

                        String ciphertext = info;
                        for (int i = 0; i < encryptionCount; i++) {
                            System.out.println("第" + (i + 1) + "重加密");
                            ciphertext = EncryptAscii.Encrypt_Ascii(ciphertext, key);
                            info = ciphertext;
                            System.out.println("Message encrypted: ");
                            System.out.println(ciphertext);
                        }

                        if (showtext.getText().length() != 0) {
                            showtext.append("密钥: " + key + "\n密文: " + ciphertext + "\n\n");
                        } else {
                            showtext.setText("密钥: " + key + "\n密文: " + ciphertext + "\n\n");
                        }
                    } else {
                        System.out.println("未加密");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "请先选择输入格式", "执行结果", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        box3.add(new JLabel("密文:"));
        decrypt_text = new JTextField(30);
        box3.add(decrypt_text);
        box3.add(new JLabel("密钥:"));
        decrypt_key_text = new JTextField(20);
        box3.add(decrypt_key_text);
        decrypt_button = new JButton("解密");
        decrypt_button.setFont(new Font("微软雅黑", Font.BOLD, 12));

        box3.add(decrypt_button);

        decrypt_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (binary_button.isSelected()) {
                    System.out.println("二进制解密");
                    String info = new String();
                    String key = new String();
                    boolean flag = true;

                   
                    Pattern pattern = Pattern.compile("[0-1]*");
                    if (!pattern.matcher(decrypt_text.getText()).matches() || decrypt_text.getText().length() != 16) {
                        JOptionPane.showMessageDialog(null, "请输入16bit二进制解密信息！", "警告", JOptionPane.WARNING_MESSAGE);
                        flag = false;
                    } else {
                        info = decrypt_text.getText();
                    }

                    if (!pattern.matcher(decrypt_key_text.getText()).matches() || decrypt_key_text.getText().length() % 16 != 0) {
                        JOptionPane.showMessageDialog(null, "请输入16的整数倍bit的二进制密钥！", "警告", JOptionPane.WARNING_MESSAGE);
                        flag = false;
                    } else {
                        key = decrypt_key_text.getText();
                    }

                    if (flag == true) {
                     

                        int decryptionCount = encryptionCountComboBox.getSelectedIndex() + 1; // 

                        String plaintext = info;
                        for (int i = 0; i < decryptionCount; i++) {
                            System.out.println("第" + (i + 1) + "重解密");
                            plaintext = Decryptbinary.Decryd(plaintext, key);
                            info = plaintext;
                            System.out.println("Message decrypted: ");
                            System.out.println(plaintext);
                        }

                        if (showtext.getText().length() != 0) {
                            showtext.append("密钥: " + key + "\n明文: " + plaintext + "\n\n");
                        } else {
                            showtext.setText("密钥: " + key + "\n明文: " + plaintext + "\n\n");
                        }
                    } else {
                        System.out.println("未解密");
                    }
                } else if (ascii_button.isSelected()) {
                    System.out.println("ascii解密");
                    String info = new String();
                    String key = new String();
                    boolean flag = true;

                    
                    Pattern pattern_01 = Pattern.compile("[0-1]*");
                    
                    info = decrypt_text.getText();
                    if (info.length() % 2 != 0) {
                        info = decrypt_text.getText();
                        info = info + " ";
                        System.out.println(info);
                    }

                    if (!pattern_01.matcher(decrypt_key_text.getText()).matches() || decrypt_key_text.getText().length() % 16 != 0) {
                        JOptionPane.showMessageDialog(null, "请输入16的整数倍bit的二进制密钥！", "警告", JOptionPane.WARNING_MESSAGE);
                        flag = false;
                    } else {
                        key = decrypt_key_text.getText();
                    }

                    if (flag == true) {
                    

                        int decryptionCount = encryptionCountComboBox.getSelectedIndex() + 1; 

                        String plaintext = info;
                        for (int i = 0; i < decryptionCount; i++) {
                            System.out.println("第" + (i + 1) + "重解密");
                            plaintext = DecryptAscii.Decrypt_Ascii(plaintext, key);
                            info = plaintext;
                            System.out.println("Message decrypted: ");
                            System.out.println(plaintext);
                        }

                        if (showtext.getText().length() != 0) {
                            showtext.append("密钥: " + key + "\n明文: " + plaintext + "\n\n");
                        } else {
                            showtext.setText("密钥: " + key + "\n明文: " + plaintext + "\n\n");
                        }
                    } else {
                        System.out.println("未解密");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "请先选择输入格式", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        showtext = new JTextArea(12, 40);
        showtext.setEditable(false);
        box4.add(new JScrollPane(showtext));

        binary_button = new JRadioButton("二进制");
        ascii_button = new JRadioButton("ASCII");
        ButtonGroup group = new ButtonGroup();
        group.add(binary_button);
        group.add(ascii_button);
        
        box5.add(binary_button);
        box5.add(ascii_button);

        boxV.add(box1);
        boxV.add(Box.createVerticalStrut(20));
        boxV.add(box2);
        boxV.add(box3);
        boxV.add(box5);
        boxV.add(Box.createVerticalStrut(10));
        boxV.add(box4);

        add(boxV);
    }

    public static void main(String[] args) {
        SAESGUI view = new SAESGUI();
        view.setVisible(true);
        view.setLocationRelativeTo(null);
    }
}
