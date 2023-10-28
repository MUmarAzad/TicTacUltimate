import java.util.Scanner;
import java.util.Random;

public class TTTSem1 {
    static char[][] board = {
            {'1', '2', '3'},
            {'4', '5', '6'},
            {'7', '8', '9'}
    };

    static char user = 'X';
    static char computer = 'O';

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char currentPlayer = user;

        int moveCount = 0;
        boolean gameWon = false;

        displayBoard();

        while (true) {
            if (currentPlayer == user) {
                System.out.println("User's turn (X):");
                int userMove = getUserMove(input);
                makeMove(userMove, user);
                moveCount++;
                displayBoard();
                gameWon = checkWin(user);
            } else {
                System.out.println("Computer's turn (O):");
                int computerMove = getComputerMove();
                makeMove(computerMove, computer);
                moveCount++;
                displayBoard();
                gameWon = checkWin(computer);
            }

            if (gameWon) {
                if (currentPlayer == user) {
                    System.out.println("User (X) wins!");
                } else {
                    System.out.println("Computer (O) wins!");
                }
                break;
            }

            if (moveCount == 9) {
                System.out.println("It's a tie!");
                break;
            }

            currentPlayer = (currentPlayer == user) ? computer : user;
        }

        input.close();
    }

    static int getUserMove(Scanner input) {
        int userMove;
        while (true) {
            System.out.print("Enter your move (1-9): ");
            userMove = input.nextInt();
            if (isValidMove(userMove)) {
                return userMove;
            } else {
                System.out.println("Invalid move. Please try again.");
            }
        }
    }

    static boolean isValidMove(int move) {
        return move >= 1 && move <= 9 && board[(move - 1) / 3][(move - 1) % 3] == (char) (move + '0');
    }

    static void makeMove(int move, char player) {
        board[(move - 1) / 3][(move - 1) % 3] = player;
    }

    static boolean checkWin(char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }

    static void displayBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("---------");
            }
        }
    }

    static int getComputerMove() {
        Random rand = new Random();
        int move;
        do {
            move = rand.nextInt(9) + 1;
        } while (!isValidMove(move));
        return move;
    }
}
