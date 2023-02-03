import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Math;

public class NumberSpiral {

	public static void main(String[] args)
	{
		FastScanner s = new FastScanner();
		int t = s.nextInt();
		
		for(int tt=0; tt<t; ++tt) {
			System.out.println(solve(s));
		}

	}
	
	public static long solve(FastScanner s) {
		long y = s.nextInt(), x = s.nextInt();
		
		long diagonal, difference = Math.abs(x - y);
		if(x > y) {
			diagonal = 1 + (x*(x-1));
			if(x%2 == 0) {
				diagonal-=difference;
			} else {
				diagonal+=difference;
			}
		} else {
			diagonal = 1 + (y*(y-1));
			if(y%2 == 1) {
				diagonal-=difference;
			} else {
				diagonal+=difference;
			}
		}
		
		return diagonal;
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
