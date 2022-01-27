import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Monsters {
	
	static final int N = 1000;
	static char[][] mat = new char[N][N];
	static int[][] dis = new int[N][N];
	static char[][] par = new char[N][N];
	static char[] ans = new char[N*N];
	
	static final int[] v = {-1, 0, 1, 0};
	static final int[] h = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer s = new StringTokenizer(br.readLine());
		PrintWriter out = new PrintWriter(System.out);

		int n = Integer.parseInt(s.nextToken());
		int m = Integer.parseInt(s.nextToken());
		
		int row_end = 0, col_end = 0;
		
		Queue<Pair> player = new LinkedList<>();
		Queue<Pair> monsters = new LinkedList<>();
		boolean isPosible = false;
		
		for(int i=0; i<n; ++i)
		{
			String line = br.readLine();
			for(int j=0; j<m; ++j)
			{
				mat[i][j] = line.charAt(j);
				
				if(mat[i][j] == 'A')
				{
					player.add(new Pair(i, j));
					mat[i][j] = 'P';
				}
				
				if(mat[i][j] == 'M')
				{
					monsters.add(new Pair(i, j));
					mat[i][j] = '#';
				}
			}
		}
		
		// Start bfs
		int possibilities = 0;
		while(!player.isEmpty() && !isPosible)
		{
			// Hacer bfs sobre los monstruos
			possibilities = monsters.size();
			
			for(int k=0; k<possibilities; ++k)
			{
				Pair tmp = monsters.remove();
				
				// Check for adyacent vertices
				for(int i=0; i<4; ++i) 
				{
					int dv = tmp.row+v[i];
					int dh = tmp.col+h[i];
					
					if(dv >= 0 && dv < n && dh >= 0 && dh < m && mat[dv][dh] != '#')
					{
						monsters.add(new Pair(dv, dh));
						mat[dv][dh] = '#';
					}
				}
			}
			
			
			// Hacer bfs sobre el jugador
			possibilities = player.size();
			for(int k=0; k<possibilities; ++k)
			{
				Pair tmp = player.remove();
				
				if(tmp.row == 0 || tmp.row == n-1 || tmp.col == 0 || tmp.col == m-1)
				{
					isPosible = true;
					break;
				}
				
				for(int i=0; i<4; ++i)
				{
					int dv = tmp.row+v[i];
					int dh = tmp.col+h[i];
					
					if(dv >= 0 && dv < n && dh >= 0 && dh < m && mat[dv][dh] != '#' && mat[dv][dh] != 'P')
					{
						if(dv == 0 || dh == 0 || dv == n-1 || dh == m-1)
						{
							isPosible = true;
							row_end = dv;
							col_end = dh;
						}
						
						// Chechar como se llego ahi
						if(i == 0) par[dv][dh] = 'U';
						if(i == 1) par[dv][dh] = 'R';
						if(i == 2) par[dv][dh] = 'D';
						if(i == 3) par[dv][dh] = 'L';
						player.add(new Pair(dv, dh));
						dis[dv][dh] = dis[tmp.row][tmp.col] + 1;
						mat[dv][dh] = 'P';
					}
				}
			}
			
//			System.out.println("Second: " + second);
//			print(mat, n, m);
//			second++;
			
		}
		
		if(isPosible) 
		{
			int distance = dis[row_end][col_end];
			out.println("YES");
			out.println(distance);
			
			int row = row_end, col = col_end;
			for(int i=0; i<distance; ++i)
			{
				ans[i] = par[row][col];
				if(par[row][col] == 'U') row++;
				else if(par[row][col] == 'R') col--;
				else if(par[row][col] == 'D') row--;
				else if(par[row][col] == 'L') col++;
			}
			
			for(int i=distance-1; i>=0; --i)
			{
				out.print(ans[i]);
			}
			out.println();
			out.close();
			
		} else 
		{
			out.println("NO");
			out.close();
		}
	}
	
	static class Pair
	{
		int row, col;
		
		public Pair() {};
		
		public Pair(int row, int col)
		{
			this.row = row;
			this.col = col;
		}
	}
	
	static void print(char[][] arr, int n, int m)
	{
		for(int i=0; i<n; ++i)
		{
			for(int j=0; j<m; ++j)
			{
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
	}
}
