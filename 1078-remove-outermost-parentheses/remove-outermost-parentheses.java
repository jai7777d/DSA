class Solution {
    public String removeOuterParentheses(String s) {
      int count =0;
      String ans="";
      for(int i=0; i<s.length();i++){

     
      if(s.charAt(i)=='('){
     
          if(count!=0){
             ans = ans + s.charAt(i);
          }
             count++;
      }  

      else if(s.charAt(i)==')'){ 
           count--;
        if(count != 0){
       ans = ans + s.charAt(i);
        }
        continue;
      } }
      return ans;
    }
}