import java.util.Scanner;

public class CoinCombinationsII {
    private static final int mod = 1000_000_007;
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

        ways[0] = 1;
        for(j=0; j<coins.length; ++j) {
            for(i=1; i<=amount; ++i) {
                if(i - coins[j] >= 0)
                    ways[i] = (ways[i - coins[j]] + ways[i]) % mod;
            }
        }
        System.out.println(ways[amount]);
    }
}
