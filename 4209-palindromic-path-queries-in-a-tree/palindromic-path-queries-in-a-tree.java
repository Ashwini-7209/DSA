class Solution {

    int[][] up;
    int[] depth, tin, tout, bit;
    int timer, LOG, n;
    List<Integer>[] graph;
    Fenwick fenwick;

    class Fenwick {
        int[] tree;
        int size;

        Fenwick(int size) {
            this.size = size;
            tree = new int[size + 1];
        }

        void update(int index, int value) {
            for (int i = index + 1; i <= size; i += i & -i) {
                tree[i] ^= value;
            }
        }

        int query(int count) {
            int result = 0;

            for (int i = count; i > 0; i -= i & -i) {
                result ^= tree[i];
            }

            return result;
        }
    }

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        this.n = n;
        graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        depth = new int[n];
        tin = new int[n];
        tout = new int[n];
        bit = new int[n];

        LOG = 32 - Integer.numberOfLeadingZeros(n);
        up = new int[LOG][n];

        // Iterative DFS to avoid stack overflow.
        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        int[] stack = new int[2 * n];
        int top = 0;
        stack[top++] = 0;

        while (top > 0) {
            int x = stack[--top];

            if (x < 0) {
                tout[~x] = timer;
                continue;
            }

            int u = x;
            tin[u] = timer++;

            stack[top++] = ~u;

            for (int v : graph[u]) {
                if (v == parent[u]) continue;

                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[top++] = v;
            }
        }

        for (int i = 0; i < n; i++) {
            up[0][i] = (parent[i] == -1) ? i : parent[i];
        }

        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                up[j][i] = up[j - 1][up[j - 1][i]];
            }
        }

        for (int i = 0; i < n; i++) {
            bit[i] = 1 << (s.charAt(i) - 'a');
        }

        fenwick = new Fenwick(n + 1);

        for (int i = 0; i < n; i++) {
            fenwick.update(tin[i], bit[i]);
            fenwick.update(tout[i], bit[i]);
        }

        List<Boolean> answer = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");
            int u = Integer.parseInt(parts[1]);

            if (parts[0].equals("update")) {
                int newBit = 1 << (parts[2].charAt(0) - 'a');

                int delta = bit[u] ^ newBit;

                if (delta != 0) {
                    fenwick.update(tin[u], delta);
                    fenwick.update(tout[u], delta);
                    bit[u] = newBit;
                }

            } else {
                int v = Integer.parseInt(parts[2]);
                int w = lca(u, v);

                int mask = fenwick.query(tin[u] + 1)
                         ^ fenwick.query(tin[v] + 1)
                         ^ bit[w];

                // True if at most one bit is set.
                answer.add((mask & (mask - 1)) == 0);
            }
        }

        return answer;
    }

    int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int j = 0; j < LOG; j++) {
            if ((diff & (1 << j)) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) return u;

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return up[0][u];
    }
}
