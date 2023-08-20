package com.heger.countdown.ui;

import javax.swing.*;
import java.awt.event.*;

public class NoticeJDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel message;

    // 创建一个单实例JDialog对象
    private static final NoticeJDialog instance = new NoticeJDialog();

    public static NoticeJDialog getInstance() {
        return instance;
    }

    private NoticeJDialog() {
        // 设置JDialog窗口大小250，150
        this.setSize(250, 150);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        // 居中
        this.setLocationRelativeTo(null);

        buttonOK.addActionListener(e -> onOK());
    }

    private void setMsg(String msg) {
        message.setText(msg);
    }

    public void setMsg(String msg, String title) {
        setTitle(title);
        setMsg(msg);
        this.setVisible(true);
    }

    private void onOK() {
        // add your code here
        dispose();
    }
}
