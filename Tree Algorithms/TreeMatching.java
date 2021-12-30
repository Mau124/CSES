import java.util.Scanner;
import java.util.ArrayList;

public class TreeMatching {

    private static final int N = 2_00_001;

    static int[] dp1 = new int[N];
    static int[] dp2 = new int[N];
    static Node[] adj = new Node[N];
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int i, j, a, b;

        int  n = s.nextInt();

        for(i=1; i<=n; ++i) 
            adj[i] = new Node();

        for(i=0; i<n-1; ++i) {
            a = s.nextInt();
            b = s.nextInt();

            adj[a].add(b);
            adj[b].add(a);
        }

        dfs(1, 0);

        System.out.println(Math.max(dp1[1], dp2[1]));
    }

    static class Node {
        ArrayList<Integer> nodes;
        boolean visited;

        public Node() {
            visited = false;
            nodes = new ArrayList<>();
        }

        public void add(int vertex) {
            nodes.add(vertex);
        }

        public int get(int index) {
            return nodes.get(index);
        }
    }

    static void dfs(int v, int p) {
        adj[v].visited = true;

        for(int u: adj[v].nodes) {
            if(!adj[u].visited) {
                dfs(u, v);
            }
        }

        int sum = 0;
        for(int u: adj[v].nodes) 
            sum += Math.max(dp1[u], dp2[u]);

        dp2[v] = sum;
        for(int u: adj[v].nodes) {
            if(u != p) 
                dp1[v] = Math.max(dp1[v], dp2[u] + 1 + dp2[v] - Math.max(dp1[u], dp2[u]));
        }
    }
}
