class Solution {
    public List<String> removeInvalidParentheses(String s) {

        List<String> ans = new ArrayList<>();
        Queue<String> q = new LinkedList<>();
        HashSet<String> set = new HashSet<>();

        q.add(s);
        set.add(s);

        boolean found = false;

        while (!q.isEmpty()) {

            int size = q.size();

            while (size-- > 0) {

                String curr = q.poll();

                if (isValid(curr)) {
                    ans.add(curr);
                    found = true;
                }

                if (found)
                    continue;

                for (int i = 0; i < curr.length(); i++) {

                    if (curr.charAt(i) != '(' &&
                        curr.charAt(i) != ')')
                        continue;

                    String next = curr.substring(0, i)
                                 + curr.substring(i + 1);

                    if (!set.contains(next)) {
                        set.add(next);
                        q.add(next);
                    }
                }
            }

            if (found)
                break;
        }

        return ans;
    }

    public boolean isValid(String s) {

        int count = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(')
                count++;

            else if (ch == ')') {
                count--;

                if (count < 0)
                    return false;
            }
        }

        return count == 0;
    }
}