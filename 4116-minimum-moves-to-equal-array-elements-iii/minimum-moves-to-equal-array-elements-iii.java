class Solution {
public int minMoves(int[] nums) {
int max = Arrays.stream(nums).max().getAsInt();

        int ans = 0;

        for (int num : nums) {
            ans += Math.abs(max - num);
        }

        return ans;
}


}
