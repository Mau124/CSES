package SortingAndSearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.HashMap;

public class ReadingBooks {
	
	static FastScanner fs = new FastScanner();
	static PrintWriter out = new PrintWriter(System.out);
	
	static final int MOD = 1_000_000_007;
	static final long INF = (long) 1e17;
	static final long NINF = -INF;
	
	public static void main(String[] args) {
		long n = fs.nextInt(), sum = 0, greatest = 0;
		for(int i=0; i<n; ++i) {
			int val = fs.nextInt();
			greatest = Math.max(greatest, val);
			sum += val;
		}
		
		out.println(Math.max(sum, 2*greatest));
		
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
	
	static class Trie {
		Trie[] children = new Trie[26];
		boolean isEndOfWord;
		
		Trie() {
			isEndOfWord = false;
			for(int i=0; i<26; ++i) {
				children[i] = null;
			}
		}
		
		void insert(String word) {
			char[] arr = word.toCharArray();
			Trie currentNode = this;
			for(int i=0; i<arr.length; ++i) {
				if(currentNode.children[arr[i]-'a'] == null)
					currentNode.children[arr[i]-'a'] = new Trie();
				currentNode = currentNode.children[arr[i]-'a'];
			}
			currentNode.isEndOfWord = true;
		}
			
		boolean search(String word) {
			char[] arr = word.toCharArray();
			Trie currentNode = this;
			for(int i=0; i<arr.length; ++i) {
				if(currentNode.children[arr[i]-'a'] == null) 
					return false;
				currentNode = currentNode.children[arr[i]-'a'];
			}

			return currentNode.isEndOfWord;
		}
	}
	
	static class Edge {
		int v, u;
		long distance;
		
		Edge(int v, int u, long distance) {
			this.v = v;
			this.u = u;
			this.distance = distance;
		}
	}
	
	static long[] BellmanFord(int nodes, ArrayList<Edge> edges) {
		long[] score = new long[nodes+1];
		Arrays.fill(score, INF);
		score[1] = 0;
		for(int i=0; i<nodes; ++i) {
			for(Edge edge: edges) {
				if(score[edge.v] == INF) continue;
				score[edge.u] = Math.min(score[edge.u], score[edge.v] + edge.distance);
			}
		}
		
		for(int i=0; i<nodes; ++i) {
			for(Edge edge: edges) {
				if(score[edge.v] == INF) continue;
				if(score[edge.v] + edge.distance < score[edge.u]) {
					score[edge.u] = NINF;
				}
			}
		}
		
		return score;
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
