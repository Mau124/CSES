import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class CreatingStrings {
	
	public static void main(String[] args) {
		FastScanner fs = new FastScanner();
		char[] s = fs.next().toCharArray();
		Arrays.sort(s);
		int permutations = 0;
		do {
			permutations++;
		} while(nextPermutation(s));
		System.out.println(permutations);
		do {
			System.out.println(s);
		} while(nextPermutation(s));
	}

	static boolean nextPermutation(char[] arr) {
		int i=arr.length-1;
		while(i > 0 && arr[i-1] >= arr[i]) {
			i--;
		}
		
		if(i == 0) {
			reverse(arr, 0, arr.length-1);
			return false;
		}
		
		int itUpperBound = upperBound(arr, arr[i-1], i, arr.length-1, true);
		swap(arr, i-1, itUpperBound);
		reverse(arr, i, arr.length-1);
		
		return true;
	}
	
	/**
	 * Returns the index of the first value that is greater than key
	 * @param arr -> Array
	 * @param key -> Value to search in the array
	 * @param i -> left bound
	 * @param j -> right bound
	 * @param reverse -> If the algorithm will search in reverse order or not
	 * @retur Index of the first value that is greater than key
	 */
	static int upperBound(char[] arr, int key, int i, int j, boolean reverse) {
		int l = i, r = j, ans = -1;
		while(l<=r) {
			int m = l + (r-l)/2;
			if(arr[m] > key) {
				if(reverse) {
					ans = m;
					l = m+1;
				} else {
					ans = m;
					r = m-1;
				}
			} else {
				if(reverse) {
					r = m-1;
				} else {
					l = m+1;
				}
			}
		}
		return ans;
	}
	
	/**
	 * Reverse the array
	 * @param arr
	 * @param i
	 * @param j
	 */
	static void reverse(char[] arr, int i, int j) { 
		while(i < j) {
			char tmp = arr[j];
			arr[j] = arr[i];
			arr[i] = tmp;
			i++;
			j--;
		}
	}
	
	/**
	 * Swaps two values of an array
	 * @param arr
	 * @param i
	 * @param j
	 */
	static void swap(char[] arr, int i, int j) {
		char tmp = arr[j];
		arr[j] = arr[i];
		arr[i] = tmp;
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
