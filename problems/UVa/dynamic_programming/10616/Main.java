/*input
10 2
1
2
3
4
5
6
7
8
9
10
5 1
5 2
5 1
2
3
4
5
6
6 2
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

    static int n;
    static int[] arr;

    static int getMod(int x, int d) {
    	return (x % d + d) % d;
    }
    static long solve(int d, int m) {
    	long[][][] dp = new long[n + 1][m + 1][d];

    	dp[0][0][0] = 1;
    	for (int i = 1; i <= n; i++) {
    		dp[i][0][0] = 1;
    		for (int j = 1; j <= m; j++) {
    			for (int k = 0; k < d; k++) 
    				dp[i][j][k] = dp[i - 1][j][k] + dp[i - 1][j - 1][getMod(k - arr[i], d)];
    		}
    	}

    	return dp[n][m][0];
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        for (int test = 1; inp.hasNextInt(); test++) {
        	n = inp.nextInt();
        	int q = inp.nextInt();
        	if (n == 0 && q == 0) break;
        	arr = new int[n + 1];
        	for (int i = 1; i <= n; i++) arr[i] = inp.nextInt();
        	
        	System.out.println("SET " + test + ":");
        	for (int qi = 1; qi <= q; qi++) {
        		int d = inp.nextInt(), m = inp.nextInt();
        		System.out.print("QUERY " + qi + ": ");
        		System.out.println(solve(d, m));
        	}
        }
        
        //out.close();
    }
}