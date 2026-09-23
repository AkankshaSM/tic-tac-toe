package com.example.tictactoe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    @Test
    void newBoardIsEmpty() {
        Board board = new Board();
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                assertEquals(Player.EMPTY, board.get(r, c));
            }
        }
    }

    @Test
    void placeAndGet() {
        Board board = new Board();
        assertTrue(board.place(0, 0, Player.X));
        assertEquals(Player.X, board.get(0, 0));
    }

    @Test
    void placeOutOfBoundsReturnsFalse() {
        Board board = new Board();
        assertFalse(board.place(-1, 0, Player.X));
        assertFalse(board.place(3, 0, Player.X));
        assertFalse(board.place(0, -1, Player.X));
        assertFalse(board.place(0, 3, Player.X));
    }

    @Test
    void placeOnOccupiedReturnsFalse() {
        Board board = new Board();
        board.place(1, 1, Player.X);
        assertFalse(board.place(1, 1, Player.O));
        assertEquals(Player.X, board.get(1, 1));
    }

    @Test
    void rowWin() {
        Board board = new Board();
        board.place(0, 0, Player.X);
        board.place(0, 1, Player.X);
        board.place(0, 2, Player.X);
        assertEquals(Player.X, board.checkWinner());
    }

    @Test
    void columnWin() {
        Board board = new Board();
        board.place(0, 1, Player.O);
        board.place(1, 1, Player.O);
        board.place(2, 1, Player.O);
        assertEquals(Player.O, board.checkWinner());
    }

    @Test
    void diagonalWinTopLeft() {
        Board board = new Board();
        board.place(0, 0, Player.X);
        board.place(1, 1, Player.X);
        board.place(2, 2, Player.X);
        assertEquals(Player.X, board.checkWinner());
    }

    @Test
    void diagonalWinTopRight() {
        Board board = new Board();
        board.place(0, 2, Player.O);
        board.place(1, 1, Player.O);
        board.place(2, 0, Player.O);
        assertEquals(Player.O, board.checkWinner());
    }

    @Test
    void noWinnerEmptyBoard() {
        Board board = new Board();
        assertEquals(Player.EMPTY, board.checkWinner());
    }

    @Test
    void noWinnerPartialBoard() {
        Board board = new Board();
        board.place(0, 0, Player.X);
        board.place(1, 1, Player.O);
        board.place(0, 1, Player.X);
        assertEquals(Player.EMPTY, board.checkWinner());
    }

    @Test
    void isFullWhenFull() {
        Board board = new Board();
        Player p = Player.X;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board.place(r, c, p);
                p = p.other();
            }
        }
        assertTrue(board.isFull());
    }

    @Test
    void isNotFullWhenEmpty() {
        Board board = new Board();
        assertFalse(board.isFull());
    }

    @Test
    void resetClearsBoard() {
        Board board = new Board();
        board.place(0, 0, Player.X);
        board.place(1, 1, Player.O);
        board.reset();
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                assertEquals(Player.EMPTY, board.get(r, c));
            }
        }
    }
}