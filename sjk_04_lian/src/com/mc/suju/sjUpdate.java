package com.mc.suju;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class sjUpdate extends JPanel implements ActionListener {
    JTextField[] tfFields;
    JButton bUpdate;
    Connection con;
    Statement sql;

    public sjUpdate() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mc01?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC", "root", "root");
            sql = con.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "数据库连接失败", "错误", JOptionPane.ERROR_MESSAGE);
        }

        setLayout(new BorderLayout());
        tfFields = new JTextField[6];
        for (int i = 0; i < tfFields.length; i++) {
            tfFields[i] = new JTextField(16);
        }

        JPanel panel = new JPanel(new GridLayout(7, 2));
        panel.add(new JLabel("学号"));
        panel.add(tfFields[0]);
        panel.add(new JLabel("姓名"));
        panel.add(tfFields[1]);
        panel.add(new JLabel("性别"));
        panel.add(tfFields[2]);
        panel.add(new JLabel("地址"));
        panel.add(tfFields[3]);
        panel.add(new JLabel("电话"));
        panel.add(tfFields[4]);
        panel.add(new JLabel("专业"));
        panel.add(tfFields[5]);

        bUpdate = new JButton("更新");
        bUpdate.addActionListener(this);

        add(panel, BorderLayout.CENTER);
        add(bUpdate, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bUpdate) {
            String updateQuery = "UPDATE stu SET name='" + tfFields[1].getText().trim() +
                    "', sex='" + tfFields[2].getText().trim() +
                    "', address='" + tfFields[3].getText().trim() +
                    "', phone='" + tfFields[4].getText().trim() +
                    "', major='" + tfFields[5].getText().trim() +
                    "' WHERE number='" + tfFields[0].getText().trim() + "'";
            try {
                sql.executeUpdate(updateQuery);
                JOptionPane.showMessageDialog(this, "数据已更新!", "提示", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "更新失败: " + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}