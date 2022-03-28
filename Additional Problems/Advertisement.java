import java.util.Scanner;
import java.util.Stack;

public class Advertisement {
	public static void main(String[] args) 
	{
		Scanner s = new Scanner(System.in);
		
		int n = s.nextInt();
		Long[] v = new Long[n];
		Stack<Pair> S = new Stack<>();
		
		for(int i=0; i<n; ++i)
		{
			v[i] = s.nextLong();
		}
		
		long ans = 0;
		S.push(new Pair(v[0], (long)1));
		for(int i=1; i<n; ++i)
		{
			if(v[i] > S.peek().first)
			{
				S.push(new Pair(v[i], (long)1));
			} else 
			{
				long total = 0;
				while(!S.empty() && v[i] <= S.peek().first) {
					total += S.peek().second;
					
					ans = Math.max(ans, S.peek().first*total);
					
					S.pop();
				}
				
				S.push(new Pair(v[i], total+1));
			}
		}
		
		long total = 0;
		while(!S.empty()) {
			total += S.peek().second;
			
			ans = Math.max(ans, S.peek().first*total);
			
			S.pop();
		}
		
		System.out.println(ans);
		
		s.close();
	}
	
	static class Pair 
	{
		Long first, second;
		
		Pair(Long first, Long second)
		{
			this.first = first;
			this.second = second;
		}
	}
}
