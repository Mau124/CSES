import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CountingDivisors {
	
	private static final int N = 1_000_000;
	private static int[] closestPrime = new int[N+1];
	
	static void build_sieve()
	{
		for(int i=2; i<=N; ++i)
		{
			if(closestPrime[i] == 0)
			{
				for(int j=i; j<=N; j+=i)
					closestPrime[j] = i;
			}
		}
	}
	
	static void print() 
	{
		for(int i=0; i<closestPrime.length; ++i)
			System.out.print(" " + closestPrime[i]);
		System.out.println();
	}
	
	public static void main(String[] args)
	{
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		
		int n = fs.nextInt();
		build_sieve();
		
		for(int i=0; i<n; ++i)
		{
			int x = fs.nextInt();
			int ans = 1; 
			
			while(x > 1)
			{
				int prime = closestPrime[x];
				int tmp = 0;
				
				while(x % prime == 0)
				{
					x /= prime;
					tmp++;
				}
				
				ans *= (tmp + 1);
			}
			
			out.println(ans);
			out.flush();
		}
		
		out.close();
	}
	
	static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");
 
        public String next() {
            while(!st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
 
            return st.nextToken();
        }
 
        int nextInt() {
            return Integer.parseInt(next());
        }
 
    }
}
