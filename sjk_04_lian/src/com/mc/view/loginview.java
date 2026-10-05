package com.mc.view;

import com.mc.link.tools;
import com.mc.suju.sjAdd;
import com.mc.suju.sjDelet;
import com.mc.suju.sjQuery;
import com.mc.suju.sjUpdate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class loginview extends JFrame implements ActionListener {
    private final int width = 520;
    private final int height = 420;
    private JMenuBar bar;
    private JMenu menu1, menu2, menu3, menu4, menu5;
    private JMenuItem item1, item2, item3, item4, item5;
    private sjAdd zengjia;
    private sjQuery chaxun;
    private sjUpdate gengxin;
    private sjDelet shanchu;

    public loginview() {
        super("学生信息管理系统");
        zengjia = new sjAdd();
        chaxun = new sjQuery();
        gengxin = new sjUpdate();
        shanchu = new sjDelet();
        bar = new JMenuBar();
        menu1 = new JMenu("信息录入");
        menu2 = new JMenu("信息查询");
        menu3 = new JMenu("信息更新");
        menu4 = new JMenu("信息删除");
        menu5 = new JMenu("退出系统");
        item1 = new JMenuItem("录入");
        item2 = new JMenuItem("查询");
        item3 = new JMenuItem("更新");
        item4 = new JMenuItem("删除");
        item5 = new JMenuItem("退出");
        menu1.add(item1);
        menu2.add(item2);
        menu3.add(item3);
        menu4.add(item4);
        menu5.add(item5);
        bar.add(menu1);
        bar.add(menu2);
        bar.add(menu3);
        bar.add(menu4);
        bar.add(menu5);
        setJMenuBar(bar);
        item1.addActionListener(this);
        item2.addActionListener(this);
        item3.addActionListener(this);
        item4.addActionListener(this);
        item5.addActionListener(this);

        initView();

        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initView() {
        JPanel jPanel = new JPanel();
        jPanel.setBounds(0, 0, width, height);
        jPanel.setLayout(null);
        add(jPanel);

        JLabel title = new JLabel("学生信息管理系统");
        title.setBounds(0, 0, width, 200);
        title.setFont(new Font("宋体", Font.BOLD, 28));
        title.setForeground(Color.CYAN);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        jPanel.add(title);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == item1) {
            this.getContentPane().removeAll();
            this.getContentPane().add(zengjia, "Center");
            this.getContentPane().repaint();
            this.getContentPane().validate();
        } else if (e.getSource() == item2) {
            this.getContentPane().removeAll();
            this.getContentPane().add(chaxun, "Center");
            this.getContentPane().repaint();
            this.getContentPane().validate();
        } else if (e.getSource() == item3) {
            this.getContentPane().removeAll();
            this.getContentPane().add(gengxin, "Center");
            this.getContentPane().repaint();
            this.getContentPane().validate();
        } else if (e.getSource() == item4) {
            this.getContentPane().removeAll();
            this.getContentPane().add(shanchu, "Center");
            this.getContentPane().repaint();
            this.getContentPane().validate();
        } else if (e.getSource() == item5) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            loginview stuM = new loginview();
            stuM.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
        });
    }
}

