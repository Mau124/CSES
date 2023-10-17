import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SubarraysSumsI {
	public static void main(String[] args) {
		FastScanner fs = new FastScanner();
		int n = fs.nextInt();
		long target = fs.nextLong(), ans = 0;
		long[] arr = new long[n];
		long[] p = new long[n+1];
		p[1] = fs.nextLong();
		arr[0] = p[1];
		for(int i=2; i<=n; ++i) {
			arr[i-1] = fs.nextLong();
			p[i] = p[i-1] + arr[i-1];
		}
//		System.out.println("Prefix sum array");
//		for(int i=0; i<=n; ++i) {
//			System.out.print(p[i] + " ");
//		}
//		System.out.println();
		// Solution with prefix sums
//		int l = n-1, r = n;
//		while(l >= 0 && l <= r) {
//			long sum = p[r] - p[l];
//			if(sum == target) {
//				ans++;
//				r--;
//			} else if(sum > target) {
//				r--;
//				if(r > l + 1) {
//					l++;
//				}
//			} else {
//				l--;
//			}
//		}
		// Solution with sliding windows
		int l = 0, r = 0;
		long sum = arr[r];
		while(r < n && l <= r) {
			if(sum == target)  {
				sum-=arr[l];
				ans++; r++; l++;
				if(r < n)
					sum+=arr[r];
			} else if(sum > target) {
				if(l == r) {
					sum-=arr[l];
					r++; l++;
					if(r < n)
						sum+=arr[r];
				} else {
					sum -= arr[l];
					l++;
				}
			} else {
				r++;
				if(r < n)
					sum += arr[r];
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
