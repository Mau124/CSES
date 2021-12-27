import java.util.Scanner;

public class GridPaths {
    private static final int mod = 1_000_000_007;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int i, j;

        int n = s.nextInt();
        s.nextLine();

        char[][] grid = new char[n+1][n+1];
        int[][] paths = new int[n+1][n+1]; 

        for(i=1; i<=n; ++i) {
            grid[i] = (" " + s.nextLine()).toCharArray();
        }

        paths[1][0] = 1;
        for(i=1; i<=n; ++i) {
            for(j=1; j<=n; ++j) {
                if(grid[i][j] == '*') 
                    paths[i][j] = 0;
                else
                    paths[i][j] = (paths[i-1][j] + paths[i][j-1]) % mod;
            }
        }

        System.out.println(paths[n][n]);
    }
}
