import java.util.Scanner;

public class AppleDivision {
	
	static long sumA = 0, sumB = 0;
	static long ans = Integer.MAX_VALUE;
	
	static void combinations(int[] arr, int it)
	{
		if(it == arr.length)
		{
			ans = Math.min(ans, Math.abs(sumA - sumB));
			return;
		}
		
		for(int i=0; i<2; ++i)
		{
			if(i == 0)
			{
				sumA += arr[it];
				combinations(arr, it+1);
				sumA -= arr[it];
			} else 
			{
				sumB += arr[it];
				combinations(arr, it+1);
				sumB -= arr[it];
			}
		}
	}
	
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		
		int n = s.nextInt();
		int[] arr = new int[n];
		
		for(int i=0; i<n; ++i)
			arr[i] = s.nextInt();
		
		combinations(arr, 0);
		
		System.out.println(ans);
		
		s.close();
	}
}
