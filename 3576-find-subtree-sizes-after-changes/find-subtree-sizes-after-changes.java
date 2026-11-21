
import java.util.*;

class Solution {
    List<Integer>[] original, adj;
    int[] ans;
    int[] nearest = new int[26];

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        original = new ArrayList[n];
        adj = new ArrayList[n];
        ans = new int[n];

        for (int i = 0; i < n; i++) {
            original[i] = new ArrayList<>();
            adj[i] = new ArrayList<>();
        }

        Arrays.fill(nearest, -1);

        // Build original tree
        for (int i = 1; i < n; i++) {
            original[parent[i]].add(i);
        }

        // Build modified tree
        dfsBuild(0, parent, s);

        // Calculate subtree sizes
        dfsSize(0);

        return ans;
    }

    void dfsBuild(int node, int[] parent, String s) {
        int ch = s.charAt(node) - 'a';

        int old = nearest[ch];

        // Find new parent
        int p = parent[node];

        if (old != -1) {
            p = old;
        }

        if (node != 0) {
            adj[p].add(node);
        }

        nearest[ch] = node;

        // Traverse original children
        for (int child : original[node]) {
            dfsBuild(child, parent, s);
        }

        // Backtrack
        nearest[ch] = old;
    }

    int dfsSize(int node) {
        int size = 1;

        for (int child : adj[node]) {
            size += dfsSize(child);
        }

        return ans[node] = size;
    }
}
