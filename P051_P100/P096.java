package P051_P100;

import java.util.List;
import util.Util;

/*
 * The P096.txt contains fifty different Su Doku puzzles ranging in difficulty, 
 * but all with unique solutions.
 * 
 * By solving all fifty puzzles find the sum of the 3-digit numbers found in the 
 * top left corner of each solution grid.
 */
public class P096 {

    public static int solve(List<String> lines) {             
              
        int sum = 0;
        for (int i = 1; i < lines.size(); i = i + 10) {
            int[][] sudoku = new int[9][9];
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) {
                    sudoku[j][k] = lines.get(i + j).charAt(k) - '0';
                }
            }
            solve(0, 0, sudoku);
            print(sudoku);
            sum += 100 * sudoku[0][0] + 10 * sudoku[0][1] + sudoku[0][2];
        }
        return sum;
    }

    private static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(" " + arr[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean solve(int i, int j, int[][] cells) {
        if (i == 9) {
            i = 0;
            if (++j == 9) {
                return true;
            }
        }
        if (cells[i][j] != 0) {
            return solve(i + 1, j, cells);
        }
        for (int val = 1; val <= 9; ++val) {
            if (isLegal(i, j, val, cells)) {
                cells[i][j] = val;
                if (solve(i + 1, j, cells)) {
                    return true;
                }
            }
        }
        cells[i][j] = 0;
        return false;
    }

    private static boolean isLegal(int i, int j, int val, int[][] cells) {
        for (int k = 0; k < 9; ++k) {
            if (val == cells[k][j]) {
                return false;
            }
        }
        for (int k = 0; k < 9; ++k) {
            if (val == cells[i][k]) {
                return false;
            }
        }
        for (int k = 0; k < 3; ++k) {
            for (int m = 0; m < 3; ++m) {
                if (val == cells[(i / 3) * 3 + k][(j / 3) * 3 + m]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<String> list = Util.readText("files/P096b.txt");
        System.out.println(P096.solve(list));
    }
}
