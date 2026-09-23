package com.example.tictactoe;

import java.util.Scanner;

public class ConsoleUI {
    private final Game game;
    private final Scanner scanner;

    public ConsoleUI(Game game, Scanner scanner) {
        this.game = game;
        this.scanner = scanner;
    }

    public void run() {
        printWelcome();
        while (true) {
            printBoard();
            GameStatus status = game.getStatus();
            if (status != GameStatus.IN_PROGRESS) {
                printResult(status);
                if (!promptPlayAgain()) {
                    break;
                }
                game.reset();
                continue;
            }

            if (game.getCurrentPlayer() == Player.X) {
                promptHumanMove();
            } else {
                System.out.println("Computer (O) is thinking...");
                game.makeComputerMove();
            }
        }
        System.out.println("Thanks for playing!");
    }

    private void printWelcome() {
        System.out.println("=== Tic-Tac-Toe ===");
        System.out.println("You are X. Computer is O.");
        System.out.println("Enter coordinates like A1, B2, C3 (letters A-C for rows, 1-3 for columns).");
        System.out.println("Type 'quit' to exit.");
        System.out.println();
    }

    private void printBoard() {
        Board board = game.getBoard();
        System.out.println("   1 2 3");
        for (int r = 0; r < 3; r++) {
            System.out.print((char)('A' + r) + "  ");
            for (int c = 0; c < 3; c++) {
                char symbol = board.get(r, c).getSymbol();
                System.out.print(symbol);
                if (c < 2) System.out.print("|");
            }
            System.out.println();
            if (r < 2) System.out.println("  ---+---+---");
        }
        System.out.println();
    }

    private void promptHumanMove() {
        while (true) {
            System.out.print("Your move (X): ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("quit")) {
                System.out.println("Quitting game.");
                System.exit(0);
            }
            if (game.makeHumanMove(input)) {
                break;
            } else {
                System.out.println("Invalid move. Use format A1-C3 for empty cells.");
            }
        }
    }

    private void printResult(GameStatus status) {
        switch (status) {
            case X_WINS -> System.out.println("You win!");
            case O_WINS -> System.out.println("Computer wins!");
            case DRAW -> System.out.println("It's a draw!");
            default -> {}
        }
    }

    private boolean promptPlayAgain() {
        System.out.print("Play again? (y/n): ");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("y") || input.equals("yes");
    }
}