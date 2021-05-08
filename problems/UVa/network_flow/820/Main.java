/*input
4
1 4 5
1 2 20
1 3 10
2 3 5
2 4 10
3 4 20
4
1 4 6
1 2 20
1 3 10
2 3 5
3 2 3
2 4 10
3 4 20
4
1 4 6
1 2 20
1 3 10
2 3 5
2 3 3
2 4 10
3 4 20
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
    static final int inf = (int)2e8;
    static int n, src, dest;
    static int[][] graph;

    static int solve() {
    	int ans = 0;
    	while (true) {
    		int[] par = new int[n]; Arrays.fill(par, -1);
    		Queue<Integer> queue = new LinkedList<>();
    		queue.add(src);
    		while (!queue.isEmpty()) {
    			int u = queue.poll();
    			if (u == dest) break;
    			for (int v = 0; v < n; v++) {
    				if (par[v] == -1 && graph[u][v] > 0) {
    					par[v] = u;
    					queue.add(v);
    				}
    			}
    		}
    		int flow = inf;
    		for (int v = dest; v != src; v = par[v]) {
    			if (par[v] == -1) {
    				flow = 0; break;
    			}
    			flow = Math.min(flow, graph[par[v]][v]);
    		}
    		if (flow == 0) break;
    		for (int v = dest; v != src; v = par[v]) {
    			graph[par[v]][v] -= flow; 
    			graph[v][par[v]] += flow;
    		}
    		ans += flow;
    	}
    	return ans;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        for (int test = 1;; test++) {
        	n = inp.nextInt();
        	if (n == 0) break;
        	graph = new int[n][n];
        	src = inp.nextInt() - 1; dest = inp.nextInt() - 1;
        	int m = inp.nextInt();
        	while (m-- > 0) {
        		int u = inp.nextInt() - 1, v = inp.nextInt() - 1, uv = inp.nextInt();
        		graph[u][v] += uv; graph[v][u] += uv;
        	}
        	System.out.format("Network %d\nThe bandwidth is %d.\n\n", test, solve());
        }	
        
        //out.close();
    }
}