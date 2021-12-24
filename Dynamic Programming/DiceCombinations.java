import java.util.Scanner;

public class DiceCombinations {

    private static final int mod = 1000000007;
    public static void main(String[] main) {
        Scanner s = new Scanner(System.in);
        int[] dp;
        int i, j;

        int n = s.nextInt();
        dp = new int[n+1];

        for(i=1; i<=n; ++i) {
            int ways = 0;
            for(j=1; j<=6; ++j) {
                if(i - j >= 0)
                    ways = (ways + dp[i - j]) % mod;
            }

            if(i <= 6)
                dp[i] = (ways + 1) % mod;
            else
                dp[i] = ways;
        }

        System.out.println(dp[n]);
    }
}
