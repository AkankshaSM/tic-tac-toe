package com.example.tictactoe;

public class Board {
    private final Player[][] cells = new Player[3][3];

    public Board() {
        reset();
    }

    public void reset() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                cells[r][c] = Player.EMPTY;
            }
        }
    }

    public boolean place(int row, int col, Player player) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
            return false;
        }
        if (cells[row][col] != Player.EMPTY) {
            return false;
        }
        cells[row][col] = player;
        return true;
    }

    public Player get(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
            return Player.EMPTY;
        }
        return cells[row][col];
    }

    public boolean isFull() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (cells[r][c] == Player.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public Player checkWinner() {
        for (int r = 0; r < 3; r++) {
            if (cells[r][0] != Player.EMPTY && cells[r][0] == cells[r][1] && cells[r][1] == cells[r][2]) {
                return cells[r][0];
            }
        }
        for (int c = 0; c < 3; c++) {
            if (cells[0][c] != Player.EMPTY && cells[0][c] == cells[1][c] && cells[1][c] == cells[2][c]) {
                return cells[0][c];
            }
        }
        if (cells[0][0] != Player.EMPTY && cells[0][0] == cells[1][1] && cells[1][1] == cells[2][2]) {
            return cells[0][0];
        }
        if (cells[0][2] != Player.EMPTY && cells[0][2] == cells[1][1] && cells[1][1] == cells[2][0]) {
            return cells[0][2];
        }
        return Player.EMPTY;
    }

    public Player[][] getCells() {
        Player[][] copy = new Player[3][3];
        for (int r = 0; r < 3; r++) {
            System.arraycopy(cells[r], 0, copy[r], 0, 3);
        }
        return copy;
    }
}