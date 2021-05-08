/*input
30
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
    static int[][] dp;
    static int solve(int idx, int prod) {
    	if (idx == n || prod == 0) return prod == 1? 0: -inf;
    	if (dp[idx][prod] != -1) return dp[idx][prod];
    	System.out.println(idx + " " + prod);
    	return Math.max(solve(idx + 1, (prod * idx) % n) + 1, solve(idx + 1, prod));
    }


    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);

        n = inp.nextInt();
        dp = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1);
    	System.out.println(solve(1, 1));
    }
}