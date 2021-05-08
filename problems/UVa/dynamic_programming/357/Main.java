/*input
29999
1
4
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

    static long[][] dp;
    static final int[] coins = {1, 5, 10, 25, 50};
    static void precompute() {
    	dp = new long[30001][coins.length + 1];
    	Arrays.fill(dp[0], 1);
    	for (int i = 1; i <= 30000; i++) {
    		for (int j = 1; j <= coins.length; j++) 
    			dp[i][j] += (i >= coins[j - 1]? dp[i - coins[j - 1]][j] : 0) + dp[i][j - 1];;
    	}
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        precompute();
        while (inp.hasNextLine()) {
        	int n = Integer.parseInt(inp.nextLine());
        	long ans =  dp[n][coins.length];
        	if (ans == 1) System.out.format("There is only %d way to produce %d cents change.\n", ans, n);
        	else System.out.format("There are %d ways to produce %d cents change.\n", ans, n);
        }
        
        //out.close();
    }
}