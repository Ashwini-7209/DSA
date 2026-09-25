class Solution {
    private String expression;
    private int index;

    public List<String> braceExpansionII(String expression) {
        this.expression = expression;
        this.index = 0;

        Set<String> result = parseExpression();

        List<String> answer = new ArrayList<>(result);
        Collections.sort(answer);

        return answer;
    }

    private Set<String> parseExpression() {
        Set<String> result = parseTerm();

        while (index < expression.length()
                && expression.charAt(index) == ',') {

            index++;

            Set<String> next = parseTerm();
            result.addAll(next);
        }

        return result;
    }

    private Set<String> parseTerm() {
        Set<String> result = new HashSet<>();
        result.add("");

        while (index < expression.length()
                && expression.charAt(index) != '}'
                && expression.charAt(index) != ',') {

            Set<String> next;

            if (expression.charAt(index) == '{') {
                index++; // '{' skip

                next = parseExpression();

                index++; // '}' skip
            } else {
                String letter = String.valueOf(expression.charAt(index));
                index++;

                next = new HashSet<>();
                next.add(letter);
            }

            result = combine(result, next);
        }

        return result;
    }

    private Set<String> combine(Set<String> first, Set<String> second) {
        Set<String> result = new HashSet<>();

        for (String a : first) {
            for (String b : second) {
                result.add(a + b);
            }
        }

        return result;
    }
}