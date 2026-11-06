class Solution {

    int[][] dp;

    int solve(int i, int buy, int[] prices) {

        if (i == prices.length)
            return 0;

        if (dp[i][buy] != -1)
            return dp[i][buy];

        int profit;

        if (buy == 1) {

            profit = Math.max(
                -prices[i] + solve(i+1, 0, prices),
                solve(i+1, 1, prices)
            );
        }

        else {

            profit = Math.max(
                prices[i] + solve(i+1, 1, prices),
                solve(i+1, 0, prices)
            );
        }

        return dp[i][buy] = profit;
    }

    public int maxProfit(int[] prices) {

        int n = prices.length;

        dp = new int[n][2];

        for (int[] row : dp)
            Arrays.fill(row, -1);

        return solve(0, 1, prices);
    }
}