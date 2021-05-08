/*input
6
5 19
55 28
38 101
28 62
111 84
43 116
5
11 27
84 99
142 81
88 30
95 38
3
132 73
49 86
72 111
0
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
    static class Computer {
    	int x, y;
    	Computer(int x, int y) {
    		this.x = x; this.y = y;
    	}
    	double cableLength(Computer other) {
    		int diff_x = this.x - other.x, diff_y = this.y - other.y;
    		return Math.sqrt((double)diff_x * diff_x + (double)diff_y * diff_y) + 16.0;
    	}
    }

    final static double inf = 2e8, eps = 1e-7;

    static int n;
    static Computer[] arr;

    static double[][] dist;
    static double[][] dp;

    static boolean isEqualDouble(double num1, double num2) {
    	return Math.abs(num1 - num2) < eps;
    } 

    static double solve(int u, int mask) {
    	if (mask == (1 << n) - 1) return 0.0;
    	if (!isEqualDouble(dp[u][mask], -1.0)) return dp[u][mask];
    	double res = inf;
    	for (int v = 0; v < n; v++)
    		if ((mask >> v & 1) == 0) res = Math.min(res, solve(v, mask | (1 << v)) + dist[u][v]);
    	dp[u][mask] = res;
    	return res;
    }

    static void trace(int u, int mask) {
    	if (mask == (1 << n) - 1) return;
    	for (int v = 0; v < n; v++)	{
    		if ((mask >> v & 1) == 0 && isEqualDouble(solve(u, mask), solve(v, mask | (1 << v)) + dist[u][v])) {
    			System.out.format("Cable requirement to connect (%d,%d) to (%d,%d) is %.2f feet.\n", arr[u].x, arr[u].y, arr[v].x, arr[v].y, dist[u][v]);
    			trace(v, mask | (1 << v));
    			break;
    		}
    	}
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        for (int test = 1;; test++) {
        	n = inp.nextInt();
        	if (n == 0) break;
        	arr = new Computer[n];
        	dist = new double[n][n];
        	for (int i = 0; i < n; i++) {
        		arr[i] = new Computer(inp.nextInt(), inp.nextInt());
        		dist[i][i] = 0.0;
        		for (int j = 0; j < i; j++) {
        			dist[i][j] = arr[i].cableLength(arr[j]);
        			dist[j][i] = dist[i][j];
        		}
        	}
        	dp = new double[n][1 << n];
        	for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1.0);
        	double ans = inf;
        	int startNode = -1;
        	for (int i = 0; i < n; i++) {
        		if (solve(i, (1 << i)) < ans) {
        			ans = solve(i, (1 << i));
        			startNode = i;
        		}	
        	}

        	System.out.println("**********************************************************");
        	System.out.format("Network #%d\n", test);
        	trace(startNode, (1 << startNode));
        	System.out.format("Number of feet of cable required is %.2f.\n", ans);
        }
        
        //out.close();
    }
}