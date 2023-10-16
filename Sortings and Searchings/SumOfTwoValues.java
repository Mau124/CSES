import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class SumOfTwoValues {

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();
		int n = fs.nextInt(), target = fs.nextInt();
		int[] arr = new int[n];
		for(int i=0; i<n; ++i) {
			arr[i] = fs.nextInt();
		}
		
		int[] aux = Arrays.copyOf(arr, arr.length);
		int index1 = -1, index2 = -1;
		Arrays.sort(arr);
		for(int i=0; i<n; ++i) {
			if(arr[i] >= target) {
				System.out.println("IMPOSSIBLE");
				return;
			}
			
			int distance = target-arr[i];
			if(distance > arr[i] && i < arr.length) {
				int index = Arrays.binarySearch(arr, i+1, arr.length, distance);
				if(index >= 0) {
					index1 = index;
					index2 = i;
					break;
				}
			} else if(distance < arr[i] && i > 0) {
				int index = Arrays.binarySearch(arr, 0, i, distance);
				if(index >= 0) {
					index1 = index;
					index2 = i;
					break;
				}
			} else{
				if(i < arr.length) { 
					int index = Arrays.binarySearch(arr, i+1, arr.length, distance);
					if(index >= 0) {
						index1 = index;
						index2 = i;
						break;
					}
				}
				if(i > 0) {
					int index = Arrays.binarySearch(arr, 0, i, distance);
					if(index >= 0) {
						index1 = index;
						index2 = i;
						break;
					}
				}
			}
			
		}
		
		if(index1 >= 0) {
			for(int i=0; i<n; ++i) {
				if(index1 >= 0 && arr[index1] == aux[i]) {
					System.out.print((i+1) + " ");
					index1 = -1;
					continue;
				}
				if(index2 >= 0 && arr[index2] == aux[i]) {
					System.out.print((i+1) + " ");
					index2 = -1;
				}
			}
			return;
		}
		
		System.out.println("IMPOSSIBLE");
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
