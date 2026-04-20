package com.minesweeper;

import javax.swing.*;

/**
 * 游戏窗口（二级界面）
 * 新增：顶部菜单栏（设置、难度、新游戏）
 */
public class GameFrame extends JFrame {

    private GamePanel gamePanel;

    public GameFrame() {
        setTitle("扫雷游戏");
        setSize(400, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // 创建游戏面板
        gamePanel = new GamePanel();
        add(gamePanel);

        // ===================== 顶部菜单 =======================
        JMenuBar menuBar = new JMenuBar();

        // 游戏菜单
        JMenu menuGame = new JMenu("游戏");
        JMenuItem itemNew = new JMenuItem("新游戏");
        JMenuItem itemExit = new JMenuItem("退出");

        menuGame.add(itemNew);
        menuGame.addSeparator();
        menuGame.add(itemExit);

        // 难度菜单
        JMenu menuLevel = new JMenu("难度");
        JMenuItem itemEasy = new JMenuItem("简单 (9×9，10雷)");
        JMenuItem itemMid = new JMenuItem("中等 (16×16，40雷)");
        JMenuItem itemHard = new JMenuItem("困难 (16×30，99雷)");

        menuLevel.add(itemEasy);
        menuLevel.add(itemMid);
        menuLevel.add(itemHard);

        menuBar.add(menuGame);
        menuBar.add(menuLevel);
        setJMenuBar(menuBar);

        // ===================== 菜单事件 =======================
        itemNew.addActionListener(e -> gamePanel.resetGame());

        itemExit.addActionListener(e -> dispose());

        itemEasy.addActionListener(e -> gamePanel.setLevel(9, 9, 10));
        itemMid.addActionListener(e -> gamePanel.setLevel(16, 16, 40));
        itemHard.addActionListener(e -> gamePanel.setLevel(16, 30, 99));
    }
}