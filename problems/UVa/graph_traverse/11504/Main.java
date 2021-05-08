/*input
1
3 2
1 2
2 3
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

    static int cntDisc, cntScc;
    static int[] disc, low, scc;
    static boolean[] onStack;
    //static Stack<Integer> stack;
    static int[] stack;
    static int stackSize;
    static void dfs(int u) {
    	disc[u] = ++cntDisc;
    	low[u] = disc[u];
    	//stack.push(u);
        stack[stackSize++] = u;
    	onStack[u] = true;
    	for (Integer v: adj[u]) {
    		if (disc[v] == 0) {
    			dfs(v);
    			low[u] = Math.min(low[u], low[v]);
    		}
    		else if (onStack[v]) low[u] = Math.min(low[u], disc[v]);
    	}
    	if (low[u] == disc[u]) {
            /*
    		while (!stack.empty()) {
    			int top = stack.pop();    		
    			onStack[top] = false;
    			scc[top] = cntScc;	
    			if (top == u) break;
    		}*/
            while (stackSize > 0) {
                int top = stack[--stackSize];
                onStack[top] = false;
                scc[top] = cntScc;
                if (top == u) break;
            }
    		cntScc++;
    	}
    }
    static int solve() {
    	cntDisc = 0; cntScc = 0;
    	disc = new int[n]; low = new int[n]; scc = new int[n];
    	Arrays.fill(scc, -1);
    	onStack = new boolean[n]; 
        stackSize = 0; stack = new int[n];
        //stack = new Stack<>();
    	for (int i = 0; i < n; i++) if (disc[i] == 0) dfs(i);
    	
    	boolean[] knock = new boolean[cntScc];
    	Arrays.fill(knock, true);
    	for (int u = 0; u < n; u++) {
    		for (Integer v: adj[u]) 
    			if (scc[v] != scc[u]) knock[scc[v]] = false; 
    	}
    	int ans = 0;
    	for (int i = 0; i < cntScc; i++) ans += knock[i]? 1: 0;
    	return ans;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	n = inp.nextInt(); m = inp.nextInt();
        	adj = new ArrayList[n];
        	for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        	
        	while (m-- > 0) {
        		int u = inp.nextInt() - 1, v = inp.nextInt() - 1;
        		adj[u].add(v);
        	}

        	System.out.println(solve());
        }

        //System.out.println(solve());
        //out.close();
    }
}