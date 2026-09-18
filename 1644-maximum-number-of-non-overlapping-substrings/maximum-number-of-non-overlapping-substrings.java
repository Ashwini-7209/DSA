class Solution {
    public List<String> maxNumOfSubstrings(String s) {
         int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

   
        for (int i = 0; i < 26; i++) {
            first[i] = -1;
        }

        for (int i = 0; i < n; i++) {
            int index = s.charAt(i) - 'a';

            if (first[index] == -1) {
                first[index] = i;
            }

            last[index] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        for (int ch = 0; ch < 26; ch++) {
            if (first[ch] == -1) {
                continue;
            }

            int start = first[ch];
            int end = last[ch];
            boolean valid = true;

            for (int i = start; i <= end; i++) {
                int currentChar = s.charAt(i) - 'a';

                if (first[currentChar] < start) {
                    valid = false;
                    break;
                }

                end = Math.max(end, last[currentChar]);
            }

            if (valid) {
                intervals.add(new int[]{start, end});
            }
        }

        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));

        List<String> result = new ArrayList<>();
        int previousEnd = -1;

        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];

            if (start > previousEnd) {
                result.add(s.substring(start, end + 1));
                previousEnd = end;
            }
        }

        return result;
    }
}