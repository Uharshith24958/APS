class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
         List<Integer>[] redGraph = new ArrayList[n];
        List<Integer>[] blueGraph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            redGraph[i] = new ArrayList<>();
            blueGraph[i] = new ArrayList<>();
        }
        for (int[] e : redEdges) redGraph[e[0]].add(e[1]);
        for (int[] e : blueEdges) blueGraph[e[0]].add(e[1]);

        // Result array
        int[] result = new int[n];
        Arrays.fill(result, -1);

        // BFS queue: [node, color, distance]
        // color: 0 = red, 1 = blue
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, 0}); // start with red
        q.offer(new int[]{0, 1, 0}); // start with blue

        boolean[][] visited = new boolean[n][2]; // visited[node][color]
        visited[0][0] = true;
        visited[0][1] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int node = cur[0], color = cur[1], dist = cur[2];

            // Update result
            if (result[node] == -1 || dist < result[node]) {
                result[node] = dist;
            }

            // Next edges must be opposite color
            if (color == 0) { // last was red, so next must be blue
                for (int nei : blueGraph[node]) {
                    if (!visited[nei][1]) {
                        visited[nei][1] = true;
                        q.offer(new int[]{nei, 1, dist + 1});
                    }
                }
            } else { // last was blue, so next must be red
                for (int nei : redGraph[node]) {
                    if (!visited[nei][0]) {
                        visited[nei][0] = true;
                        q.offer(new int[]{nei, 0, dist + 1});
                    }
                }
            }
        }

        return result;

    }
}