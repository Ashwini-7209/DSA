class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            adj[u].add(new int[]{v, w});
        }

        int INF = 1_000_000_000;
        int[][] dist = new int[n][k + 1];

        for (int[] row : dist) {
            Arrays.fill(row, INF);
        }

        // state: {weight, node, consecutiveCount}
        PriorityQueue<int[]> pq =
            new PriorityQueue<>((a, b) -> a[0] - b[0]);

        dist[0][1] = 0;
        pq.offer(new int[]{0, 0, 1});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int weight = current[0];
            int node = current[1];
            int count = current[2];

            if (node == n - 1) {
                return weight;
            }

            if (weight > dist[node][count]) {
                continue;
            }

            for (int[] next : adj[node]) {
                int neighbor = next[0];
                int edgeWeight = next[1];

                int nextCount =
                    labels.charAt(neighbor) == labels.charAt(node)
                        ? count + 1
                        : 1;

                if (nextCount > k) {
                    continue;
                }

                int newWeight = weight + edgeWeight;

                if (newWeight < dist[neighbor][nextCount]) {
                    dist[neighbor][nextCount] = newWeight;
                    pq.offer(new int[]{newWeight, neighbor, nextCount});
                }
            }
        }

        return -1;
    }
}