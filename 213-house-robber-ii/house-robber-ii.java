class Solution {
    public int rob(int[] nums) {
        int n=nums.length;
        if(n==1){
            return nums[0];
        }
        int case1 = solve(nums, 1, n - 1);

        int case2 = solve(nums, 0, n - 2);

        return Math.max(case1,case2);


    }

    public int solve(int[]nums, int start,int end){
        int prev=nums[start];
        int prev2=0;

        for(int i =start+1;i<=end;i++){
            int pick= nums[i]+prev2;
            int notpick=prev;
            int curr= Math.max(pick,notpick);
            prev2=prev;
            prev=curr;

        }
        return prev; 
    }
}