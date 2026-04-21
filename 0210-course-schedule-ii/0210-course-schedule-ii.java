class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] pre : prerequisites) {
            graph.get(pre[1]).add(pre[0]);
        }

        int[] visited = new int[numCourses]; // 0=unvisited, 1=visiting, 2=visited
        List<Integer> order = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            if (!dfs(graph, visited, order, i)) return new int[0];
        }

        Collections.reverse(order); // reverse for correct order
        return order.stream().mapToInt(Integer::intValue).toArray();
    }

    private boolean dfs(List<List<Integer>> graph, int[] visited, List<Integer> order, int course) {
        if (visited[course] == 1) return false; // cycle
        if (visited[course] == 2) return true;  // already processed

        visited[course] = 1; // visiting
        for (int next : graph.get(course)) {
            if (!dfs(graph, visited, order, next)) return false;
        }
        visited[course] = 2; // visited
        order.add(course);
        return true;

    }
}