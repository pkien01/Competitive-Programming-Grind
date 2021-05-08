/*input
4
1 2
3 1 3 4
1 2
1 2
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

    static int[] height;
    static int maxDepth, endpoint;

    static void dfs(int u, int p, int depth) {
    	if (depth >= maxDepth) {
    		maxDepth =depth;
    		endpoint = u;
    	}
    	height[u] = Math.max(height[u], depth);
    	for (Integer v: adj[u]) { 
    		if (v == p) continue;
    		dfs(v, u, depth + 1);
    	}
    }


    static void solve() {
    	height = new int[n]; 
    	maxDepth = 0; endpoint = -1;
    	dfs(0, -1, 0);
    	dfs(endpoint, -1, 0);
    	dfs(endpoint, -1, 0);
    	
    	int minHeight = maxDepth;
    	for (int i = 0; i < n; i++) minHeight = Math.min(minHeight, height[i]);
    	
    	//System.out.println(Arrays.toString(height));
    	
    	System.out.print("Best Roots  :");
    	for (int i = 0; i < n; i++) if (height[i] == minHeight) System.out.print(" " + (i + 1));
    	System.out.println();
    	System.out.print("Worst Roots :");
    	for (int i = 0; i < n; i++) if (height[i] == maxDepth) System.out.print(" " + (i + 1));
    	System.out.println();
    	//System.out.println(diam);

    }
    public static void main(String[] args) {      
        runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (inp.hasNextInt()) {
        	n = inp.nextInt();
        	adj = new ArrayList[n];
        	for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        	for (int u = 0; u < n; u++) {
        		int n_i = inp.nextInt();
        		while (n_i-- > 0) {
        			int v = inp.nextInt() - 1;
        			adj[u].add(v); 
        		}
        	}
        	solve();
        }
        
        //out.close();
    }
}