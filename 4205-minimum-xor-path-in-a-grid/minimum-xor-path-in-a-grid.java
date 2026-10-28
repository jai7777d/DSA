
class Solution {
    public int minCost(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        // dp[j][xor] = is this XOR possible
        // at current row, column j?
        boolean[][] dp = new boolean[n][1024];

        dp[0][grid[0][0]] = true;

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) {
                    continue;
                }

                boolean[] curr = new boolean[1024];

                // From top
                if (i > 0) {
                    for (int x = 0; x < 1024; x++) {
                        if (dp[j][x]) {
                            curr[x ^ grid[i][j]] = true;
                        }
                    }
                }

                // From left
                if (j > 0) {
                    for (int x = 0; x < 1024; x++) {
                        if (dp[j - 1][x]) {
                            curr[x ^ grid[i][j]] = true;
                        }
                    }
                }

                dp[j] = curr;
            }
        }

        // Find minimum possible XOR
        for (int x = 0; x < 1024; x++) {
            if (dp[n - 1][x]) {
                return x;
            }
        }

        return -1;
    }
}
