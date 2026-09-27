class Solution {
    public String reverseParentheses(String s) {
         Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {

            if (ch != ')') {
                stack.push(ch);
            } 
            else {
                StringBuilder temp = new StringBuilder();

                while (stack.peek() != '(') {
                    temp.append(stack.pop());
                }

                stack.pop(); // '(' remove

                for (int i = 0; i < temp.length(); i++) {
                    stack.push(temp.charAt(i));
                }
            }
        }

        StringBuilder answer = new StringBuilder();

        while (!stack.isEmpty()) {
            answer.append(stack.pop());
        }

        return answer.reverse().toString();
    }
}