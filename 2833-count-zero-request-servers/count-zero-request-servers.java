class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {

        Arrays.sort(logs, (a, b) -> a[1] - b[1]);

        int[][] q = new int[queries.length][2];

        for (int i = 0; i < queries.length; i++) {
            q[i][0] = queries[i];
            q[i][1] = i;
        }

        Arrays.sort(q, (a, b) -> a[0] - b[0]);

        int[] ans = new int[queries.length];
        int[] count = new int[n + 1];

        int left = 0, right = 0;
        int active = 0;

        for (int[] query : q) {

            int t = query[0];
            int idx = query[1];

            // Add logs inside window
            while (right < logs.length && logs[right][1] <= t) {

                int id = logs[right][0];

                if (count[id] == 0) {
                    active++;
                }

                count[id]++;
                right++;
            }

            // Remove logs outside window
            while (left < right && logs[left][1] < t - x) {

                int id = logs[left][0];

                count[id]--;

                if (count[id] == 0) {
                    active--;
                }

                left++;
            }

            ans[idx] = n - active;
        }

        return ans;
    }
}