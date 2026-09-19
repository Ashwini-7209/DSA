class Solution {
    public int myAtoi(String s) {
        int n = s.length();
        int i = 0;

        while(i < n && s.charAt(i) == ' ') {
            i++;
        } 

        if (i ==n) {
            return 0;
        }

         int sign = 1;

         if(s.charAt(i) == '+' || s.charAt(i) =='-') {
            if(s.charAt(i) == '-') {
                sign = -1;
            }
            i++;
         }

         int result = 0;

         while(i< n && s.charAt(i) >= '0'
                    && s.charAt(i) <= '9') {
                        int digit = s.charAt(i) - '0';
                        if(result > Integer.MAX_VALUE / 10 || (result== Integer.MAX_VALUE /10 
                        && digit > 7)) {

                        if(sign == 1) {
                            return Integer.MAX_VALUE;
                        } else {
                            return Integer.MIN_VALUE;
                        }
                    }

                    result = result * 10 + digit;
                    i++;
    }
    return result * sign;
}
}