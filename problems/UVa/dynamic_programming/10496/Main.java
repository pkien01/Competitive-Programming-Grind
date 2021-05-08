/*input
1
10 10
1 1
4
2 3
5 5
9 4
6 5
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

    static int n;
    static int[][] dist;
    static int[][] arr;
    static int[][] dp;

    static int solve(int u, int mask) {
    	if (mask == (1 << n) - 1) return dist[u][0];
    	if (dp[u][mask] != -1) return dp[u][mask];
    	int res = 20*20;
    	for (int v = 0; v < n; v++)
    		if ((mask >> v & 1) == 0) res = Math.min(res, solve(v, mask | (1 << v)) + dist[u][v]);
    	return res;
    }

    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	int x_size = inp.nextInt(), y_size = inp.nextInt();
        	int start_x = inp.nextInt(), start_y = inp.nextInt();
        	n = inp.nextInt() + 1;
     		arr = new int[n][2];
     		arr[0][0] = start_x; arr[0][1] = start_y;
     		
     		dist = new int[n][n];
     		for (int i = 1; i < n; i++) {
     			arr[i][0] = inp.nextInt(); arr[i][1] = inp.nextInt();
     			for (int j = 0; j < i; j++) {
     				dist[i][j] = Math.abs(arr[i][0] - arr[j][0]) + Math.abs(arr[i][1] - arr[j][1]);
     				dist[j][i] = dist[i][j];
     			}
     		}
     		dp = new int[n][1 << n];
     		for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1);
     		System.out.println("The shortest path has length " + solve(0, 1));
        }
        
        //out.close();
    }
}