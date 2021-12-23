import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class KnightsTour {

    private final static int[][] access = {{2, 3, 4, 4, 4, 4, 3, 2},
                                           {3, 4, 6, 6, 6, 6, 4, 3},
                                           {4, 6, 8, 8, 8, 8, 6, 4},
                                           {4, 6, 8, 8, 8, 8, 6, 4},
                                           {4, 6, 8, 8, 8, 8, 6, 4},
                                           {4, 6, 8, 8, 8, 8, 6, 4},
                                           {3, 4, 6, 6, 6, 6, 4, 3},
                                           {2, 3, 4, 4, 4, 4, 3, 2}};

    private final static int[] horizontal = {1, 2, 2, 1, -1, -2, -2, -1};
    private final static int[] vertical = {2, 1, -1, -2, -2, -1, 1, 2};
    private final static int n = 8, m = 8;
    private static int[][] ans = new int[n][m];

    private static boolean found = false;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int col = s.nextInt(); col--;
        int row = s.nextInt();  row--;
        int it = 1;

        ans[row][col] = it;

        dfs(row, col, it);

        for(int i=0; i<n; ++i) {
            for(int j=0; j<m; ++j) {
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void dfs(int row, int col, int it) {
        ArrayList<Element> elements = new ArrayList<Element>();
        if(found)
            return;

        ans[row][col] = it;

        if(it == n*m) { 
            found = true;
            return;
        }

        for(int i=0; i<horizontal.length; ++i) {
            int nRow = row + vertical[i];
            int nCol = col + horizontal[i];

            if(nRow < 0 || nRow >= n || nCol < 0 || nCol >= m || ans[nRow][nCol] != 0)
                continue;

            elements.add(new Element(access[nRow][nCol], nRow, nCol));
        }
        Collections.sort(elements);
        for(int i=0; i<elements.size(); ++i) {
            dfs(elements.get(i).row, elements.get(i).col, it+1);
        }

        if(!found)
            ans[row][col] = 0;
    }

    public static class Element implements Comparable<Element> {
        public int weight;
        public int row;
        public int col;
    
        public Element(int weight, int row, int col) {
            this.weight = weight;
            this.row = row;
            this.col = col;
        }
    
        @Override
        public int compareTo(Element el) {
            return this.weight - el.weight;
        }
    }
}
