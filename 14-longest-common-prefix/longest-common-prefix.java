class Solution {
    public String longestCommonPrefix(String[] strs) {
        StringBuilder ans = new StringBuilder();
        int j = 0;

        for (int i = 0; i < strs.length - 1; i++) {
  
            while (j < strs[i].length() && j < strs[i + 1].length()) {

                if (strs[i].charAt(j) != strs[i + 1].charAt(j)) {
                    break;
                }

                j++;
            }

            if (i == 0) {
                ans.append(strs[i].substring(0, j));
            } else {
                while (ans.length() > j) {
                    ans.deleteCharAt(ans.length() - 1);
                }
            }

            if (j == 0) {
                return "";
            }

            j = 0;
        }
 
 if (strs.length == 1) {
    return strs[0];
}
        return ans.toString();
    }
}