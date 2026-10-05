package com.mc.display;
import com.mc.link.User;
import com.mc.link.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Loginframe {


    public Loginframe(UserService userService) {
    }

    public class LoginFrame extends JFrame {
        private JTextField usernameField;
        private JPasswordField passwordField;
        private JButton loginButton;
        private JButton registerButton;
        private JButton forgotPasswordButton;
        private final UserService userService;

        public LoginFrame(UserService userService) {
            this.userService = userService;
            setTitle("Login");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBackground(new Color(240, 240, 240));
            add(panel);

            Font labelFont = new Font("Arial", Font.BOLD, 14);
            Font fieldFont = new Font("Arial", Font.PLAIN, 14);
            Font buttonFont = new Font("Arial", Font.BOLD, 14);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);

            // 用户名标签
            JLabel userLabel = new JLabel("Username:");
            userLabel.setFont(labelFont);
            gbc.gridx = 0;
            gbc.gridy = 0;
            panel.add(userLabel, gbc);

            // 用户名输入框
            usernameField = new JTextField(20);
            usernameField.setFont(fieldFont);
            gbc.gridx = 1;
            gbc.gridy = 0;
            panel.add(usernameField, gbc);

            // 密码标签
            JLabel passwordLabel = new JLabel("Password:");
            passwordLabel.setFont(labelFont);
            gbc.gridx = 0;
            gbc.gridy = 1;
            panel.add(passwordLabel, gbc);

            // 密码输入框
            passwordField = new JPasswordField(20);
            passwordField.setFont(fieldFont);
            gbc.gridx = 1;
            gbc.gridy = 1;
            panel.add(passwordField, gbc);

            // 登录按钮
            loginButton = new JButton("Login");
            loginButton.setFont(buttonFont);
            loginButton.setBackground(new Color(0, 120, 215));
            loginButton.setForeground(Color.WHITE);
            loginButton.setFocusPainted(false);
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
            panel.add(loginButton, gbc);

            // 注册按钮
            registerButton = new JButton("Register");
            registerButton.setFont(buttonFont);
            registerButton.setBackground(new Color(50, 205, 50));
            registerButton.setForeground(Color.WHITE);
            registerButton.setFocusPainted(false);
            gbc.gridx = 0;
            gbc.gridy = 3;
            panel.add(registerButton, gbc);

            // 忘记密码按钮
            forgotPasswordButton = new JButton("Forgot Password");
            forgotPasswordButton.setFont(buttonFont);
            forgotPasswordButton.setBackground(new Color(255, 69, 0));
            forgotPasswordButton.setForeground(Color.WHITE);
            forgotPasswordButton.setFocusPainted(false);
            gbc.gridx = 1;
            gbc.gridy = 3;
            panel.add(forgotPasswordButton, gbc);

            // 添加事件监听器
            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());
                    User user = userService.login(username, password);
                    if (user != null) {
                        JOptionPane.showMessageDialog(null, "Login Successful!");
                        new HomeFrame(user, userService);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid username or password!");
                    }
                }
            });

            registerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new RegisterFrame(userService);
                    dispose();
                }
            });

            forgotPasswordButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ForgotPasswordFrame(userService);
                    dispose();
                }
            });

            setVisible(true);
        }
    }
}
