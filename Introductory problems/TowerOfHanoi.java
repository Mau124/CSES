import java.util.Scanner;
import java.util.ArrayList;

public class TowerOfHanoi {

	static int times = 0;
	static ArrayList<Pair> moves = new ArrayList<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		hanoi(1, 3, n);
		System.out.println(times);
		moves.forEach(m -> System.out.println(m.source + " " + m.target));
		sc.close();
	}
	
	static class Pair {
		int source, target;
		
		Pair(int source, int target) {
			this.source = source;
			this.target = target;
		}
	}
	
	static void hanoi(int source, int target, int discs) {
		if(discs == 0)
			return;
		int aux = 0;
		if((source == 1 && target == 2) || (source == 2 && target == 1)) aux = 3;
		if((source == 1 && target == 3) || (source == 3 && target == 1)) aux = 2;
		if((source == 2 && target == 3) || (source == 3 && target == 2)) aux = 1;
		hanoi(source, aux, discs-1);
		// System.out.println(source + " " + target);
		moves.add(new Pair(source, target));
		times++;
		hanoi(aux, target, discs-1);
	}
}
