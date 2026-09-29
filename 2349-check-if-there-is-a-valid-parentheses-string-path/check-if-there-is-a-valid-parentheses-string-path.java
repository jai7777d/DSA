class Solution {
    Boolean[][][] dp;
    int m, n;

    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;

        // Total path length must be even
        if ((m + n - 1) % 2 != 0)
            return false;

        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(')
            return false;

        dp = new Boolean[m][n][m + n];

        return dfs(grid, 0, 0, 0);
    }

    boolean dfs(char[][] grid, int i, int j, int bal) {

        // Update balance
        if (grid[i][j] == '(')
            bal++;
        else
            bal--;

        // Invalid
        if (bal < 0)
            return false;

        // Destination
        if (i == m - 1 && j == n - 1)
            return bal == 0;

        // Already calculated
        if (dp[i][j][bal] != null)
            return dp[i][j][bal];

        boolean ans = false;

        // Down
        if (i + 1 < m)
            ans = dfs(grid, i + 1, j, bal);

        // Right
        if (!ans && j + 1 < n)
            ans = dfs(grid, i, j + 1, bal);

        return dp[i][j][bal] = ans;
    }
}
