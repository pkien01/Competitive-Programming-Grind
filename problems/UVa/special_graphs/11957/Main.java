/*input
2
4
....
....
....
..W.
8
.B.B.B..
........
........
..B.....
........
..B.....
.W......
........
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

    static final int mod = 1000007;

    static int n;
    static char[][] board;

    static int[][] dp;
    static int solve(int x, int y) {
    	if (x == 0) return 1;
    	if (dp[x][y] != -1) return dp[x][y];
    	int res = 0;
    	if (y - 1 >= 0 && board[x - 1][y - 1] == '.') res = (res + solve(x - 1, y - 1)) % mod;
    	if (x - 2 >= 0 && y - 2 >= 0 && board[x - 1][y - 1] != '.' && board[x - 2][y - 2] == '.') res = (res + solve(x - 2, y - 2)) % mod;
    	if (y + 1 < n && board[x - 1][y + 1] == '.') res = (res + solve(x - 1, y + 1)) % mod;
    	if (x - 2 >= 0 && y + 2 < n && board[x - 1][y + 1] != '.' && board[x - 2][y + 2] == '.') res = (res + solve(x - 2, y + 2)) % mod;
    	dp[x][y] = res;
    	return dp[x][y];
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        for (int test = 1; test <= numTests; test++) {
        	n = inp.nextInt();
        	board = new char[n][n];
        	for (int i = 0; i < n; i++) board[i] = inp.next().toCharArray();

        	dp = new int[n][n];
        	for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1);

        	int init_x = -1, init_y = -1;
        	for (int i = 0; i < n; i++) {
        		for (int j = 0; j < n; j++) {
        			if (board[i][j] == 'W') {
        				init_x = i; init_y = j;
        			}
        		}
        	}
        	System.out.println("Case " + test + ": " + solve(init_x, init_y));
        }
        
        //out.close();
    }
}