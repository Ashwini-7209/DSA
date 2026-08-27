class Solution {
    public String minWindow(String s, String t) {

        if(s == null || t == null || s.length() == 0 || t.length() ==0 || t.length() > s.length()) {
            return "";
        }

        int[] need = new int[128];
        for(char c : t.toCharArray()) {
            need[c]++;
        }

        int required = 0;
        for(int count : need) {
           if(count > 0) required++;
        }

        int[] window = new int[128];
        int formed = 0;
        
        int left = 0;
        int bestLen = Integer.MAX_VALUE;
        int bestStart = 0;

        for(int right = 0; right < s.length(); right++) {
            char c =s.charAt(right);
            window[c]++;
        

        if(need[c] > 0 && window[c] == need[c]) {
            formed++;

        }
        while(formed == required) {
            int currLen = right - left +1;
            if(currLen < bestLen) {
                bestLen = currLen;
                bestStart = left;
            }

            char leftChar = s.charAt(left);
            window[leftChar]--;

            if(need[leftChar] > 0 && window[leftChar] < need[leftChar]) {
                formed--;

            }
            left ++;
        }
    }

    return bestLen == Integer.MAX_VALUE ? "" : s.substring(bestStart, bestStart  + bestLen);

  
   }
}