/*input
3
2
10 10
-5 -5
2
10 -5
10 -5
2
10 -5
-5 10
*/
import java.util.*;
import java.io.*;

public class Main {
    static void runFromFile() {
        final String IODir = "D:/Kien/competitive_programming/io";
        final File inputFile = new File(IODir + "/input.txt");
        final File outputFile = new File(IODir + "/output.txt");
        try {
            System.setIn(new FileInputStream(inputFile));
            System.setOut(new PrintStream(outputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
  
  	static boolean nextPermutation(int[] perm) {
    	for (int i = perm.length - 2; i >= 0; i--) {
    		if (perm[i] < perm[i + 1]) {
    			int upperBound = -1;
    			for (int j = perm.length - 1; j > i; j--) {
    				if (perm[j] > perm[i]) {
    					upperBound = j; break;
    				}
    			}
    			if (upperBound != -1) {
    				int tmp = perm[i]; perm[i] = perm[upperBound]; perm[upperBound] = tmp;
    				for (int l = i + 1, r = perm.length - 1; l < r; l++, r--) {
    					tmp = perm[l]; perm[l] = perm[r]; perm[r] = tmp;
    				}
    				return true;
    			}
    		}
    	}
    	return false;
    }

    static int n;
    static int[][] arr;

    static int solve() {
    	int[] moves = new int[n];
        for (int i = 0; i < n; i++) moves[i] = i;
        int res = Integer.MAX_VALUE;
        do {
            int sum = 0;
        	for (int i = 0; i < n; i++) {
        		sum += arr[i][moves[i]];
        	}
            //System.out.println(Arrays.toString(moves));
            res = Math.min(res, sum); 
        } while (nextPermutation(moves));

        return res;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	n = inp.nextInt();
        	arr = new int[n][n];
        	for (int i = 0; i < n; i++)
        		for (int j = 0; j < n; j++) arr[i][j] = inp.nextInt();
        	System.out.println(solve());
        }
    }
}