package com.example.tictactoe;

public enum Player {
    X('X'),
    O('O'),
    EMPTY(' ');

    private final char symbol;

    Player(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public Player other() {
        return this == X ? O : X;
    }
}