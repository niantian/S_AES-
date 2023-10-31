import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class EncryptionDecryptionGUI extends JFrame {
    private JTextField inputTextField;
    private JTextField keyField;
    private JTextField resultField;
    private JButton encryptButton;
    private JButton decryptButton;
    private JComboBox<String> inputTypeComboBox;

    public EncryptionDecryptionGUI() {
        setTitle("加密和解密");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(3, 2));
        inputTextField = new JTextField(20);
        keyField = new JTextField(20);
        encryptButton = new JButton("加密");
        decryptButton = new JButton("解密");
        String[] inputTypes = {"ASCII", "Binary"};
        inputTypeComboBox = new JComboBox<>(inputTypes);
        inputTypeComboBox.setSelectedIndex(0); // 默认选择ASCII
        topPanel.add(new JLabel("输入文本:"));
        topPanel.add(inputTextField);
        topPanel.add(new JLabel("输入密钥:"));
        topPanel.add(keyField);
        topPanel.add(new JLabel("输入类型:"));
        topPanel.add(inputTypeComboBox);

        JPanel middlePanel = new JPanel(new FlowLayout());
        middlePanel.add(encryptButton);
        middlePanel.add(decryptButton);

        resultField = new JTextField(30);
        resultField.setEditable(false);

        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(resultField, BorderLayout.SOUTH);

        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = inputTextField.getText();
                String key = keyField.getText();
                String inputType = inputTypeComboBox.getSelectedItem().toString();

                if (inputType.equals("ASCII")) {
                    String encryptedText = performEncryption(inputText, key);
                    resultField.setText("加密后的文本: " + encryptedText);
                } else if (inputType.equals("Binary")) {

                }
            }
        });

        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = inputTextField.getText();
                String key = keyField.getText();
                String inputType = inputTypeComboBox.getSelectedItem().toString();

                if (inputType.equals("ASCII")) {
                    String decryptedText = performDecryption(inputText, key);
                    resultField.setText("解密后的文本: " + decryptedText);
                } else if (inputType.equals("Binary")) {

                }
            }
        });
    }

    private String performEncryption(String inputText, String key) {

        return inputText;
    }

    private String performDecryption(String inputText, String key) {

        return inputText;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EncryptionDecryptionGUI().setVisible(true);
            }
        });
    }
}
