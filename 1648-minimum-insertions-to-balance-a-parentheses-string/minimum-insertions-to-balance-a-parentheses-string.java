class Solution {
public int minInsertions(String s) {
int l = 0;
int diff = 0;


    for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) == '(') {
            l++;
        } else {
            if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                i++;
            } else {
                diff++;
            }

            if (l > 0) {
                l--;
            } else {
                diff++;
            }
        }
    }

    return diff + 2 * l;
}

}
