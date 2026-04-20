package com.minesweeper;

import javax.swing.*;

/**
 * 程序入口
 * 启动主菜单（一级界面）
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainMenu().setVisible(true);
        });
    }
}