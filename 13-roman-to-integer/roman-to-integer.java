class Solution {
    public int romanToInt(String s) {
        
        java.util.Map<Character, Integer> map = java.util.Map.of(
            'I', 1, 
            'V', 5, 
            'X', 10,
            'L', 50,
            'C', 100,
            'D', 500,
            'M', 1000 );

            int sum = 0;

            int n = s.length();

            for(int i =0; i<n; i++) {

                int curr = map.get(s.charAt(i));
                
                if(i +1 <n && curr < map.get(s.charAt(i + 1))) {
                    sum -= curr;
                } else {
                    sum += curr;
                }
            }
            return sum;
    }
}