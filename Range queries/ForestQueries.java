import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ForestQueries {
	
	public static void main(String[] args) {
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int n = fs.nextInt(), q = fs.nextInt();
		char[][] forest = new char[n+1][];
		int[][] pre = new int[n+1][n+1];
		
		for(int i=1; i<=n; ++i) {
			forest[i] = fs.next().toCharArray();
		}
		
		// Calculate the prefixSums in 2D
		// pre[i][j] = (forest[i][j] + pre[i][j-1] + pre[i-1][j]) - pre[i-1][j-1]
		for(int i=1; i<=n; ++i) {
			for(int j=1; j<=n; ++j) {
				int tree = (forest[i][j-1] == '*') ? 1 : 0;
				pre[i][j] = (tree + pre[i][j-1] + pre[i-1][j]) - pre[i-1][j-1];
			}
		}
		
//		System.out.println("Printing the value of the prefix sums");
//		printMat(pre);
		
		// Answer queries
		// Count(x1, y1, x2, y2) = pre[y2][x2] - pre[y1-1][x2] - pre[y2][x1-1] + pre[y1+1][x1+1]
		for(int qq=0; qq<q; ++qq) {
			int y1 = fs.nextInt(), x1 = fs.nextInt();
			int y2 = fs.nextInt(), x2 = fs.nextInt();
			
			out.println(pre[y2][x2] - pre[y1-1][x2] - pre[y2][x1-1] + pre[y1-1][x1-1]);
		}
		
		out.close();
	}
	
	static void printMat(int[][] mat) {
		for(int i=0; i<mat.length; ++i) {
			for(int j=0; j<mat[i].length; ++j) {
				System.out.print(mat[i][j] + " ");
			}
			System.out.println();
		}
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
