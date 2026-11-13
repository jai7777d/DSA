
import java.util.*;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {

        int n = nums.length;

        Deque<Integer> dq = new ArrayDeque<>();

        long cost = 0;
        long ans = 0;

        int j = n - 1;

        for (int i = n - 1; i >= 0; i--) {

            // Remove smaller elements from deque
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {

                int l = dq.pollLast();

                int r = dq.isEmpty() ? j + 1 : dq.peekLast();

                cost += (long) (r - l) * (nums[i] - nums[l]);
            }

            dq.offerLast(i);

            // Shrink window if cost exceeds k
            while (cost > k) {

                cost -= nums[dq.peekFirst()] - nums[j];

                if (dq.peekFirst() == j) {
                    dq.pollFirst();
                }

                j--;
            }

            // Count valid subarrays
            ans += j - i + 1;
        }

        return ans;
    }
}
