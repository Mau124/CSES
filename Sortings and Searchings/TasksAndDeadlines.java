import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Arrays;

public class TasksAndDeadlines {
	
	public static void main(String[] args) {
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		
		int n = fs.nextInt();
		Task[] tasks = new Task[n];
		for(int i=0; i<n; ++i) {
			tasks[i] = new Task(fs.nextInt(), fs.nextInt());
		}
		
		Arrays.sort(tasks, (t1, t2) -> Integer.compare(t1.duration, t2.duration));
		long ans = 0, time = 0;
		for(int i=0; i<n; ++i) {
			time += tasks[i].duration;
			ans += (tasks[i].deadline - time);
		}
		
		System.out.println(ans);
		
		out.close();
	}
	
	static class Task {
		int duration, deadline;
		Task(int duration, int deadline) { this.duration = duration; this.deadline = deadline; }
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
