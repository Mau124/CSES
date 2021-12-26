import java.util.Scanner;
import java.util.ArrayList;

public class MoneySums {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int i, j;

        int n = s.nextInt();
        int[] coins = new int[n+1];


        int sum = 0;

        for(i=1; i<=n; ++i) {
            coins[i] = s.nextInt();
            sum += coins[i];
        }

        boolean[][] moneySum = new boolean[n+1][sum+1];

        for(i=1; i<=n; ++i) {
            for(j=1; j<=sum; ++j) {
                if(moneySum[i-1][j]) 
                    moneySum[i][j] = true;
                else if(j - coins[i] > 0)
                    moneySum[i][j] = moneySum[i-1][j - coins[i]];
                
                if(j - coins[i] == 0)
                    moneySum[i][j] = true;
            }
        }

        ArrayList<Integer> numbers = new ArrayList<>();
        for(i=1; i<=sum; ++i) {
            if(moneySum[n][i]) {
                numbers.add(i);
            }
        }

        System.out.println(numbers.size());
        for(int num: numbers) {
            System.out.print(num + " ");
        }
    }
}
