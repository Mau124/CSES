import java.util.Scanner;

public class EditDistance {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int i, j, rows, cols;

        char[] s1 = s.nextLine().toCharArray();
        char[] s2 = s.nextLine().toCharArray();

        rows = s1.length;
        cols = s2.length;

        int[][] editDistances = new int[rows+1][cols+1];

        for(i=1; i<=rows; ++i) 
            editDistances[i][0] = i;

        for(j=1; j<=cols; ++j) 
            editDistances[0][j] = j;
        
        for(i=1; i<=rows; ++i) {
            for(j=1; j<=cols; ++j) {
                if(s1[i-1] == s2[j-1]) {
                    // System.out.println("Row: " + i + " Value: " +  text2[i-1] + " Column: " +  j + " Value: " + text1[j-1]);
                    editDistances[i][j] = Math.min(Math.min(editDistances[i][j-1] + 1, editDistances[i-1][j-1]), editDistances[i-1][j] + 1);
                }
                else
                    editDistances[i][j] = Math.min(Math.min(editDistances[i][j-1] + 1, editDistances[i-1][j-1] + 1), editDistances[i-1][j] + 1);
            }
        } 


        // for(i=0; i<=rows; ++i) {
        //     for(j=0; j<=cols; ++j) {
        //         System.out.print(editDistances[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        System.out.println(editDistances[rows][cols]);
    }
}
