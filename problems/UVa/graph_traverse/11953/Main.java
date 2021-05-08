/*input
2
4
x...
..x.
@.@.
....
2
..
x.
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
    static char[][] arr;
    static boolean[][] vis;

    static boolean dfs(int x, int y) {
    	if (x < 0 || x >= n || y < 0 || y >= n || arr[x][y] == '.' || vis[x][y]) return false;
    	vis[x][y] = true;
    	boolean res = arr[x][y] == 'x';
    	if (dfs(x - 1, y)) res = true;
    	if (dfs(x, y + 1)) res = true;
    	if (dfs(x + 1, y)) res = true;
    	if (dfs(x, y - 1)) res = true;
    	return res;
    }

    static int solve() {
    	vis = new boolean[n][n];
    	int ans = 0;
    	for (int i = 0; i < n; i++)
    		for (int j = 0; j < n; j++)
    			if (!vis[i][j] && arr[i][j] != '.') ans += dfs(i, j)? 1: 0;
    	return ans;
    }
    public static void main(String[] args) {      
        runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        for (int test = 1; test <= numTests; test++) {
        	n = inp.nextInt();
        	arr = new char[n][n];
        	for (int i = 0; i < n; i++) arr[i] = inp.next().toCharArray();
        	System.out.println("Case " + test + ": " + solve());
        }
        
        //out.close();
    }
}