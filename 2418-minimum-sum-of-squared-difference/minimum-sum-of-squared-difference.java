class Solution {
public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {


    long k = (long) k1 + k2;
    int n = nums1.length;

    long[] diff = new long[n];
    long max = 0;
    long sum = 0;

    for (int i = 0; i < n; i++) {
        diff[i] = Math.abs((long) nums1[i] - nums2[i]);
        max = Math.max(max, diff[i]);
        sum += diff[i];
    }

    if (sum <= k) {
        return 0;
    }

    long left = 0;
    long right = max;

    while (left < right) {
        long mid = left + (right - left) / 2;
        long operations = 0;

        for (long d : diff) {
            if (d > mid) {
                operations += d - mid;
            }
        }

        if (operations <= k) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }

    long ans = 0;

    for (long d : diff) {
        long reduced = Math.min(d, left);
        ans += reduced * reduced;
        k -= d - reduced;
    }

    for (int i = 0; i < n && k > 0; i++) {
        if (diff[i] >= left && diff[i] > 0) {
            ans -= left * left;
            ans += (left - 1) * (left - 1);
            k--;
        }
    }

    return ans;
}


}
