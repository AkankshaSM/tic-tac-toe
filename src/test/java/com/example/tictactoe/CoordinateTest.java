package com.example.tictactoe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {
    @Test
    void parseValidCoordinates() {
        assertEquals(new Coordinate(0, 0), Coordinate.parse("A1"));
        assertEquals(new Coordinate(0, 1), Coordinate.parse("A2"));
        assertEquals(new Coordinate(0, 2), Coordinate.parse("A3"));
        assertEquals(new Coordinate(1, 0), Coordinate.parse("B1"));
        assertEquals(new Coordinate(1, 1), Coordinate.parse("B2"));
        assertEquals(new Coordinate(1, 2), Coordinate.parse("B3"));
        assertEquals(new Coordinate(2, 0), Coordinate.parse("C1"));
        assertEquals(new Coordinate(2, 1), Coordinate.parse("C2"));
        assertEquals(new Coordinate(2, 2), Coordinate.parse("C3"));
    }

    @Test
    void parseLowercase() {
        assertEquals(new Coordinate(0, 0), Coordinate.parse("a1"));
        assertEquals(new Coordinate(2, 2), Coordinate.parse("c3"));
    }

    @Test
    void parseInvalidLength() {
        assertThrows(IllegalArgumentException.class, () -> Coordinate.parse("A"));
        assertThrows(IllegalArgumentException.class, () -> Coordinate.parse("A12"));
        assertThrows(IllegalArgumentException.class, () -> Coordinate.parse(""));
        assertThrows(IllegalArgumentException.class, () -> Coordinate.parse(null));
    }

    @Test
    void parseInvalidRow() {
        assertThrows(IllegalArgumentException.class, () -> Coordinate.parse("D1"));
        assertThrows(IllegalArgumentException.class, () -> Coordinate.parse("Z1"));
    }

    @Test
    void parseInvalidColumn() {
        assertThrows(IllegalArgumentException.class, () -> Coordinate.parse("A0"));
        assertThrows(IllegalArgumentException.class, () -> Coordinate.parse("A4"));
    }

    @Test
    void toStringFormat() {
        assertEquals("A1", new Coordinate(0, 0).toString());
        assertEquals("B2", new Coordinate(1, 1).toString());
        assertEquals("C3", new Coordinate(2, 2).toString());
    }
}