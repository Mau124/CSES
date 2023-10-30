import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.TreeMap;

public class TrafficLights {
	
	public static void main(String[] args) {
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		
		int n = fs.nextInt(), q = fs.nextInt();
		StringBuilder sb = new StringBuilder();
		TreeSet<Integer> lights = new TreeSet<>();
		TreeMap<Integer, Integer> dist = new TreeMap<>(); 
		lights.add(0);
		lights.add(n);
		dist.put(n, 1);
		for(int qq=0; qq<q; ++qq) {
			int pos = fs.nextInt();
			int end = lights.ceiling(pos);
			int st = lights.floor(pos);
//			out.println("Value of end: " + end);
//			out.println("Value of st: " + st);
			int interval = end-st;
			
			// Remove that interval from dist
			Integer freq = dist.get(interval);
			if(freq != null) {
				if(freq == 1)
					dist.remove(interval);
				else
					dist.put(interval, freq-1);
			}
			dist.merge(end-pos, 1, (a, b) -> a + b);
			dist.merge(pos-st, 1, (a, b) -> a + b);
			lights.add(pos);
			sb.append(dist.lastEntry().getKey() + " ");
//			out.println(dist.lastEntry().getKey());
		}
		out.print(sb);
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
		
		public int nextInt() {
			return Integer.parseInt(next());
		}
		
		public long nextLong() {
			return Long.parseLong(next());
		}
	}
	
}
