package com.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 游戏棋盘面板
 * 支持：动态难度调整、菜单控制、结束弹窗
 */
public class GamePanel extends JPanel {
    private int rows = 9;
    private int cols = 9;
    private int mineCount = 10;

    private MineMap map;
    private JButton[][] buttons;
    private boolean gameOver;

    public GamePanel() {
        initGame();
    }

    /**
     * 初始化游戏面板与布局
     */
    private void initGame() {
        removeAll(); // 切换难度时清空原有组件
        setLayout(new GridLayout(rows, cols));
        map = new MineMap(rows, cols, mineCount);
        buttons = new JButton[rows][cols];
        gameOver = false;
        createButtons();
        revalidate();
        repaint();
    }

    /**
     * 创建所有格子按钮
     */
    private void createButtons() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JButton btn = new JButton();
                btn.setFont(new Font("Arial", Font.BOLD, 14));
                int r = i;
                int c = j;

                btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (gameOver) return;
                        if (SwingUtilities.isRightMouseButton(e)) {
                            toggleFlag(r, c);
                        } else if (SwingUtilities.isLeftMouseButton(e)) {
                            openCell(r, c);
                        }
                    }
                });
                buttons[i][j] = btn;
                add(btn);
            }
        }
    }

    /**
     * 右键插旗
     */
    private void toggleFlag(int r, int c) {
        if (map.opened[r][c]) return;
        map.flagged[r][c] = !map.flagged[r][c];
        buttons[r][c].setText(map.flagged[r][c] ? "🚩" : "");
    }

    /**
     * 左键打开格子
     */
    private void openCell(int r, int c) {
        if (map.flagged[r][c]) return;

        if (map.isMine(r, c)) {
            gameOver = true;
            showAllMines();
            showEndDialog("你踩到地雷了！游戏结束", false);
            return;
        }

        map.open(r, c);
        refresh();

        if (map.isWin()) {
            gameOver = true;
            showEndDialog("恭喜你，扫雷成功！", true);
        }
    }

    /**
     * 刷新数字显示
     */
    private void refresh() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (map.opened[i][j]) {
                    buttons[i][j].setEnabled(false);
                    int num = map.getCount(i, j);
                    buttons[i][j].setText(num == 0 ? "" : String.valueOf(num));
                }
            }
        }
    }

    /**
     * 显示所有地雷
     */
    private void showAllMines() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (map.isMine(i, j)) {
                    buttons[i][j].setText("💣");
                }
            }
        }
    }

    /**
     * 游戏结束弹窗：再来一局 / 退出
     */
    private void showEndDialog(String message, boolean isWin) {
        Object[] options = {"再来一局", "退出"};
        int choice = JOptionPane.showOptionDialog(
                this, message, isWin ? "胜利" : "游戏结束",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);

        if (choice == 0) {
            resetGame();
        } else {
            SwingUtilities.getWindowAncestor(this).dispose();
        }
    }

    // ===================== 外部调用方法 =====================
    /**
     * 重置当前难度的新游戏
     */
    public void resetGame() {
        initGame();
    }

    /**
     * 设置难度（由菜单调用）
     */
    public void setLevel(int rows, int cols, int mines) {
        this.rows = rows;
        this.cols = cols;
        this.mineCount = mines;
        initGame();
    }
}