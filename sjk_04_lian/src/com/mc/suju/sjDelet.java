package com.mc.suju;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class sjDelet extends JPanel implements ActionListener {
    JTextField tfDelete;
    JButton bDelete;
    Connection con;
    Statement sql;

    public sjDelet() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mc01?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC", "root", "root");
            sql = con.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "数据库连接失败", "错误", JOptionPane.ERROR_MESSAGE);
        }

        setLayout(new BorderLayout());
        tfDelete = new JTextField(20);
        bDelete = new JButton("删除");
        bDelete.addActionListener(this);

        add(tfDelete, BorderLayout.NORTH);
        add(bDelete, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bDelete) {
            String deleteQuery = "DELETE FROM stu WHERE number = '" + tfDelete.getText().trim() + "'";
            try {
                sql.executeUpdate(deleteQuery);
                JOptionPane.showMessageDialog(this, "数据已删除!", "提示", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "删除失败: " + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}