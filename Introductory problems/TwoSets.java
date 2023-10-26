import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TwoSets {
	
	static boolean[] flag = new boolean[6];

	public static void main(String[] args) {
		// 1 2 3 4 5 6 7 8 9 10 11
		
		// 3, 7, 11, 15, 19, 23, 27 -> You can always do it
		// 4, 8, 12, 16, 20, 24, 28 -> You can always do it
		// 2, 6, 10, 14, 18, etc.. -> You can't
		// 1, 5, 9, 13, 17, etc.. -> You can't
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int n = fs.nextInt();
		if(n%4 == 1 || n%4 == 2) {
			out.println("NO");
		} else if(n%4 == 0){
			out.println("YES");
			out.println(n/2);
			int i=1;
			for( ; i<=(n/4); ++i) {
				out.print(i + " " + (n+1-i) +" ");
			}
			out.println("\n" + (n/2));
			for( ; i<=(n/2); ++i) {
				out.print(i + " " + (n+1-i) + " ");
			}
		} else {
			out.println("YES");
			out.println((int)Math.ceil(n/2.0));
			out.print("1 2 ");
			int i=1;
			for( ; i<=(n-3)/4; ++i) {
				out.print((i+3) + " " + (n+1-i) + " ");
			}
			out.println("\n" + (int)Math.floor(n/2.0));
			out.print("3 ");
			for( ; i<=(n-3)/2; ++i) {
				out.print((i+3) + " " + (n+1-i) + " ");
			}
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
		
		public int nextInt() {
			return Integer.parseInt(next());
		}
		
		public long nextLong() {
			return Long.parseLong(next());
		}
	}
	
}
