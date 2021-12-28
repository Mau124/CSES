import java.util.Scanner;
import java.util.Arrays;

public class ArrayDescription {

    private static final int mod = 1_000_000_007;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int i, j;

        int n = s.nextInt();
        int m = s.nextInt();
        int[] x = new int[n+1];
        int[][] numCombinations = new int[n+1][m+2];

        for(i=1; i<=n; ++i) 
            x[i] = s.nextInt();

        if(x[1] == 0) 
            Arrays.fill(numCombinations[1], 1, m+1, 1);
        else 
            numCombinations[1][x[1]] = 1;

        for(i=2; i<=n; ++i) {
            if(x[i] == 0) {
                for(j=1; j<=m; ++j) {
                    numCombinations[i][j] = ((numCombinations[i-1][j-1] + numCombinations[i-1][j]) % mod + numCombinations[i-1][j+1]) % mod;
                }
            } else {
                numCombinations[i][x[i]] = ((numCombinations[i-1][x[i] - 1] + numCombinations[i-1][x[i]]) % mod + numCombinations[i-1][x[i]+1]) % mod;
            }
        }

        // for(i=1; i<=n; ++i) {
        //     for(j=1; j<=m; ++j) {
        //         System.out.print(numCombinations[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        int sum = 0;
        for(j=1; j<=m; ++j) {
            sum = (sum + numCombinations[n][j]) % mod;
        } 
        System.out.println(sum);
    }
}
