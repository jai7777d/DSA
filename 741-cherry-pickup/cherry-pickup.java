import java.util.*;

class Solution {
    int n;
    int[][] grid;
    int[][][] dp;

    int solve(int r1, int c1, int r2) {

        int c2 = r1 + c1 - r2;

        if (r1 >= n || c1 >= n ||
            r2 >= n || c2 >= n) {
            return -10000000;
        }

        if (grid[r1][c1] == -1 ||
            grid[r2][c2] == -1) {
            return -10000000;
        }

        if (r1 == n-1 && c1 == n-1) {
            return grid[r1][c1];
        }

        // FIX: use r2, not c2
        if (dp[r1][c1][r2] != -1) {
            return dp[r1][c1][r2];
        }

        int cherries = grid[r1][c1];

        if (r1 != r2 || c1 != c2) {
            cherries += grid[r2][c2];
        }

        int a = solve(r1+1, c1, r2+1);
        int b = solve(r1+1, c1, r2);
        int c = solve(r1, c1+1, r2+1);
        int d = solve(r1, c1+1, r2);

        int best = Math.max(
            Math.max(a,b),
            Math.max(c,d)
        );

        return dp[r1][c1][r2] = cherries + best;
    }

    public int cherryPickup(int[][] grid) {

        this.grid = grid;
        n = grid.length;

        dp = new int[n][n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        return Math.max(0, solve(0,0,0));
    }
}