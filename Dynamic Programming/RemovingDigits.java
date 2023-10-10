import java.util.Scanner;
 
public class RemovingDigits {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        int n = sc.nextInt();
 
        int ans = 0;
        while(n > 0) {
            // We search for the greatest one
            int greatest = 0, tmp = n;
            while(tmp > 0) {
                greatest = Math.max(greatest, tmp%10);
                tmp/=10;
            }
            n-=greatest;
            ans++;
        }
 
        System.out.println(ans);
        sc.close();
    }
