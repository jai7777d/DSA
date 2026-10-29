
import java.util.*;

class Solution {

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        List<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        int LOG = 1;
        while ((1 << LOG) <= n) {
            LOG++;
        }

        int[][] up = new int[n][LOG];
        int[] parent = new int[n];
        int[] depth = new int[n];
        int[] tin = new int[n];
        int[] size = new int[n];
        int[] prefix = new int[n];

        char[] chars = s.toCharArray();

        Arrays.fill(parent, -1);

        // Iterative DFS
        int[] stack = new int[n];
        int[] order = new int[n];

        int top = 0;
        int count = 0;

        stack[top++] = 0;
        parent[0] = 0;

        while (top > 0) {
            int u = stack[--top];

            tin[u] = count;
            order[count++] = u;

            if (u == 0) {
                prefix[u] = 1 << (chars[u] - 'a');
            } else {
                prefix[u] = prefix[parent[u]]
                          ^ (1 << (chars[u] - 'a'));
            }

            for (int v : graph[u]) {
                if (parent[v] == -1) {
                    parent[v] = u;
                    depth[v] = depth[u] + 1;
                    stack[top++] = v;
                }
            }
        }

        // Binary lifting table
        for (int i = 0; i < n; i++) {
            up[i][0] = parent[i];
        }

        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                up[i][j] = up[up[i][j - 1]][j - 1];
            }
        }

        // Calculate subtree sizes
        Arrays.fill(size, 1);

        for (int i = n - 1; i > 0; i--) {
            int u = order[i];
            size[parent[u]] += size[u];
        }

        // Fenwick tree for range XOR updates
        Fenwick bit = new Fenwick(n);

        List<Boolean> result = new ArrayList<>();

        for (String query : queries) {

            String[] parts = query.split("\\s+");

            if (parts[0].equals("update")) {

                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                int oldMask = 1 << (chars[u] - 'a');
                int newMask = 1 << (c - 'a');

                int delta = oldMask ^ newMask;

                // Update all root-prefix masks in subtree of u
                bit.rangeXor(tin[u], tin[u] + size[u] - 1, delta);

                chars[u] = c;

            } else {

                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int lca = getLCA(u, v, depth, up, LOG);

                int pu = prefix[u] ^ bit.query(tin[u]);
                int pv = prefix[v] ^ bit.query(tin[v]);

                int mask = pu ^ pv
                         ^ (1 << (chars[lca] - 'a'));

                // At most one character can have odd frequency
                result.add((mask & (mask - 1)) == 0);
            }
        }

        return result;
    }

    private int getLCA(
            int u, int v, int[] depth, int[][] up, int LOG) {

        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int j = 0; j < LOG; j++) {
            if ((diff & (1 << j)) != 0) {
                u = up[u][j];
            }
        }

        if (u == v) {
            return u;
        }

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[u][j] != up[v][j]) {
                u = up[u][j];
                v = up[v][j];
            }
        }

        return up[u][0];
    }

    static class Fenwick {

        int[] tree;
        int n;

        Fenwick(int n) {
            this.n = n;
            tree = new int[n + 2];
        }

        void update(int index, int value) {

            index++;

            while (index <= n) {
                tree[index] ^= value;
                index += index & -index;
            }
        }

        void rangeXor(int left, int right, int value) {

            update(left, value);
            update(right + 1, value);
        }

        int query(int index) {

            index++;

            int result = 0;

            while (index > 0) {
                result ^= tree[index];
                index -= index & -index;
            }

            return result;
        }
    }
}
