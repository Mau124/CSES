import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter; 
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Collection;

public class Towers {
	
	public static void main(String[] args) {
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		
		int n = fs.nextInt();
		MultiTreeSet<Integer> towers = new MultiTreeSet<>();
		for(int i=0; i<n; ++i) {
			int cube = fs.nextInt();
			Integer closest = towers.higher(cube);
			if(closest != null)
				towers.remove(closest);
			towers.add(cube);
		}
		out.println(towers.size());
		
		out.close();
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
	
	// MultiTreeSet
	// This implementation is from tmwilliamlin168
	// https://ideone.com/IxJk23
	static class MultiTreeSet<E> {
		TreeMap<E, Integer> freqTreeMap = new TreeMap<E, Integer>();
		int size;
	 
		public MultiTreeSet() {}
	 
		public MultiTreeSet(Collection<? extends E> c) {
			for(E element : c)
				add(element);
		}
	 
		public int size() {
			return size;
		}
	 
		public void add(E element) {
			Integer freq = freqTreeMap.get(element);
			if(freq==null)
				freqTreeMap.put(element, 1);
			else
				freqTreeMap.put(element,freq+1);
			++size;
		}
	 
		public void remove(E element) {
			Integer freq = freqTreeMap.get(element);
			if(freq!=null) {
				if(freq==1)
					freqTreeMap.remove(element);
				else
					freqTreeMap.put(element, freq-1);
				--size;
			}
		}
	 
		public int get(E element) {
			Integer freq = freqTreeMap.get(element);
			if(freq==null)
				return 0;
			return freq;
		}
	 
		public boolean contains(E element) {
			return get(element)>0;
		}
	 
		public boolean isEmpty() {
			return size==0;
		}
	 
		public E first() {
			return freqTreeMap.firstKey();
		}
	 
		public E last() {
			return freqTreeMap.lastKey();
		}
	 
		public E ceiling(E element) {
			return freqTreeMap.ceilingKey(element);
		}
	 
		public E floor(E element) {
			return freqTreeMap.floorKey(element);
		}
	 
		public E higher(E element) {
			return freqTreeMap.higherKey(element);
		}
	 
		public E lower(E element) {
			return freqTreeMap.lowerKey(element);
		}
	}
	
}
