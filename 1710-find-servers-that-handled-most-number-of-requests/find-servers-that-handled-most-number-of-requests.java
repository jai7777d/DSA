class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {

        TreeSet<Integer> available = new TreeSet<>();

        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        PriorityQueue<int[]> busy = new PriorityQueue<>(
            (a, b) -> a[0] - b[0]
        );

        int[] count = new int[k];

        for (int i = 0; i < arrival.length; i++) {

            // Free all servers whose work is completed
            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                int server = busy.poll()[1];
                available.add(server);
            }

            if (available.isEmpty()) {
                continue;
            }

            int preferred = i % k;

            // Find smallest available server >= preferred
            Integer server = available.ceiling(preferred);

            // If not found, wrap around to smallest server
            if (server == null) {
                server = available.first();
            }

            available.remove(server);

            count[server]++;

            busy.offer(new int[]{
                arrival[i] + load[i], server
            });
        }

        int max = 0;

        for (int x : count) {
            max = Math.max(max, x);
        }

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == max) {
                ans.add(i);
            }
        }

        return ans;
    }
}