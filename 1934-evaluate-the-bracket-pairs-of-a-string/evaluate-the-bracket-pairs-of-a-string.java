class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        
        Map<String , String> map = new HashMap<>();

        for(List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder sb = new StringBuilder();
        char[] ch = s.toCharArray();
        int n = ch.length;
        int i = 0;

        while(i<n) {
            if(ch[i] == '(') {
                int j = i+1;
                while(j < n && ch[j] != ')') {
                    j++;
                }

                String key = s.substring(i + 1, j);
                String value = map.getOrDefault(key, "?");
                sb.append(value);

                i= j+1;

            } else {
                sb.append(ch[i]);
                i++;
            }
        }
        return sb.toString();
    }
}