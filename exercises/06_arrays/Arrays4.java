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

// I AM NOT DONE

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
        return new int[0];
    }

    static int[][] transpose(int[][] grid) {
        return grid;
    }
}
