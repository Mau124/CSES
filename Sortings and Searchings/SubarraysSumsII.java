import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.HashMap;

public class SubarraysSumsII {
	
	public static void main(String[] args) {
		FastScanner fs = new FastScanner();
		HashMap<Long, Integer> times = new HashMap<>();
		int n = fs.nextInt();
		long target = fs.nextLong(), pre = 0, ans = 0;
		times.put(pre, 1);
		for(int i=0; i<n; ++i) {
			long v = fs.nextLong();
			pre += v;
			long need = pre - target;
			if(times.containsKey(need)) {
				ans += times.get(need);
			}
			times.merge(pre, 1, (a, b) -> a + b);
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
