import java.util.*;

class Solution {

    class MultiSet {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int size = 0;
        long sum = 0;

        void add(int x) {
            map.put(x, map.getOrDefault(x, 0) + 1);
            size++;
            sum += x;
        }

        void remove(int x) {
            int c = map.get(x);

            if (c == 1)
                map.remove(x);
            else
                map.put(x, c - 1);

            size--;
            sum -= x;
        }

        int first() {
            return map.firstKey();
        }

        int last() {
            return map.lastKey();
        }
    }

    MultiSet low = new MultiSet();
    MultiSet high = new MultiSet();

    void rebalance() {

        while (low.size > high.size + 1) {
            int x = low.last();
            low.remove(x);
            high.add(x);
        }

        while (low.size < high.size) {
            int x = high.first();
            high.remove(x);
            low.add(x);
        }
    }

    void insert(int x) {

        if (low.size == 0 || x <= low.last())
            low.add(x);
        else
            high.add(x);

        rebalance();
    }

    void erase(int x) {

        if (low.map.containsKey(x)) {
            low.remove(x);
        } else {
            high.remove(x);
        }

        rebalance();
    }

    long getCost(int x) {

        long median = low.last();

        return median * low.size - low.sum
             + high.sum - median * high.size;
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        long[] cost = new long[n - x + 1];

        low = new MultiSet();
        high = new MultiSet();

        // Sliding Window
        for (int i = 0; i < n; i++) {

            insert(nums[i]);

            if (i >= x) {
                erase(nums[i - x]);
            }

            if (i >= x - 1) {
                cost[i - x + 1] = getCost(x);
            }
        }

        // DP
        long INF = Long.MAX_VALUE / 4;

        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];

        for (int t = 1; t <= k; t++) {

            Arrays.fill(curr, INF);

            for (int i = n - 1; i >= 0; i--) {

                // Skip current index
                curr[i] = curr[i + 1];

                // Select window starting at i
                if (i + x <= n) {

                    curr[i] = Math.min(
                        curr[i],
                        cost[i] + prev[i + x]
                    );
                }
            }

            long[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[0];
    }
}