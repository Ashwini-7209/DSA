class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] best = new int[n];

        int INF = 1_000_000_000;
        int answer = INF;

        int left = 0;
        int sum = 0;
        int minimumLength = INF;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            while (sum > target) {
                sum -= arr[left];
                left++;
            }

            if (sum == target) {
                int currentLength = right - left + 1;

                if (left > 0 && best[left - 1] != INF) {
                    answer = Math.min(
                        answer,
                        currentLength + best[left - 1]);
                }

                minimumLength = Math.min(minimumLength, currentLength);
            }

            best[right] = minimumLength;
        }

        return answer == INF ? -1 : answer;
    }
}