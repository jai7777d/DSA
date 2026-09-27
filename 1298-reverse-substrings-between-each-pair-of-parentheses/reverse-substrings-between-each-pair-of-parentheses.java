class Solution {
    public String reverseParentheses(String s) {

        Stack<String> stack = new Stack<>();

        StringBuilder curr = new StringBuilder();

        for (char ch : s.toCharArray()) {

            // Opening bracket
            if (ch == '(') {
                // Current string ko save karo
                stack.push(curr.toString());

                // Naya substring start
                curr = new StringBuilder();
            }

            // Closing bracket
            else if (ch == ')') {

                // Current substring ko reverse karo
                curr.reverse();

                // Pehle wali string nikalo
                String prev = stack.pop();

                // Dono ko join karo
                curr = new StringBuilder(prev + curr);
            }

            // Normal character
            else {
                curr.append(ch);
            }
        }

        return curr.toString();
    }
}