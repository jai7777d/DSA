class Solution {
    public long[] resultArray(int[] nums, int k) {

        long[] ans = new long[k];
        long[] dp = new long[k];

        for (int num : nums) {

            int mod = num % k;

            long[] newDp = new long[k];

            // Sirf current element wala subarray
            newDp[mod]++;

            // Purane subarrays ko current element ke saath jodo
            for (int r = 0; r < k; r++) {

                int newR = (r * mod) % k;

                newDp[newR] += dp[r];
            }

            // Answer me add karo
            for (int r = 0; r < k; r++) {
                ans[r] += newDp[r];
            }

            dp = newDp;
        }

        return ans;
    }
}