class Solution {
    static final int MOD = 1_000_000_007;

    int n;
    long[] sum, sq, lazy;

    void push(int node, int l, int r) {
        if (lazy[node] == 0) return;

        long v = lazy[node];
        long len = r - l + 1L;

        sq[node] = (sq[node] + 2 * v % MOD * sum[node] % MOD
                    + len % MOD * v % MOD * v % MOD) % MOD;

        sum[node] = (sum[node] + len % MOD * v % MOD) % MOD;

        if (l != r) {
            lazy[node * 2] = (lazy[node * 2] + v) % MOD;
            lazy[node * 2 + 1] = (lazy[node * 2 + 1] + v) % MOD;
        }

        lazy[node] = 0;
    }

    void update(int node, int l, int r, int ql, int qr) {
        push(node, l, r);

        if (r < ql || l > qr) return;

        if (ql <= l && r <= qr) {
            lazy[node] = 1;
            push(node, l, r);
            return;
        }

        int mid = (l + r) / 2;

        update(node * 2, l, mid, ql, qr);
        update(node * 2 + 1, mid + 1, r, ql, qr);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
    }

    public int sumCounts(int[] nums) {
        n = nums.length;

        sum = new long[4 * n];
        sq = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        java.util.Arrays.fill(last, -1);

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int left = last[nums[i]] + 1;

            update(1, 0, n - 1, left, i);

            ans = (ans + sq[1]) % MOD;

            last[nums[i]] = i;
        }

        return (int) ans;
    }
}