class Solution {
    public int minOperations(int k) {
        int ans=k-1;
        for(int a=1;a<=k;a++){
        int x=(k+a-1)/a;
        int operations= (a-1)+(x-1);
        ans= Math.min(ans,operations);
        }
        return ans;
    }
}