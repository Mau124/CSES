import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.ArrayList;

public class SumOfThreeValues {
	
	public static void main(String[] args) {
		FastScanner fs = new FastScanner();
		int n = fs.nextInt();
		long target = fs.nextLong();
		Pair[] arr = new Pair[n];
		for(int i=0; i<n; ++i) {
			arr[i] = new Pair(i, fs.nextLong());
		}
		
		// Sort the array
		// 1 2 3
		//   i
		// l
		//   r
		// need = 3
		Arrays.sort(arr, (p1, p2) -> Long.compare(p1.value, p2.value));
		for(int i=0; i<n; ++i) {
			long pivot = arr[i].value, need = target - pivot;
			int l=0, r=arr.length-1;
			while(l < r) {
				if(l == i) {
					l++;
					continue;
				}
				if(r == i) {
					r--;
					continue;
				}
				if(arr[l].value + arr[r].value > need) r--;
				else if(arr[l].value + arr[r].value < need) l++;
				else if(arr[l].value + arr[r].value == need) break;
			}
			if(l < r && arr[l].value + arr[r].value + pivot == target) {
				System.out.println((arr[l].index+1) + " " + (arr[r].index+1) + " " + (arr[i].index+1));
				return;
			}
		}
		System.out.println("IMPOSSIBLE");
	} 
	
	static class Pair {
		int index;
		long value;
		Pair(int index, long value) { this.index = index; this.value = value;}
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
