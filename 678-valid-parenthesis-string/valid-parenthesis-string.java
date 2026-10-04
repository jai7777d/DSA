class Solution {
    public boolean checkValidString(String s) {

        int low = 0;
        int high = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                low++;
                high++;
            }

            else if (s.charAt(i) == ')') {
                low--;
                high--;
            }

            else { // '*'
                low--;   // '*' = ')'
                high++;  // '*' = '('
            }

            // Too many ')' possible
            if (high < 0) {
                return false;
            }

            // Minimum balance negative ho sakta hai,
            // because '*' ko empty bhi bana sakte hain
            low = Math.max(low, 0);
        }

        return low == 0;
    }
}