package com.example.tictactoe;

import java.util.Random;

public class Game {
    private final Board board;
    private Player currentPlayer;
    private final Random random;
    private GameStatus status;

    public Game() {
        this.board = new Board();
        this.currentPlayer = Player.X;
        this.random = new Random();
        this.status = GameStatus.IN_PROGRESS;
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public GameStatus getStatus() {
        return status;
    }

    public boolean makeHumanMove(String input) {
        if (status != GameStatus.IN_PROGRESS) {
            return false;
        }
        if (currentPlayer != Player.X) {
            return false;
        }

        Coordinate coord;
        try {
            coord = Coordinate.parse(input);
        } catch (IllegalArgumentException e) {
            return false;
        }

        boolean placed = board.place(coord.row(), coord.col(), Player.X);
        if (!placed) {
            return false;
        }

        updateStatus();
        if (status == GameStatus.IN_PROGRESS) {
            currentPlayer = Player.O;
        }
        return true;
    }

    public void makeComputerMove() {
        if (status != GameStatus.IN_PROGRESS) {
            return;
        }
        if (currentPlayer != Player.O) {
            return;
        }

        int[] emptyCells = new int[9];
        int count = 0;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board.get(r, c) == Player.EMPTY) {
                    emptyCells[count++] = r * 3 + c;
                }
            }
        }

        if (count > 0) {
            int idx = random.nextInt(count);
            int cell = emptyCells[idx];
            int row = cell / 3;
            int col = cell % 3;
            board.place(row, col, Player.O);
            updateStatus();
            if (status == GameStatus.IN_PROGRESS) {
                currentPlayer = Player.X;
            }
        }
    }

    private void updateStatus() {
        Player winner = board.checkWinner();
        if (winner != Player.EMPTY) {
            status = winner == Player.X ? GameStatus.X_WINS : GameStatus.O_WINS;
        } else if (board.isFull()) {
            status = GameStatus.DRAW;
        } else {
            status = GameStatus.IN_PROGRESS;
        }
    }

    public void reset() {
        board.reset();
        currentPlayer = Player.X;
        status = GameStatus.IN_PROGRESS;
    }
}