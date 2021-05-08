/*input
4 2
1 2 1
3 4 2
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
    static int n, m;
    static ArrayList<Integer>[] adj;

    static int discTime;
    static int[] disc, low;
    static boolean[] onStack;
    static Stack<Integer> stack;
    static int cntComps;
    static void dfs(int u) {
    	if (cntComps > 1) return;
    	disc[u] = ++discTime;
    	low[u] = disc[u];
    	stack.push(u);
    	onStack[u] = true;
    	for (Integer v: adj[u]) {
    		if (disc[v] == 0) {
    			dfs(v);
    			low[u] = Math.min(low[u], low[v]);
    		}
    		else if (onStack[v]) low[u] = Math.min(low[u], disc[v]);
    	}
    	if (low[u] == disc[u]) {
    		cntComps++;
    		while (!stack.empty()) {
                int top = stack.pop();
                onStack[top] = false;
                if (top == u) break;
            } 
    	}
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (true) {
        	n = inp.nextInt(); m = inp.nextInt();
        	if (n == 0 && m == 0) break;
        	adj = new ArrayList[n];
        	for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        	while (m-- > 0) {
        		int u = inp.nextInt() - 1, v = inp.nextInt() - 1, type = inp.nextInt();
        		adj[u].add(v);
        		if (type == 2) adj[v].add(u);
        	}

        	cntComps = 0;
        	discTime = 0;
        	disc = new int[n]; low = new int[n]; onStack = new boolean[n];
        	stack = new Stack<>();
        	for (int i = 0; i < n; i++) if (disc[i] == 0) dfs(i);
        	System.out.println(cntComps != 1? 0 : 1);
        }
        
        //out.close();
    }
}