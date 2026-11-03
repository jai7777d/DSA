class Solution {

    int[][][] dp;
    int m, n;

    public int cherryPickup(int[][] grid) {

        m = grid.length;
        n = grid[0].length;

        dp = new int[m][n][n];

        for (int[][] a : dp) {
            for (int[] b : a) {
                Arrays.fill(b, -1);
            }
        }

        return solve(grid, 0, 0, n - 1);
    }

    public int solve(int[][] grid, int row, int c1, int c2) {

        if (c1 < 0 || c2 < 0 || c1 >= n || c2 >= n) {
            return 0;
        }

        if (row == m) {
            return 0;
        }

        if (dp[row][c1][c2] != -1) {
            return dp[row][c1][c2];
        }

        int cherries = grid[row][c1];

        if (c1 != c2) {
            cherries += grid[row][c2];
        }

        int max = 0;

        for (int d1 = -1; d1 <= 1; d1++) {

            for (int d2 = -1; d2 <= 1; d2++) {

                max = Math.max(max,
                    solve(grid, row + 1, c1 + d1, c2 + d2));
            }
        }

        return dp[row][c1][c2] = cherries + max;
    }
}