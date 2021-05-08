/*input
4 3
7
2
6
4
5
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

    static final int inf = (int)1e9;
    static int n, k;
    static int[] dist;

    static int solve() {
    	for (int i = 1; i <= n; i++) dist[i] += dist[i - 1];
    	int[][] dp = new int[n + 1][k + 1];
    	for (int pos = 0; pos <= n; pos++) {
    		for (int nights = 0; nights <= k; nights++) {
    			if (nights > 0) {
    				dp[pos][nights] = inf;
    				for (int prev = pos; prev >= 0; prev--) 
    					dp[pos][nights] = Math.min(dp[pos][nights], Math.max(dp[prev][nights - 1], dist[pos] - dist[prev]));
    			}
    			else dp[pos][nights] = dist[pos];
    		}
    	}
    	return dp[n][k];
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (inp.hasNextInt()) {
        	n = inp.nextInt(); k = inp.nextInt();
        	dist = new int[n + 1];
        	for (int i = 0; i <= n; i++) dist[i] = inp.nextInt();
        	System.out.println(solve());
        }	
        
        //out.close();
    }
}