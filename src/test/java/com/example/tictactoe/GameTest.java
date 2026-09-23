package com.example.tictactoe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    void newGameStartsWithX() {
        Game game = new Game();
        assertEquals(Player.X, game.getCurrentPlayer());
        assertEquals(GameStatus.IN_PROGRESS, game.getStatus());
    }

    @Test
    void humanMoveValid() {
        Game game = new Game();
        assertTrue(game.makeHumanMove("A1"));
        assertEquals(Player.X, game.getBoard().get(0, 0));
        assertEquals(Player.O, game.getCurrentPlayer());
    }

    @Test
    void humanMoveInvalidFormat() {
        Game game = new Game();
        assertFalse(game.makeHumanMove("X"));
        assertFalse(game.makeHumanMove("A4"));
        assertFalse(game.makeHumanMove("D1"));
        assertFalse(game.makeHumanMove(""));
        assertFalse(game.makeHumanMove(null));
    }

    @Test
    void humanMoveOccupiedCell() {
        Game game = new Game();
        game.makeHumanMove("A1");
        game.makeComputerMove(); // O moves
        assertFalse(game.makeHumanMove("A1"));
    }

    @Test
    void humanMoveAfterGameOver() {
        Game game = new Game();
        // X wins: A1, B1, A2, B2, A3
        game.makeHumanMove("A1");
        game.makeComputerMove();
        game.makeHumanMove("A2");
        game.makeComputerMove();
        game.makeHumanMove("A3");
        assertEquals(GameStatus.X_WINS, game.getStatus());
        assertFalse(game.makeHumanMove("B1"));
    }

    @Test
    void computerMovePlacesO() {
        Game game = new Game();
        game.makeHumanMove("A1");
        game.makeComputerMove();
        Player[][] cells = game.getBoard().getCells();
        int oCount = 0;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (cells[r][c] == Player.O) oCount++;
            }
        }
        assertEquals(1, oCount);
        assertEquals(Player.X, game.getCurrentPlayer());
    }

    @Test
    void computerMoveOnlyOnEmptyCells() {
        Game game = new Game();
        // Fill all but one cell with X
        game.makeHumanMove("A1");
        game.makeComputerMove();
        game.makeHumanMove("A2");
        game.makeComputerMove();
        game.makeHumanMove("A3");
        game.makeComputerMove();
        game.makeHumanMove("B1");
        game.makeComputerMove();
        game.makeHumanMove("B2");
        game.makeComputerMove();
        game.makeHumanMove("B3");
        game.makeComputerMove();
        game.makeHumanMove("C1");
        game.makeComputerMove();
        game.makeHumanMove("C2");
        // Now only C3 is empty
        game.makeComputerMove();
        assertEquals(Player.O, game.getBoard().get(2, 2));
    }

    @Test
    void computerDoesNotMoveWhenNotItsTurn() {
        Game game = new Game();
        game.makeComputerMove(); // Should not move, it's X's turn
        assertEquals(Player.EMPTY, game.getBoard().get(0, 0));
        assertEquals(Player.X, game.getCurrentPlayer());
    }

    @Test
    void computerDoesNotMoveAfterGameOver() {
        Game game = new Game();
        game.makeHumanMove("A1");
        game.makeComputerMove();
        game.makeHumanMove("A2");
        game.makeComputerMove();
        game.makeHumanMove("A3");
        assertEquals(GameStatus.X_WINS, game.getStatus());
        game.makeComputerMove(); // Should not move
        assertEquals(GameStatus.X_WINS, game.getStatus());
    }

    @Test
    void resetRestartsGame() {
        Game game = new Game();
        game.makeHumanMove("A1");
        game.makeComputerMove();
        game.reset();
        assertEquals(Player.X, game.getCurrentPlayer());
        assertEquals(GameStatus.IN_PROGRESS, game.getStatus());
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                assertEquals(Player.EMPTY, game.getBoard().get(r, c));
            }
        }
    }

    @Test
    void drawGame() {
        Game game = new Game();
        // Sequence that leads to a draw
        game.makeHumanMove("A1"); // X
        game.makeComputerMove(); // O
        game.makeHumanMove("A2"); // X
        game.makeComputerMove(); // O
        game.makeHumanMove("B1"); // X
        game.makeComputerMove(); // O
        game.makeHumanMove("B2"); // X
        game.makeComputerMove(); // O
        game.makeHumanMove("C3"); // X
        game.makeComputerMove(); // O
        game.makeHumanMove("A3"); // X
        game.makeComputerMove(); // O
        game.makeHumanMove("C1"); // X
        game.makeComputerMove(); // O
        game.makeHumanMove("B3"); // X
        game.makeComputerMove(); // O
        game.makeHumanMove("C2"); // X
        // Game should be a draw
        assertEquals(GameStatus.DRAW, game.getStatus());
    }
}