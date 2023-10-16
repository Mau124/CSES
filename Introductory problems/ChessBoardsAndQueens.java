import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ChessBoardsAndQueens {

	static char[][] board = new char[8][];
	static Position[] pos = new Position[8];
	static int ans = 0;
	
	public static void main(String[] args) {
		FastScanner fs = new FastScanner();
		
		for(int i=0; i<8; ++i) {
			board[i] = fs.next().toCharArray();
			pos[i] = new Position();
		}
		
//		System.out.println("Print Matrix");
//		printMatrix(board);
		
		numWays(0);
		System.out.println(ans);
//		System.out.println(ans);
	}
	
	static void numWays(int queens) {
//		System.out.println("Enter with queens: " + queens);
		if(queens >= 8) {
//			System.out.println("Bigger than 8");
			ans++;
			return;
		}
		
		for(int col=0; col<8; ++col) {
			if(board[queens][col] == '.') {
				pos[queens].x = col;
				pos[queens].y = queens;
				
				if(validate(queens)) {
					numWays(queens+1);
				}
				
			}
		}
		
	}
	
	static boolean validate(int queens) { 
		for(int i=0; i<queens; ++i) {
			if(pos[i].x == pos[queens].x)
				return false;
			
			if(pos[i].y == pos[queens].y)
				return false;
			
			if(Math.abs(pos[i].x - pos[queens].x) == Math.abs(pos[i].y - pos[queens].y))
				return false;
		}
		return true;
	}
	
	static class Position {
		
		int x, y;
		
		Position() {
			x = 0;
			y = 0;
		}
		
		Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
	
	static void printMatrix(char[][] mat) {
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
	}
}
