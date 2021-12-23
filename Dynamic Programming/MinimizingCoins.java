import java.util.Scanner;
import java.util.Arrays;

public class MinimizingCoins {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        int amount = s.nextInt();

        int[] coins = new int[n];

        for(int i=0; i<n; ++i) {
            coins[i] = s.nextInt();
        }

        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;

        for(int i=0; i<dp.length; ++i) {
            for(int j=0; j<coins.length; ++j) {
                if(i - coins[j] >= 0)
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
            }
        }

        if(dp[amount] < amount+1)
            System.out.println(dp[amount]);
        else
            System.out.println(-1);
    }   
}
