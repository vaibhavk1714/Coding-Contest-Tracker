package org.example;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class GUI implements ActionListener{
    JFrame frame;
    private JTextField userText;
    private JPasswordField passText;
    private JLabel success;

    public static boolean isCorrect = false;

    public void showGUI(){
        frame = new JFrame();
        JPanel panel = new JPanel();

        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(10, 50, 80, 25);
        panel.add(passLabel);

        passText = new JPasswordField();
        passText.setBounds(100, 50, 165, 25);
        panel.add(passText);


        JButton button = new JButton("Login");
        button.setBounds(150, 80, 80, 25);
        button.addActionListener(new GUI());
        panel.add(button);

        success = new JLabel("");
        success.setBounds(10, 110, 300, 25);
        panel.add(success);

        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = userText.getText();
        String password = Arrays.toString(passText.getPassword());

        if(user.equals("root") && password.equals("vaibhav@1712004")) {
            success.setText("Login successful!!");
            isCorrect = true;
        }
        else {
            success.setText("Incorrect Credentials... Please try again");
        }
    }
}
