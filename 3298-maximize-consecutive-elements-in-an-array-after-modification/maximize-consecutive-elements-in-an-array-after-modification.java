class Solution {
    public int maxSelectedElements(int[] nums) {

        Arrays.sort(nums);

        HashMap<Integer, Integer> dp = new HashMap<>();

        int ans = 0;

        for (int x : nums) {

            // Option 1: Increment x to x+1
            int inc = dp.getOrDefault(x, 0) + 1;

            // Option 2: Keep x unchanged
            int keep = dp.getOrDefault(x - 1, 0) + 1;

            dp.put(x + 1, Math.max(dp.getOrDefault(x + 1, 0), inc));

            dp.put(x, Math.max(dp.getOrDefault(x, 0), keep));

            ans = Math.max(ans, Math.max(dp.get(x), dp.get(x + 1)));
        }

        return ans;
    }
}