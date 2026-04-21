class Solution {
    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        
        Queue<int[]> q = new LinkedList<>();
        int fresh = 0;
        
        // Step 1: Initialize queue and count fresh
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    q.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }
        
        // If no fresh oranges
        if (fresh == 0) return 0;
        
        int minutes = 0;
        int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
        
        // Step 2: BFS
        while (!q.isEmpty()) {
            int size = q.size();
            boolean rottenThisMinute = false;
            
            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                
                for (int[] d : dir) {
                    int x = curr[0] + d[0];
                    int y = curr[1] + d[1];
                    
                    if (x >= 0 && y >= 0 && x < m && y < n && grid[x][y] == 1) {
                        grid[x][y] = 2;
                        q.offer(new int[]{x, y});
                        fresh--;
                        rottenThisMinute = true;
                    }
                }
            }
            
            if (rottenThisMinute) minutes++;
        }
        
        return fresh == 0 ? minutes : -1;
    }
}