package com.mc.suju;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class sjQuery extends JPanel implements ActionListener {
    JTextField tfSearch;
    JButton bSearch;
    JTextArea taResult;
    Connection con;
    Statement sql;

    public sjQuery() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mc01?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC", "root", "root");
            sql = con.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "数据库连接失败", "错误", JOptionPane.ERROR_MESSAGE);
        }

        setLayout(new BorderLayout());
        tfSearch = new JTextField(20);
        bSearch = new JButton("查询");
        taResult = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(taResult);

        bSearch.addActionListener(this);

        add(tfSearch, BorderLayout.NORTH);
        add(bSearch, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bSearch) {
            String searchQuery = "SELECT * FROM stu WHERE number = '" + tfSearch.getText().trim() + "'";
            try {
                ResultSet rs = sql.executeQuery(searchQuery);
                taResult.setText(""); // 清空结果区域
                while (rs.next()) {
                    taResult.append(rs.getString("number") + " " +
                            rs.getString("name") + " " +
                            rs.getString("sex") + " " +
                            rs.getString("address") + " " +
                            rs.getString("phone") + " " +
                            rs.getString("major") + "\n");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "查询失败: " + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}