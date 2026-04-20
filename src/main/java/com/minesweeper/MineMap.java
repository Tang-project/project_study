package com.minesweeper;

import java.util.Random;

/**
 * 扫雷核心逻辑
 */
public class MineMap {
    private final int rows;
    private final int cols;
    private final int mineCount;
    private final boolean[][] isMine;
    private final int[][] count;
    public final boolean[][] opened;
    public final boolean[][] flagged;

    public MineMap(int rows, int cols, int mineCount) {
        this.rows = rows;
        this.cols = cols;
        this.mineCount = mineCount;
        isMine = new boolean[rows][cols];
        count = new int[rows][cols];
        opened = new boolean[rows][cols];
        flagged = new boolean[rows][cols];
        generateMines();
        calcNumbers();
    }

    private void generateMines() {
        Random random = new Random();
        int num = 0;
        while (num < mineCount) {
            int r = random.nextInt(rows);
            int c = random.nextInt(cols);
            if (!isMine[r][c]) {
                isMine[r][c] = true;
                num++;
            }
        }
    }

    private void calcNumbers() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (isMine[i][j]) continue;
                int cnt = 0;
                for (int dr = -1; dr <= 1; dr++) {
                    for (int dc = -1; dc <= 1; dc++) {
                        int nr = i + dr;
                        int nc = j + dc;
                        if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && isMine[nr][nc]) {
                            cnt++;
                        }
                    }
                }
                count[i][j] = cnt;
            }
        }
    }

    public boolean isMine(int r, int c) {
        return isMine[r][c];
    }

    public int getCount(int r, int c) {
        return count[r][c];
    }

    public void open(int r, int c) {
        if (opened[r][c] || flagged[r][c]) return;
        opened[r][c] = true;
        if (count[r][c] == 0) {
            for (int dr = -1; dr <= 1; dr++) {
                for (int dc = -1; dc <= 1; dc++) {
                    int nr = r + dr;
                    int nc = c + dc;
                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                        open(nr, nc);
                    }
                }
            }
        }
    }

    public boolean isWin() {
        int total = rows * cols;
        int openCnt = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (opened[i][j]) openCnt++;
            }
        }
        return openCnt == total - mineCount;
    }
}