/*input
Sample001
3 2
1 2
3 4
5 6
7 8
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

    static final int inf = (int)2e8;
    static int n, m;
    static int[][][] cost;

    static int[][] dp;
    static int solve(int floor, int hole) {
    	if (floor == n - 1) return 0;
    	if (dp[floor][hole] != -1) return dp[floor][hole];
    	int res = inf;
    	for (int nextHole = 0; nextHole < m; nextHole++) 
    		res = Math.min(res,  solve(floor + 1, nextHole) + cost[floor][hole][nextHole] + 2);
    	dp[floor][hole] = res;
    	//System.out.println(floor + " " + hole + " " + res);
    	return res;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (inp.hasNext()) {
        	String name = inp.next();
        	n = inp.nextInt(); m = inp.nextInt();
        	cost = new int[n - 1][m][m];
        	for (int k = 0; k < n - 1; k++) 
        		for (int i = 0; i < m; i++) 
        			for (int j = 0; j < m; j++) cost[k][i][j] = inp.nextInt();
        	
        	dp = new int[n][m];
        	for (int k = 0; k < n; k++) Arrays.fill(dp[k], -1);
        	int ans = inf;
        	for (int i = 0; i < m; i++) ans = Math.min(ans, solve(0, i));
        	System.out.println(name + "\n" + ans);
        }
        
        //out.close();
    }
}