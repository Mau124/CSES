import java.util.Scanner;

public class CoinCombinationsI {
    private static final int mod = 1000000007;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int i, j;

        int n = s.nextInt();
        int amount = s.nextInt();
        int[] coins = new int[n];
        int[] ways = new int[amount+1];

        for(i=0; i<n; ++i) {
            coins[i] = s.nextInt();
        }

        for(i=1; i<=amount; ++i) {
            int totalWays = 0;
            boolean exist = false;
            for(j=0; j < coins.length; ++j) {

                if(i - coins[j] > 0) {
                    totalWays = (totalWays + ways[i - coins[j]]) % mod;
                }

                if(i == coins[j])
                    exist = true;
            }

            if(exist)
                ways[i] = (totalWays + 1) % mod;
            else 
                ways[i] = totalWays;
        }

        System.out.println(ways[amount]);
    }
}
