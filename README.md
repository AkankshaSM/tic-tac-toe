# Tic-Tac-Toe

Java command-line Tic-Tac-Toe game with human vs computer.

## Requirements
- Java 21
- Maven

## Build
```bash
mvn clean package
```
This produces an executable JAR at `target/tic-tac-toe-1.0.0.jar`.

## Run Tests
```bash
mvn test
```

## Play
```bash
java -jar target/tic-tac-toe-1.0.0.jar
```

## How to Play
- You are **X**, the computer is **O**.
- Enter coordinates like `A1`, `B2`, `C3` (letters A-C for rows, 1-3 for columns).
- Type `quit` to exit at any time.
- After a game ends, you can choose to play again.

## Game Rules
- First to get three in a row (horizontal, vertical, or diagonal) wins.
- If the board fills with no winner, it's a draw.
- Invalid input is rejected with a message.
- Occupied cells are rejected.
- Computer plays randomly on empty cells.