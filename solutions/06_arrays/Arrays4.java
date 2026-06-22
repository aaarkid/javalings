// Arrays4.java
//
// Arrays of arrays: a grid.
//
//     int[][] grid = {
//         {1, 2, 3},
//         {4, 5, 6},
//     };
//     grid[1][2]        // 6  (row 1, column 2)
//     grid.length       // 2 rows
//     grid[0].length    // 3 columns
//
// Implement `rowSums`: for each row, the sum of that row.
// Implement `transpose`: rows become columns.

import javalings.Check;

public class Arrays4 {
    public static void main(String[] args) {
        int[][] grid = {
            {1, 2, 3},
            {4, 5, 6},
        };
        Check.arrayEquals(new int[]{6, 15}, rowSums(grid), "row sums");

        int[][] t = transpose(grid);
        Check.equals(3, t.length, "transposed has 3 rows");
        Check.arrayEquals(new int[]{1, 4}, t[0], "first row of transpose");
        Check.arrayEquals(new int[]{3, 6}, t[2], "last row of transpose");
    }

    static int[] rowSums(int[][] grid) {
        int[] sums = new int[grid.length];
        for (int r = 0; r < grid.length; r++) {
            for (int x : grid[r]) {
                sums[r] += x;
            }
        }
        return sums;
    }

    static int[][] transpose(int[][] grid) {
        int[][] t = new int[grid[0].length][grid.length];
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                t[c][r] = grid[r][c];
            }
        }
        return t;
    }
}
