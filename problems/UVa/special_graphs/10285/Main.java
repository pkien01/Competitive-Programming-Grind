/*input
2
Feldberg 10 5
56 14 51 58 88
26 94 24 39 41
24 16 8 51 51
76 72 77 43 10
38 50 59 84 81
5 23 37 71 77
96 10 93 53 82
94 15 96 69 9
74 0 62 38 96
37 54 55 82 38
Spiral 5 5
1 2 3 4 5
16 17 18 19 6
15 24 25 20 7
14 23 22 21 8
13 12 11 10 9
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
    static int n, m;
    static int[][] arr;

    static final int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
    static int[][] dp;

    static boolean valid(int x, int y) {
    	return x >= 0 && x < n && y >= 0 && y < m;
    }
    static int solve(int x, int y) {
    	if (dp[x][y] != -1) return dp[x][y];
    	int res = 1;
    	for (int i = 0; i < 4; i++) 
    		if (valid(x + dx[i], y + dy[i]) && arr[x + dx[i]][y + dy[i]] < arr[x][y])res = Math.max(res, solve(x + dx[i], y + dy[i]) + 1);
    	dp[x][y] = res;
    	return res;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	String name = inp.next(); n = inp.nextInt(); m = inp.nextInt();
        	arr = new int[n][m];
        	for (int i = 0; i < n; i++)
        		for (int j = 0; j < m; j++) arr[i][j] = inp.nextInt();

        	dp = new int[n][m];
        	for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1);

        	int ans = 0;
        	for (int i = 0; i < n; i++)
        		for (int j = 0; j < m; j++) ans = Math.max(ans, solve(i, j));
        	System.out.println(name + ": " + ans);
        }
        
        //out.close();
    }
}