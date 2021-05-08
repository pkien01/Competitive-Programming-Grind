/*input
4 1
1 2 3 -5
-10 6 0 -1
-10 -10 -10 2
0 0 0 1
4 0
1 2 3 -5
-10 6 0 -1
-10 -10 -10 2
0 0 0 1
0 0
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
    static int n, k;
    static int[][] arr;

    static int[][][] dp;
    static boolean[][][] vis;
    static int solve(int x, int y, int negs) {
    	if (vis[x][y][negs]) return dp[x][y][negs];
    	vis[x][y][negs] = true;
    	if (x == n - 1) {
    		int next_negs = negs, sum = 0;
    		for (int next_y = y; next_y < n; next_y++) {
    			next_negs += arr[x][next_y] < 0 ? 1 : 0;
    			if (next_negs > k) {
    				dp[x][y][negs] = -inf;
    				return -inf;
    			}
    			sum += arr[x][next_y];
    		}
    		return dp[x][y][negs] = sum;
    	} 

    	int res = -inf;

    	int next_negs = negs, sum = 0;
    	for (int next_y = y; next_y >= 0; next_y--)  {
    		next_negs += arr[x][next_y] < 0? 1 : 0;
    		if (next_negs > k) break;
    		sum += arr[x][next_y];
    		if (solve(x + 1, next_y, next_negs) != -inf)
    			res = Math.max(res, sum + solve(x + 1, next_y, next_negs));
    	}
    	
    	next_negs = negs; sum = 0;
    	for (int next_y = y; next_y < n; next_y++)  {
    		next_negs += arr[x][next_y] < 0? 1 : 0;
    		if (next_negs > k) break;
    		sum += arr[x][next_y];
    		if (solve(x + 1, next_y, next_negs) != -inf) {
    			res = Math.max(res, sum + solve(x + 1, next_y, next_negs));
    		}
    	}
    	return dp[x][y][negs] = res;
    }
    public static void main(String[] args) {      
        runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        for (int test = 1;; test++) {
        	n = inp.nextInt(); k = inp.nextInt();
        	if (n == 0 && k == 0) break;
        	
        	arr = new int[n][n];
        	for (int i = 0; i < n; i++) 
        		for (int j = 0; j < n; j++) arr[i][j] = inp.nextInt();

        	dp = new int[n][n][k + 1];
        	vis = new boolean[n][n][k + 1];
        	int ans = solve(0, 0, 0);
        	System.out.println("Case " + test + ": " + (ans == -inf? "impossible" : ans));
        }
        
        //out.close();
    }
}