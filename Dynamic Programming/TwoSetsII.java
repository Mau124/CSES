import java.util.Scanner;

public class TwoSetsII {

    private static final int mod = 1_000_000_007;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int i, j;

        int n = s.nextInt();

        int total = n*(n+1)/2;

        int[][] dp = new int[n+1][total+1];

        if(total%2==1) {
            System.out.println(0);
            return;
        }

        for(i=1; i<=n; ++i) {
            int sum = i*(i+1)/2;
            for(j=1; j<=sum; ++j) {
                if(j-i > 0) 
                    dp[i][j] = (dp[i-1][j] + dp[i-1][j-i]) % mod;
                else if(j-i == 0)
                    dp[i][j] = (dp[i-1][j] + 1) % mod;
                else
                    dp[i][j] = dp[i-1][j];
            }
        }

        // for(i=1; i<=n; ++i) {
        //     for(j=1; j<=total; ++j) {
        //         System.out.print(dp[i][j] + " " );
        //     }
        //     System.out.println();
        // }
        System.out.println(dp[n-1][total/2]);
    }
}
