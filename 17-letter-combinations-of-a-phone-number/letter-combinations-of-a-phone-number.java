class Solution {

      private static final String[] KEYPAD = {
        "", "", "abc", "def", "ghi", "jkl","mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if(digits == null || digits.length() ==0) {
            return result;
        }

        backtrack(digits , 0, new StringBuilder(), result);
        return result;
    }

    private void backtrack(String digits, int index, StringBuilder current, List<String> result) {

        if(index == digits.length()) {
            result.add(current.toString());
            return ;
        }

        char digitChar = digits.charAt(index);
        int digit = digitChar -'0';
        String letters = KEYPAD[digit];

        for(int i = 0; i< letters.length(); i++) {
            char ch = letters.charAt(i);
            current.append(ch);

            backtrack(digits, index +1, current, result);

            current.deleteCharAt(current.length() -1);
        }
    }
}