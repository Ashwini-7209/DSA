class Solution {
    public int scoreOfParentheses(String s) {
         int depth = 0;
        int score = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                depth++;
            } else {
                depth--;

                if (s.charAt(i - 1) == '(') {
                    score += 1 << depth;
                }
            }
        }

        return score;
    }
}