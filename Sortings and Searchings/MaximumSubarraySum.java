import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MaximumSubarraySum {
	
	public static void main(String[] args) {
		FastScanner fs = new FastScanner();
		int n = fs.nextInt();
		long[] arr = new long[n];
		for(int i=0; i<n; ++i) {
			arr[i] = fs.nextLong();
		}
		
		// Brute force solution
		// O(N^2)
//		long ans = Integer.MIN_VALUE;
//		for(int i=0; i<n; ++i) {
//			long sum = 0;
//			for(int j=i; j<n; ++j) {
//				sum += arr[j];
//				ans = Math.max(ans, sum);
//			}
//		}
//		System.out.print(ans);
		
		// Kadhane's algorithm
		// O(N)
		long auxSum = 0, ans = Integer.MIN_VALUE;
		for(int i=0; i<n; ++i) {
			auxSum += arr[i];
			
			ans = Math.max(ans,  auxSum);
			
			if(auxSum <= 0) {
				auxSum = 0;
			}
		}
		
		System.out.println(ans);
		
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
		
		public int nextInt() {
			return Integer.parseInt(next());
		}
		
		public long nextLong() {
			return Long.parseLong(next());
		}
	}
}
