/*input

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

    final static int n = 26;
    static boolean[][] graph;
    static int[] task;

    static int[] dp;
    static int dfs(int u) {
    	if (dp[u] != -1) return dp[u];
    	int res = 0;
    	for (int v = 0; v < n; v++)
    		if (graph[u][v]) res = Math.max(res, dfs(v));
    	dp[u] = res + task[u];
    	return dp[u];
    } 
    public static void main(String[] args) {      
        runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = Integer.parseInt(inp.nextLine()); inp.nextLine();
        for (int test = 1; test <= numTests; test++) {
        	graph = new boolean[n][n];
        	task = new int[n];
        	while (inp.hasNextLine()) {
        		String line = inp.nextLine();
        		if (line.isEmpty()) break;
        		String[] line_split = line.split(" ");
        		int u = (int)line_split[0].charAt(0) - 'A';
        		task[u] = Integer.parseInt(line_split[1]);
        		if (line_split.length == 3) {
        			for (int i = 0; i < line_split[2].length(); i++) {
        				int v = (int)line_split[2].charAt(i) - 'A';
        				graph[v][u] = true;
        			}
        		}	
        	}
        	dp = new int[n];
        	Arrays.fill(dp, -1);
        	int ans = 0;
        	for (int i = 0; i < n; i++) ans = Math.max(ans, dfs(i));
        	if (test != 1) System.out.println();
        	System.out.println(ans);

        }
        
        //out.close();
    }
}