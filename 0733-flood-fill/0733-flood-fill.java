class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        if (oldColor == newColor) return image; // avoid infinite recursion
        dfs(image, sr, sc, oldColor, newColor);
        return image;
    }
    
    private void dfs(int[][] image, int r, int c, int oldColor, int newColor) {
        // boundary check
        if (r < 0 || c < 0 || r >= image.length || c >= image[0].length) return;
        // stop if not the target color
        if (image[r][c] != oldColor) return;
        
        // fill with new color
        image[r][c] = newColor;
        
        // recurse in 4 directions
        dfs(image, r + 1, c, oldColor, newColor);
        dfs(image, r - 1, c, oldColor, newColor);
        dfs(image, r, c + 1, oldColor, newColor);
        dfs(image, r, c - 1, oldColor, newColor);
    }
}