
class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {

        // Case 1: Buy separately
        long a = (long) need1 * cost1 + (long) need2 * cost2;

        // Case 2: Buy only combined items
        long b = (long) costBoth * Math.max(need1, need2);

        // Case 3: Buy combined items for common requirement
        int mn = Math.min(need1, need2);

        long c = (long) mn * costBoth
               + (long) (need1 - mn) * cost1
               + (long) (need2 - mn) * cost2;

        return Math.min(a, Math.min(b, c));
    }
}
