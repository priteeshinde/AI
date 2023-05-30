import java.util.Arrays;
import java.util.Scanner;

public class N_Queen {

    private static int totalSolutions = 0;

    public static void solveNQueens(int n) {
        int[][] board = new int[n][n];
        backtrack(board, 0, n);
        System.out.println("Total solutions: " + totalSolutions);
    }

    private static void backtrack(int[][] board, int col, int n) {
        if (col == n) {
            // All queens are placed, found a solution
            totalSolutions++;
            printBoard(board);
            return;
        }

        for (int row = 0; row < n; row++) {
            if (isSafe(board, row, col, n)) {
                board[row][col] = 1; // Place the queen
                backtrack(board, col + 1, n);
                board[row][col] = 0; // Remove the queen (backtrack)
            }
        }
    }

    private static boolean isSafe(int[][] board, int row, int col, int n) {
        // Check if the current position is safe for a queen

        // Check the left side of the current row
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }

        // Check the upper diagonal on the left side
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // Check the lower diagonal on the left side
        for (int i = row, j = col; i < n && j >= 0; i++, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    private static void printBoard(int[][] board) {
        int n = board.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Enter the number of queen");
        int n = sc.nextInt(); // Change the value of n for different board sizes
        solveNQueens(n);
    }
}