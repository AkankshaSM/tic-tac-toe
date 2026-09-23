package com.example.tictactoe;

public record Coordinate(int row, int col) {
    public static Coordinate parse(String input) {
        if (input == null || input.length() != 2) {
            throw new IllegalArgumentException("Invalid coordinate format. Use A1, B2, C3, etc.");
        }
        char rowChar = Character.toUpperCase(input.charAt(0));
        char colChar = input.charAt(1);

        if (rowChar < 'A' || rowChar > 'C') {
            throw new IllegalArgumentException("Row must be A, B, or C.");
        }
        if (colChar < '1' || colChar > '3') {
            throw new IllegalArgumentException("Column must be 1, 2, or 3.");
        }

        int row = rowChar - 'A';
        int col = colChar - '1';
        return new Coordinate(row, col);
    }

    @Override
    public String toString() {
        return "" + (char)('A' + row) + (col + 1);
    }
}