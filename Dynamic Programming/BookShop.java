import java.util.Scanner;

public class BookShop {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int i, j;

        int books = s.nextInt();
        int amount = s.nextInt();

        int[] price = new int[books+1];
        int[] pages = new int[books+1];
        int[][] maxPages = new int[books+1][amount+1];

        for(i=1; i<=books; ++i)
            price[i] = s.nextInt();

        for(i=1; i<=books; ++i)
            pages[i] = s.nextInt();

        for(i=1; i<=books; ++i) {
            for(j=1; j<=amount; ++j) {
                if(j - price[i] >= 0)
                    maxPages[i][j] = Math.max(maxPages[i-1][j], maxPages[i-1][j - price[i]] + pages[i]);    
                else
                    maxPages[i][j] = maxPages[i-1][j];
            }
        }

        // for(i=1; i<=books; ++i) {
        //     for(j=1; j<=amount; ++j) {
        //         System.out.print(maxPages[i][j] + " ");
        //     }
        //     System.out.println();
        // } 

        System.out.println(maxPages[books][amount]);
    }
}
