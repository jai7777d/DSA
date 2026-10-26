
class Solution {
    public int countIslands(int[][] grid, int k) {

        int m = grid.length;
        int n = grid[0].length;

        int ans = 0;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 0) continue;

                long sum = 0;

                Stack<Integer> st = new Stack<>();

                st.push(i * n + j);

                sum += grid[i][j];
                grid[i][j] = 0; // Mark visited

                while (!st.isEmpty()) {

                    int curr = st.pop();

                    int r = curr / n;
                    int c = curr % n;

                    // Check 4 directions
                    for (int d = 0; d < 4; d++) {

                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        if (nr >= 0 && nr < m &&
                            nc >= 0 && nc < n &&
                            grid[nr][nc] > 0) {

                            sum += grid[nr][nc];

                            st.push(nr * n + nc);

                            grid[nr][nc] = 0;
                        }
                    }
                }

                if (sum % k == 0) {
                    ans++;
                }
            }
        }

        return ans;
    }
}
