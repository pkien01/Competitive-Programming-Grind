/*input
2 2 4
1
3
4 2
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
    static class Point implements Comparable<Point> {
    	int x, y;
    	Point(int x, int y) {
    		this.x = x; this.y =y; 
    	}
    	@Override 
    	public int compareTo(Point other) {
    		if (this.x != other.x) return this.x - other.x;
    		return this.y - other.y;
    	}
    }

    static final long inf = (long)2e15;
    static int n, m, k;
    static int[][] edgeRight, edgeDown;
    static long[][][] dp;


    static void solve() {
    	dp = new long[n][m][k + 1];
    	for (int step = 1; step <= k; step++) {
    		for (int i = 0; i < n; i++) {
    			for (int j = 0; j < m; j++) {
    				if (step < 2) dp[i][j][step] = inf;
    				else dp[i][j][step] = dp[i][j][step - 2] + edge
    			}
    		}
    	}
    }

    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        n = inp.nextInt(); m = inp.nextInt(); k = inp.nextInt();
        edgeRight = new int[n][m - 1];
        for (int i = 0; i < n; i++)
        	for (int j = 0; j < m - 1; j++) edgeRight[i][j] = inp.nextInt();

        edgeDown = new int[n - 1][m];
    	for (int i = 0; i < n - 1; i++)
    		for (int j = 0; j < m; j++) edgeDown[i][j] = inp.nextInt();

    	solve();
    	for (int i = 0; i < n; i++)
    		for (int j = 0; j < m; j++) System.out.print(ans[i][j] + (j == m - 1? "\n": " "));
        
        //out.close();
    }
}