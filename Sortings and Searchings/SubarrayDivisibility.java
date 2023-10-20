import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import java.util.HashMap;

public class SubarrayDivisibility {
	
	public static void main(String[] args) {
		FastScanner fs = new FastScanner();
		int n = fs.nextInt();
		HashMap<Long, Long> freq = new HashMap<>();
		long pre = 0, ans = 0;
		freq.put(0L, 1L);
//		System.out.println("Value of 0%n: " + (0%3));
		for(int i=0; i<n; ++i) {
			long value = fs.nextLong();
			pre += value;
			long need = ((pre%n)+n)%n;
//			System.out.print(need + " ");
//			System.out.print(pre + " ");
			ans += freq.getOrDefault(need, 0L);
			freq.merge(((pre%n)+n)%n, 1L, (a, b) -> a + b);
		}
		
//		System.out.println(freq);
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
