import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.PrintWriter;

public class StaticRangeSumQueries {
    public static void main(String[] args) {
        FastScanner s = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);
        int i, l, r;

        int n = s.nextInt();
        int q = s.nextInt();
        int[] arr = new int[n+1];
        long[] prefix = new long[n+1];

        arr[1] = s.nextInt();
        prefix[1] = arr[1];

        for(i=2; i<=n; ++i) {
            arr[i] = s.nextInt();
            prefix[i] = prefix[i-1] + arr[i];
        }

        for(i=0; i<q; ++i) {
            l = s.nextInt();
            r = s.nextInt(); 
            out.println(prefix[r] - prefix[l-1]);
        }

        out.flush();
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

        int nextInt() {
            return Integer.parseInt(next());
        }

    }
}