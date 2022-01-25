import java.util.Scanner;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

public class CycleFinding {
	
	static final int N = 2501;
	static Node[] nodes = new Node[N];
	static boolean[] visited = new boolean[N];
	static int[][] scores = new int[N][N];
	static long[] score = new long[N];
	static int[] par = new int[N];
	
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		
		int n = s.nextInt(), m = s.nextInt(), a = 1, b = 1, c = 1;
		int x = -1;
		ArrayList<Edge> edges = new ArrayList<>();
		Arrays.fill(score, Integer.MAX_VALUE);
		score[1] = 0;
		
		for(int i=1; i<=n; ++i)
			nodes[i] = new Node();
		
		for(int i=0; i<m; ++i)
		{
			a = s.nextInt(); b = s.nextInt(); c = s.nextInt();
			scores[a][b] = c;
			nodes[a].add(b);
		}
		
		// Hacer bfs para colocarlos de fomar correcta
		for(int i=1; i<=n; ++i)
		{
			Queue<Integer> q = new LinkedList<>();
			q.add(i);
			
			if(!visited[i])
			{
				visited[i] = true;
				while(!q.isEmpty())
				{
					int v = q.remove();
					
					for(int u: nodes[v].adj)
					{
						edges.add(new Edge(v, u, scores[v][u]));
						
						if(!visited[u])
						{
							q.add(u);
							visited[u] = true;
						}
						
					}
				}
			}
		}
		
//		System.out.println(edges.size());
//		
//		for(Edge edge: edges)
//		{
//			System.out.printf("%d %d %d\n", edge.v, edge.u, edge.score);
//		}
		
		// Buscar ciclo utilizando Bellman-Ford
		for(int i=0; i<n; ++i)
		{
			x = -1;
			for(Edge edge: edges)
			{
				if(score[edge.v] + edge.score < score[edge.u] )
				{
					score[edge.u] = score[edge.v] + edge.score;
					par[edge.u] = edge.v;
					x = edge.u;
				}
			}	
		}
		
		if(x == -1)
		{
			System.out.println("NO");
		} else 
		{
			for (int i = 0; i < n; ++i)
	            x = par[x];
			
			System.out.println("YES");
			//System.out.println(x);
			ArrayList<Integer> cycle = new ArrayList<>();
			for(int v = x; ; v = par[v])
			{
				cycle.add(v);
				
				if(v == x && cycle.size() > 1)
					break;
			}
			
			for(int i=cycle.size()-1; i>=0; --i)
			{
				System.out.print(cycle.get(i) + " ");
			}
			
			System.out.println();
		}
		
		s.close();
	}
	
	static class Node
	{
		ArrayList<Integer> adj = new ArrayList<>();
		
		public Node() {}
		
		void add(int v)
		{
			adj.add(v);
		}
		
		int get(int it)
		{
			return adj.get(it);
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
}
