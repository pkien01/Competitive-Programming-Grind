/*input
2
400
1 1 1 1
1 1 1 1
1 1 1 1
1 1 1 1
1 1 1 1
1 1 1 1
1 1 1 1
1 1 1 1
1 9 9 1
1 -9 -9 1
1000
9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9
7 7 7 7 7 7 7 7 7 7
-5 -5 -5 -5 -5 -5 -5 -5 -5 -5
-7 -3 -7 -7 -7 -7 -7 -7 -7 -7
-9 -9 -9 -9 -9 -9 -9 -9 -9 -9
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
    static final int inf = (int)1e8;

   	static int n;
   	static int[][] winds;

   	static int solve() {
   		int[] dp = new int[10];
   		Arrays.fill(dp, inf);
   		dp[0] = 0;
   		for (int i = n - 1; i >= 0; i--) {
   			int[] next_dp = new int[10];
   			Arrays.fill(next_dp, inf);
   			for (int j = 0; j <= 9; j++) {
   				int resDp = dp[j] + 30 - winds[i][j];
   				if (j < 9) resDp = Math.min(resDp, dp[j + 1] + 60 - winds[i][j]);
   				if (j > 0) resDp = Math.min(resDp, dp[j - 1] + 20 - winds[i][j]);
   				next_dp[j] = resDp;
   			}
   			dp = next_dp.clone();
   			//System.out.println(Arrays.toString(dp));
   		}
   		return dp[0];
   	}
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	n = inp.nextInt() / 100;
        	//System.out.println(n);
        	winds = new int[n][10];
        	for (int j = 9; j >= 0; j--) 
        		for (int i = 0; i < n; i++) winds[i][j] = inp.nextInt();

        	System.out.println(solve() + "\n");
	    }
        
        //out.close();
    }
}