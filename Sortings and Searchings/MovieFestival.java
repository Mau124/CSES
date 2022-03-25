import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class MovieFestival {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		int n = s.nextInt();
		ArrayList<Pair> movies = new ArrayList<>();
		Stack<Pair> stk = new Stack<>();
		
		for(int i=0; i<n; ++i) 
		{		
			int a = s.nextInt(), b = s.nextInt();
			
			movies.add(new Pair(a, b));
		}
		
		Collections.sort(movies);
		
		stk.add(movies.get(0));
		for(int i=0; i<n; ++i) 
		{
			if(movies.get(i).first >= stk.peek().second)
			{
				stk.add(movies.get(i));
			} else 
			{
				if(movies.get(i).second < stk.peek().second)
				{
					stk.pop();
					stk.add(movies.get(i));
				}
			}
		}
		
		System.out.println(stk.size());
		
		s.close();
	}
	
	static class Pair implements Comparable<Pair>
	{
		int first, second;
		
		Pair(int first, int second) 
		{
			this.first = first;
			this.second = second;
		}
		
		public int compareTo(Pair p) 
		{
			return this.first - p.first;
		}
	}
}
