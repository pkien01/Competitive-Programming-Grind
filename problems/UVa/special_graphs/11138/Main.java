/*input
2
3 4
0 0 1 0
1 1 0 1
0 0 1 0
5 5
1 0 0 1 1
1 1 0 0 0
1 0 0 0 0
0 1 1 0 0
0 0 0 0 1
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
    static int[][] graph;

    static boolean[] vis;
    static int[] match;
    static boolean kuhn(int u) {
    	if (vis[u]) return false;
    	vis[u] = true;
    	for (int v = n; v < n + m; v++) {
    		if (graph[u][v - n] == 0) continue;
    		if (match[v] == -1 || kuhn(match[v])) {
    			match[v] = u;
    			return true;
    		}
    	}
    	return false;
    }
    static int solve() {
    	vis = new boolean[n];
    	match = new int[n + m]; Arrays.fill(match, -1);
    	int ans = 0;
    	for (int v = 0; v < n; v++) {
    		Arrays.fill(vis, false);
    		if (kuhn(v)) ans++;
    	}
    	return ans;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        for (int test = 1; test <= numTests; test++) {
        	n = inp.nextInt(); m = inp.nextInt();
        	graph = new int[n][m];
        	for (int i = 0; i < n; i++)
        		for (int j = 0; j < m; j++) graph[i][j] = inp.nextInt();

        	System.out.format("Case %d: a maximum of %d nuts and bolts can be fitted together\n", test, solve());
        }
        
        //out.close();
    }
}