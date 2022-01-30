import java.util.StringTokenizer;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
 
public class HighScore {
	
	static final int N = 2501;
	static final long INF = (long)1e17;
	static final long NINF = -INF;
	
	static long[] score = new long[N];

	public static void main(String[] args)
	{
		FastScanner s = new FastScanner();
		Arrays.fill(score, INF);
		
		int n = s.nextInt(), m = s.nextInt();
		ArrayList<Edge> edges = new ArrayList<>();
			
		for(int i=0; i<m; ++i) 
		{
			int v = s.nextInt(), u = s.nextInt(), score = s.nextInt();
			edges.add(new Edge(v, u, -score));
		}
		
		// Bellman-Ford algorithm
		
		score[1] = 0;
		for(int i=0; i < n-1; ++i)
		{
			for(Edge edge: edges)
			{
				if(score[edge.v] == INF) continue;
				score[edge.u] = Math.min(score[edge.u], score[edge.v] + edge.score); 
			}
		}
		
		for(int i=0; i < n-1; ++i)
		{
			for(Edge edge: edges)
			{
				if(score[edge.v] == INF) continue;
				if(score[edge.v] + edge.score < score[edge.u])
				{
					score[edge.u] = NINF; 
				}
			}
		}
		
		
		
		if(score[n] == NINF)
		{
			System.out.println("-1");
		} else 
		{
			System.out.println(-score[n]);
		}
	}

	static class Edge 
	{
		int v, u;
		long score;
		
		public Edge(int v, int u, long score)
		{
			this.v = v;
			this.u = u;
			this.score = score;
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
 
        int nextInt() {
            return Integer.parseInt(next());
        }
 
    }
}