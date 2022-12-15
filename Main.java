// first option with user playing the two roles
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        //first loop to ask the user if he wants to play again
        do {
            //multidimensional array to create the matrix representing the board game
            String[][] board = {{"_ ", "_ ", "_ ", "_ "}, {"_ ", "_ ", "_ ", "_ "},
                    {"_ ", "_ ", "_ ", "_ "}, {"_ ", "_ ", "_ ", "_ "}};
            int row, column;
            row = column = 0;
            printBoard(board);
            //second loop to repeat turns until there is a winner or tie
            while (checkWinner(board, "Player 2") && checkTie(board)) {
                turns(board, row, column, "Player 1");
                printBoard(board);
                checkNextTurnWinner(board, "Player 1");
                checkNextTurnWinner1(board, "Player 1");
                //condition to break loop in case first player wins
                if (!checkWinner(board, "Player 1") || !checkTie(board)) {
                    break;
                }
                turns(board, row, column, "Player 2");
                printBoard(board);
                checkNextTurnWinner(board, "Player 2");
                checkNextTurnWinner1(board, "Player 2");
            }
        } while (nextGame());
    }

    static void printBoard(String[][] board) {
        //method used to print the board with for each loop
        for (String[] row : board) {
            for (String column : row) {
                System.out.print(column);
            }
            System.out.println();
        }
    }

    static void turns(String[][] board, int row, int column, String user) {
        //method that takes the information needed to do each turn,
        //with loop to ask a position from user while the position
        //chosen is already taken
        String symbol = checkSymbol(user);
        do {
            row = getRow(user, row);
            column = getColumn(user, column);
        } while (board[row][column].equals("X ") || board[row][column].equals("O "));
        board[row][column] = symbol;
    }

    static boolean checkWinner(String[][] board, String user) {
        //method to check if someone wins using for loop.
        //return false in case of a winner, for the game to end
        String symbol = checkSymbol(user);
        int i, j, checkRows, checkColumns;
        i = j = checkRows = checkColumns = 0;
        for (; i < board.length; i++) {
            for (; j < board.length; j++) {
                if (board[i][j].equals(symbol)) {
                    checkRows++;
                }
                if (checkRows == 4) {
                    System.out.println(user + " wins!");
                    return false;
                }
            }
            j = checkRows = 0;
        }
        for (i = 0; i < board.length; i++) {
            for (j = 0; j < board.length; j++) {
                if (board[j][i].equals(symbol)) {
                    checkColumns++;
                }
                if (checkColumns == 4) {
                    System.out.println(user + " wins!");
                    return false;
                }
            }
            checkColumns = 0;
        }
        if (board[0][0].equals(symbol) && board[1][1].equals(symbol)
                && board[2][2].equals(symbol) && board[3][3].equals(symbol)
                || board[0][3].equals(symbol) && board[1][2].equals(symbol)
                && board[2][1].equals(symbol) && board[3][0].equals(symbol)) {
            System.out.println(user + " wins");
            return false;
        }
        return true;
    }

    static String checkSymbol(String user) {
        //method to determine which symbol the program should print
        switch (user) {
            case "Player 1" -> {
                return "X ";
            }
            case "Player 2" -> {
                return "O ";
            }
        }
        return "_ ";
    }

    static int getRow(String user, int row) {
        //method to get the row from user, using try catch to prevent
        // exception in case user enter string instead of int
        Scanner scanner = new Scanner(System.in);
        boolean check = false;
        while (!check) {
            try {
                if (user.equals("Player 1")) {
                    System.out.println("Player 1, please enter row");
                    row = scanner.nextInt();
                    check = true;
                } else if (user.equals("Player 2")) {
                    System.out.println("Player 2, please enter row");
                    row = scanner.nextInt();
                    check = true;
                }
            } catch (Exception e) {
                System.out.println("string is not valid. you need to enter a number");
                scanner.next();
            }
        }
        return row;
    }

    static int getColumn(String user, int column) {
        //same as previous method, with columns
        Scanner scanner = new Scanner(System.in);
        boolean check = false;
        while (!check) {
            try {
                if (user.equals("Player 1")) {
                    System.out.println("Player 1, please enter column");
                    column = scanner.nextInt();
                    check = true;
                } else if (user.equals("Player 2")) {
                    System.out.println("Player 2, please enter column");
                    column = scanner.nextInt();
                    check = true;
                }
            } catch (Exception e) {
                System.out.println("string is not valid. you need to enter a number");
                scanner.next();
            }
        }
        return column;
    }


    static boolean checkTie(String[][] board) {
        //method to check tie by loop through board
        // and check if there is a blanc space
        int i, j;
        i = j = 0;
        for (; i < board.length; i++) {
            for (; j < board.length; j++) {
                if (board[i][j].equals("_ ")) {
                    return true;
                }
            }
            j = 0;
        }
        System.out.println("Game end in a tie");
        return false;
    }

    static void checkNextTurnWinner(String[][] board, String user) {
        //method to check almost all options to win next turn
        //using for loop
        String symbol = checkSymbol(user);
        int i, j, i1, j1, checkRows, checkColumns;
        i = j = i1 = j1 = checkRows = checkColumns = 0;
        for (; i < board.length; i++) {
            for (; j < board.length; j++) {
                if (board[i][j].equals(symbol)) {
                    checkRows++;
                } else if (board[i][j].equals("_ ")) {
                    i1 = i;
                    j1 = j;
                }
            }
            if (checkRows == 3 && board[i1][j1].equals("_ ")) {
                System.out.println(user + " can win next turn if choosing row " + i1 + " and column " + j1);
            }
            j = checkRows = 0;
        }
        for (i = 0; i < board.length; i++) {
            for (j = 0; j < board.length; j++) {
                if (board[j][i].equals(symbol)) {
                    checkColumns++;
                } else if (board[j][i].equals("_ ")) {
                    i1 = j;
                    j1 = i;
                }
            }
                if (checkColumns == 3 && board[i1][j1].equals("_ ")) {
                    System.out.println(user + " can win next turn if choosing row " + i1 + " and column " + j1);
                }
                checkColumns = 0;
            }
        }

    static void checkNextTurnWinner1(String[][] board, String user) {
        //method to check next turn win that the previous method don't check
        String symbol = checkSymbol(user);
        if (board[0][0].equals(symbol) && board[1][1].equals(symbol)
                && board[2][2].equals(symbol) && board[3][3].equals("_ ")) {
            System.out.println(user + " can win next turn if choosing row 3 and column 3");
        } else if (board[0][0].equals(symbol) && board[1][1].equals(symbol)
                && board[3][3].equals(symbol) && board[2][2].equals("_ ")) {
            System.out.println(user + " can win next turn if choosing row 2 and column 2");
        } else if (board[0][0].equals(symbol) && board[2][2].equals(symbol)
                && board[3][3].equals(symbol) && board[1][1].equals("_ ")) {
            System.out.println(user + " can win next turn if choosing row 1 and column 1");
        } else if (board[1][1].equals(symbol) && board[2][2].equals(symbol)
                && board[3][3].equals(symbol) && board[0][0].equals("_ ")) {
            System.out.println(user + " can win next turn if choosing row 0 and column 0");
        } else if (board[0][3].equals(symbol) && board[1][2].equals(symbol)
                && board[2][1].equals(symbol) && board[3][0].equals("_ ")) {
            System.out.println(user + " can win next turn if choosing row 3 and column 0");
        } else if (board[0][3].equals(symbol) && board[1][2].equals(symbol)
                && board[3][0].equals(symbol) && board[2][1].equals("_ ")) {
            System.out.println(user + " can win next turn if choosing row 2 and column 1");
        } else if (board[0][3].equals(symbol) && board[2][1].equals(symbol)
                && board[3][0].equals(symbol) && board[1][2].equals("_ ")) {
            System.out.println(user + " can win next turn if choosing row 1 and column 2");
        } else if (board[1][2].equals(symbol) && board[2][1].equals(symbol)
                && board[3][0].equals(symbol) && board[0][3].equals("_ ")) {
            System.out.println(user + " can win next turn if choosing row 0 and column 3");
        }
    }

    static boolean nextGame() {
        //method that return boolean to check if user wants to play again
        String answer;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Do you want to play again (y/n)?");
            answer = scanner.nextLine();
            if (answer.equals("y")) {
                return true;
            }
        } while (!answer.equals("n"));

        return false;
    }
}



