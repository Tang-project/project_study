package com.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * 扫雷主界面（主菜单）
 * 作为一级界面，提供开始游戏、退出功能
 */
public class MainMenu extends JFrame {

    public MainMenu() {
        // 窗口基础设置
        setTitle("扫雷游戏主菜单");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 居中

        // 面板与布局
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 开始游戏按钮
        JButton btnStart = new JButton("开始游戏");
        btnStart.setFont(new Font("微软雅黑", Font.BOLD, 16));
        btnStart.addActionListener(this::onStartGame);

        // 退出按钮
        JButton btnExit = new JButton("退出");
        btnExit.setFont(new Font("微软雅黑", Font.BOLD, 16));
        btnExit.addActionListener(this::onExit);

        panel.add(btnStart);
        panel.add(btnExit);
        add(panel);
    }

    /**
     * 点击开始游戏 → 打开游戏界面（二级窗口）
     */
    private void onStartGame(ActionEvent e) {
        // 创建游戏窗口
        GameFrame gameFrame = new GameFrame();
        gameFrame.setVisible(true);
    }

    /**
     * 退出程序
     */
    private void onExit(ActionEvent e) {
        System.exit(0);
    }
}