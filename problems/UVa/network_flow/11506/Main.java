/*input
4 6
3 0
2 0
1 2 100000
1 3 100000
1 4 4
2 4 100000
2 3 100000
3 4 100000
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

    static final int inf = (int)2e8;
    static int n, src, sink;
    static int[][] graph;

    static int solve() {
    	int ans = 0;
    	while (true) {
    		int[] par = new int[n * 2]; Arrays.fill(par, -1);
    		Queue<Integer> queue = new LinkedList<>(); 
    		queue.add(src);
    		while (!queue.isEmpty()) {
    			int u = queue.poll();
    			if (u == sink) break;
    			for (int v = 0; v < n * 2; v++) {
    				if (par[v] == -1 && graph[u][v] > 0) {
    					par[v] = u;
    					queue.add(v);
    				}
    			}
    		}

    		int flow = inf;
    		for (int v = sink; v != src; v = par[v]) {
    			if (par[v] == -1) {
    				flow = 0; break;
    			}
    			flow = Math.min(flow, graph[par[v]][v]);
    		}
    		if (flow == 0) break;

    		for (int v = sink; v != src; v = par[v]) {
    			graph[par[v]][v] -= flow; graph[v][par[v]] += flow;
    			//System.out.println(v + "<-" + par[v]);
    		}
    		//System.out.println();
    		ans += flow;
    	}
    	return ans;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (true) {
        	n = inp.nextInt(); int m = inp.nextInt();
        	if (n == 0 && m == 0) break;
        	graph = new int[n * 2][n * 2];
        	src = 0; sink = n - 1;
        	for (int i = 0; i < n - 2; i++) {
        		int v_in = inp.nextInt() - 1, w = inp.nextInt();
        		int v_out = v_in + n;
        		graph[v_in][v_out] =  w; graph[v_out][v_in] = w;
        		//System.out.println(v_in + " " + v_out + " " + w);
        	}
        	while (m-- > 0) {
        		int u_in = inp.nextInt() - 1, v_in = inp.nextInt() - 1, w = inp.nextInt();
        		int u_out = u_in + (u_in == src || u_in == sink? 0 : n);
        		int v_out = v_in + (v_in == src || v_in == sink? 0 : n);
        		graph[u_out][v_in] = w;  graph[v_out][u_in] = w;
        	}
        	System.out.println(solve());

        }
        
        //out.close();
    }
}