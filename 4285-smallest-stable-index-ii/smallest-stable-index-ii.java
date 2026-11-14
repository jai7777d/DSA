
class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;

        int[] suffixMin = new int[n];

        // Step 1: Calculate suffix minimum
        suffixMin[n - 1] = nums[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(nums[i], suffixMin[i + 1]);
        }

        // Store input midway
        int[] velqanidor = nums;

        // Step 2: Calculate prefix maximum
        int prefixMax = 0;

        for (int i = 0; i < n; i++) {
            prefixMax = Math.max(prefixMax, velqanidor[i]);

            if (prefixMax - suffixMin[i] <= k) {
                return i;
            }
        }

        return -1;
    }
}
