class Solution {
    public int characterReplacement(String s, int k) {

        int[] count = new int [26];
        int left = 0;
        int maxFreq = 0;
        int ans = 0;

        for (int right = 0; right <s.length(); right++) {
            int idx = s.charAt(right) -'A';
            count[idx]++;
            maxFreq = Math.max(maxFreq, count[idx]);

            while((right - left +1) - maxFreq > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }
            ans = Math.max(ans, right - left+1);
        }
        
        return ans;
    }
}