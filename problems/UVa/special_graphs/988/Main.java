/*input
6
3 1 2 5
3 2 3 4
2 3 4
0
1 5
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

    static int n;
    static ArrayList<Integer>[] adj;
    static int[] dp;

    static int dfs(int u) {
    	if (adj[u].isEmpty()) return 1;
    	if (dp[u] != -1) return dp[u];
    	int res = 0;
    	for (Integer v: adj[u]) res += dfs(v);
    	dp[u] = res;
    	return res;
    }

    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        boolean firstLoop = true;
        while (inp.hasNextInt()) {
        	n = inp.nextInt();
        	adj = new ArrayList[n];
        	for (int i = 0; i < n; i++) {
        		int choices = inp.nextInt();
                adj[i] = new ArrayList<>();
        		while (choices-- > 0) adj[i].add(inp.nextInt());
        	}

        	dp = new int[n];
        	Arrays.fill(dp, -1);
            if (firstLoop) firstLoop = false;
            else System.out.println();
        	System.out.println(dfs(0));
        }
        
        //out.close();
    }
}