import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.Map.Entry;
 
public class PlayList {
	
	static final int MOD = 1_000_000_007;
	
	public static void main(String[] args) {
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		
		int n = fs.nextInt();
        HashMap<Integer, Integer> songs = new HashMap<>();
        int ans = 0, acc = 0;
		for(int i=0; i<n; ++i) {
            int aux = fs.nextInt();
            if(songs.containsKey(aux)) {
                ans = Math.max(ans, acc);
                if(i - songs.get(aux) <= 0) {
                    acc = 1;
                } else {
                    acc = Math.min(i - songs.get(aux), acc + 1);
                }
                songs.put(aux, i);
            } else {
                songs.put(aux, i);
                acc++;
            }
            // System.out.println("Value of acc: " + acc);
        }
        // out.println("Values of the map");
        // for( Entry<Integer, Integer> entries : songs.entrySet()) {
        //     out.println("Key: " + entries.getKey() + "Value: " + entries.getValue());
        // }
        out.println(Math.max(ans, acc));
		out.close();
	}
	
	static long fastPow(int b, int e, int mod) {
		if(e == 0)
			return 1;
		long aux = fastPow(b, e/2, mod);
		if(e%2 == 0) {
			return ( aux * aux) % mod;
		} else {
			return ((aux * aux) % mod * b) % mod;
		}
	}
	
	static class DSU {
		int size;
		int[] p, r, sum, min, max, experience;
		
		DSU(int size) {
			this.size = size;
			p = new int[size];
			r = new int[size];
			sum = new int[size];
			min = new int[size];
			max = new int[size];
			experience = new int[size];
			for(int i=0; i<size; ++i) {
				p[i] = i;
				r[i] = 1;
				sum[i] = 1;
				min[i] = i;
				max[i] = i;
				experience[i] = 0;
			}
		}
		
		// Returns if nodeA and nodeB belongs to the same disjoint set
		boolean find(int nodeA, int nodeB) {
			return (get(nodeA) == get(nodeB));
		}
		
		// O(alpha(m, n)) -> Ackermann function, almost O(1)
		// Path compression
		int get(int node) {
			if(p[node] != node)
				p[node] = get(p[node]);
			return p[node];	
		}
		
		// O(alpha(m, n)) -> Ackermann function, almost O(1)
		// Rank heuristic to union the sets
		// Always join smaller to larger
		void union(int nodeA, int nodeB) {
			nodeA = get(nodeA);
			nodeB = get(nodeB);
			
			if(nodeA == nodeB) 
				return;
			
			if(r[nodeA] == r[nodeB]) {
				r[nodeA]++;
			}
			if(r[nodeA] > r[nodeB]) {
				p[nodeB] = p[nodeA];
				sum[nodeA] += sum[nodeB];
				min[nodeA] = Math.min(min[nodeA], min[nodeB]);
				max[nodeA] = Math.max(max[nodeA], max[nodeB]);
			} else {
				p[nodeA] = nodeB;
				sum[nodeB] += sum[nodeA];
				min[nodeB] = Math.min(min[nodeA], min[nodeB]);
				max[nodeB] = Math.max(max[nodeA], max[nodeB]);
			}
		}
		
		// Returns the minimum value in the set where nodeA is
		int getMin(int nodeA) {
			nodeA = get(nodeA);
			return min[nodeA];
		}
		
		// Returns the maximum value in the set where nodeA is
		int getMax(int nodeA) {
			nodeA = get(nodeA);
			return max[nodeA];
		}
		
		// Return the number of elements in the set where nodeA is
		int getElements(int nodeA) {
			nodeA = get(nodeA);
			return sum[nodeA];
		}
		
		// Add experience to each player on group
		void add(int nodeA, int exp) {
			nodeA = get(nodeA);
			experience[nodeA] += exp;
		}
		
		// Return the experience of player p
		int getExperience(int nodeA) {
			nodeA = get(nodeA);
			return experience[nodeA];
		}
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
		
		public long nextLong() {
			return Long.parseLong(next());
		}
	}
}