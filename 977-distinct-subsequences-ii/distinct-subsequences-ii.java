class Solution {
    public int distinctSubseqII(String s) {

        long mod = 1000000007L;

        long[] dp = new long[26];

        long total = 0;

        for (int i = 0; i < s.length(); i++) {

            int c = s.charAt(i) - 'a';

            long newTotal = (2 * total + 1 - dp[c] + mod) % mod;

            dp[c] = (total + 1) % mod;

            total = newTotal;
        }

        return (int) total;
    }
}
