package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JLabel l1, l2, l3;
    JTextField tf;
    JPasswordField pf;
    JButton btn1, btn2;
    String username, password;
    boolean flag = false;
    Login() {
        setTitle("Login to application");
        setVisible(true);
        setSize(400, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        l1 = new JLabel("Login Form");
        l1.setForeground(Color.blue);
        l1.setFont(new Font("serif", Font.BOLD, 20));

        l2 = new JLabel("Username: ");
        l3 = new JLabel("Password: ");
        tf = new JTextField();
        pf = new JPasswordField();
        btn1 = new JButton("Login");
        btn2 = new JButton("Clear");

        l1.setBounds(150, 30, 400, 30);
        l2.setBounds(50, 70, 200, 30);
        l3.setBounds(50, 110, 200, 30);
        tf.setBounds(120, 70, 200, 30);
        pf.setBounds(120, 110, 200, 30);
        btn1.setBounds(50, 160, 100, 30);
        btn2.setBounds(170, 160, 100, 30);

        add(l1);
        add(l2);
        add(tf);
        add(l3);
        add(pf);
        add(btn1);
        add(btn2);

        btn1.addActionListener(this);
        btn2.addActionListener(this);
    }
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btn1) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "vaibhav@1712004");
                username = tf.getText();
                password = pf.getText();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM user WHERE username='" + username + "'AND password='" + password + "'");
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Logged in Successfully");
                    flag = true;
                    Thread.sleep(1000);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            tf.setText("");
            pf.setText("");
        }
    }
    public String getUsername() {
        return username;
    }
    public synchronized String getPassword() {
        return password;
    }
    public static void main(String[] args) {
        new Login();
    }
}