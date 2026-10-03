class Solution {
    public int longestValidParentheses(String s) {
          Stack<Integer> st = new Stack<>();
                  // Starting boundary
        st.push(-1);

        int max = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {

                // '(' ka index store karo
                st.push(i);

            } else {

                // ')' mila -> ek '(' remove karo
                st.pop();

                if (st.empty()) {

                    // Ye ')' kisi '(' se match nahi hua
                    st.push(i);

                } else {

                    // Valid substring ki length
                    int len = i - st.peek();

                    max = Math.max(max, len);
                }
            }
        }

        return max;
    }
}