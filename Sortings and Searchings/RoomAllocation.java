import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.HashMap;

public class RoomAllocation {

	static int[] roomAssigned = new int[200001];
	
	public static void main(String[] args) {
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		
		int customers = fs.nextInt();
		Queue<Pair> departureTimes = new PriorityQueue<>((p1, p2) -> Integer.compare(p1.departureTime, p2.departureTime));
		Time[] times = new Time[customers];
		for(int cc=0; cc<customers; ++cc) {
			int arrival = fs.nextInt(), departure = fs.nextInt();
			times[cc] = new Time(cc, arrival, departure);
		}
//		System.err.println(times.length);
		Arrays.sort(times, (t1, t2) -> Integer.compare(t1.arrival, t2.arrival) );
		
		int room = 1;
		for(int i=0; i<times.length; ++i) {
			if(!departureTimes.isEmpty() && times[i].arrival > departureTimes.peek().departureTime) {
				roomAssigned[times[i].customer] = departureTimes.poll().roomAssigned;
			} else {
				roomAssigned[times[i].customer] = room;
				room++;
			}
			departureTimes.add(new Pair(times[i].departure, roomAssigned[times[i].customer]));
		}
		
		out.println(room-1);
		for(int i=0; i<customers; ++i) {
			out.println(roomAssigned[i]);
		}
		out.close();
	}
	
	static class Time {
		int customer, arrival, departure;
		Time(int customer, int arrival, int departure) {
			this.customer = customer;
			this.arrival = arrival;
			this.departure = departure;
		}
	}
	
	static class Pair {
		int departureTime, roomAssigned;
		Pair(int departureTime, int roomAssigned) {
			this.departureTime = departureTime;
			this.roomAssigned = roomAssigned;
		}
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
