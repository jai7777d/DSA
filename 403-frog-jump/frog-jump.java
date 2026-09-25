class Solution {
    public boolean canCross(int[] stones) {

        int n = stones.length;

        // dp[i] = possible jumps from stone i
        HashSet<Integer>[] dp = new HashSet[n];

        for (int i = 0; i < n; i++) {
            dp[i] = new HashSet<>();
        }

        dp[0].add(0);

        for (int i = 0; i < n; i++) {

            for (int jump : dp[i]) {

                // next jump can be jump-1, jump, jump+1
                for (int next = jump - 1; next <= jump + 1; next++) {

                    if (next <= 0) continue;

                    int position = stones[i] + next;

                    // find this stone
                    for (int j = i + 1; j < n; j++) {

                        if (stones[j] == position) {
                            dp[j].add(next);
                            break;
                        }

                        if (stones[j] > position) {
                            break;
                        }
                    }
                }
            }
        }

        return !dp[n - 1].isEmpty();
    }
}