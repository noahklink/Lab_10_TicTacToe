import java.util.Objects;

private static final int ROWS = 3;
private static final int COLS = 3;
private static String[][] board = new String[ROWS][COLS];

void main() {
    Scanner in = new Scanner(System.in);
    String playerOne = "x";
    String playerTwo = "o";
    boolean gameDone;
    boolean gameOver;

    do { // replay loop
        int moves = 0;
        gameDone = false;
        do { // game loop
            // Player One
            boolean pOneDone = false;
            moves++;
            display();

            do {
                int row = SafeInput.getRangedInt(in, "["+moves+"] Player 1 (" + playerOne + "), what row is your move? ", 1, 3) - 1;
                int col = SafeInput.getRangedInt(in, "["+moves+"] Player 1 (" + playerOne + "), what column is your move? ", 1, 3) - 1;

                if (isValidMove(row, col)) {
                    board[row][col] = playerOne;
                    pOneDone = true;
                } else IO.println("Invalid move!");
            } while (!pOneDone);

            if (moves >= 5 && isWin(playerOne)) {
                display();
                IO.println("> Congratulations! Player 1 (" + playerOne + ") wins!");
                gameDone = true;
                break;
            } else if (isTie()) {
                display();
                gameDone = true;
                IO.println("> Tie!");
                break;
            }
            /////////////
            // Player Two
            boolean pTwoDone = false;
            moves++;
            display();

            do {
                int row = SafeInput.getRangedInt(in, "["+moves+"] Player 2 (" + playerTwo + "), what row is your move? ", 1, 3) - 1;
                int col = SafeInput.getRangedInt(in, "["+moves+"] Player 2 (" + playerTwo + "), what column is your move? ", 1, 3) - 1;

                if (isValidMove(row, col)) {
                    board[row][col] = playerTwo;
                    pTwoDone = true;
                } else IO.println("Invalid move!");
            } while (!pTwoDone);

            if (isWin(playerTwo)) {
                display();
                IO.println("\n> Congratulations! Player 2 (" + playerTwo + ") wins!");
                gameDone = true;
                break;
            } else if (isTie()) {
                display();
                IO.println("> Tie!");
                gameDone = true;
                break;
            }
        } while (!gameDone);
        // Swap x/o
        if (playerOne.equals("o")) {
            playerOne = "x";
            playerTwo = "o";
        } else {
            playerOne = "o";
            playerTwo = "x";
        }

        clearBoard();
        gameOver = !SafeInput.getYNConfirm(in, "Would you like to play again? y/n: ");
        if (!gameOver) System.out.printf("Players switched. P1: %s / P2: %s\n", playerOne, playerTwo);
    } while (!gameOver);
}

private static void clearBoard() {
    board = new String[ROWS][COLS];
}

private static void display() {
    int i = 0;
    IO.println();
    for (String[] row : board) {
        for (String col : row) {
            i++;
            if (col == null)  IO.print("| - ");
            else IO.print("| " + col + " ");
        }
        if (i < 9) IO.print("|\n-------------\n");
        else IO.print("|\n\n");
    }
}

private static boolean isValidMove(int row, int col) {
    return (row < 3 && col < 3) && board[row][col] == null;
}

private static boolean isWin(String player) {
    return isColWin(player) || isRowWin(player) || isDiagonalWin(player);
}

private static boolean isColWin(String player) {
    for (int col = 0; col < COLS; col++) {
        if (Objects.equals(board[0][col], player) &&
                Objects.equals(board[1][col], player) &&
                Objects.equals(board[2][col], player)) {
            return true;
        }
    }

    return false;
}

private static boolean isRowWin(String player) {
    for (String[] row : board) {
        if (Objects.equals(row[0], player) && Objects.equals(row[1], player) && Objects.equals(row[2], player)) return true;
    }

    return false;
}

private static boolean isDiagonalWin(String player) {
    if (Objects.equals(board[0][0], player) && Objects.equals(board[1][1], player) && Objects.equals(board[2][2], player)) return true;
    else return Objects.equals(board[0][2], player) && Objects.equals(board[1][1], player) && Objects.equals(board[2][0], player);
    /*
    x - -
    - x -
    - - x
    else
    - - x
    - x -
    x - -
     */
}

private static boolean isTie() {
    int emptyBoxes = 0;
    for (String[] row : board) {
        for (String col : row) {
            if (col == null) emptyBoxes++;
        }
    }

    return emptyBoxes == 0 && !isWin("x") && !isWin("o");
}