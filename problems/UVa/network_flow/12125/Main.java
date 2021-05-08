/*input
2
5 3.5
1 1 1 1
2 3 0 1
3 5 1 1
5 1 1 1
5 4 0 1
3 1.1
-1 0 5 10
0 0 3 9
2 0 1 1
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
    static class Floe {
    	int x, y, penguins, jumps;
    	Floe(int x, int y, int penguins, int jumps) {
    		this.x = x; this.y = y; this.penguins = penguins; this.jumps = jumps;
    	}
    	double distance(Floe other) {
    		int diff_x = x - other.x, diff_y = y - other.y;
    		return Math.sqrt((double)diff_x * diff_x + diff_y * diff_y);
    	}
    }

    static final int inf = (int)2e8;

    static int n;
    static double maxDist;
    static Floe[] arr;


    static int[][] graph;
    static int EdmondKarp(int src, int sink) {
    	int[][] resGraph = new int[graph.length][graph.length];
    	for (int i = 0; i < graph.length; i++)
    		for (int j = 0; j < graph.length; j++) resGraph[i][j] = graph[i][j];
    	int maxFlow = 0;
    	while (true) {
    		int[] par = new int[graph.length]; Arrays.fill(par, -1);
    		Queue<Integer> queue = new LinkedList<>(); queue.add(src);
    		while (!queue.isEmpty()) {
    			int u = queue.poll();
    			if (u == sink) break;
    			for (int v = 0; v < graph.length; v++) {
    				if (par[v] == -1 && resGraph[u][v] > 0) {
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
    			flow = Math.min(flow, resGraph[par[v]][v]);
    		}
    		if (flow == 0) break;

    		for (int v = sink; v != src; v = par[v]) {
    			resGraph[par[v]][v] -= flow; resGraph[v][par[v]] += flow;
    		}
    		maxFlow += flow;
    	}
    	return maxFlow;
    }

    static void solve() {
    	graph = new int[2*n + 1][2*n + 1];
    	for (int i = 1; i <= n; i++) {
    		graph[0][i] = arr[i].penguins;
    		graph[i][i + n] = arr[i].jumps;
    	}

    	for (int i = 1; i <= n; i++) {
    		for (int j = i + 1; j <= n; j++) {
    			if (arr[i].distance(arr[j]) <= maxDist) {
    				graph[i + n][j] = inf; graph[j + n][i] = inf;
    			}
    		}
    	}

    	int sumPenguins = 0;
    	for (int i = 1; i <= n; i++) sumPenguins += arr[i].penguins;
    	//System.out.println(sumPenguins);
    	
    	ArrayList<Integer> ans = new ArrayList<>();

    	for (int i = 1; i <= n; i++) 
    		if (EdmondKarp(0, i) == sumPenguins) ans.add(i - 1);

    	if (ans.isEmpty()) System.out.println("-1");
    	else {
    		for (int i = 0; i < ans.size(); i++) System.out.print(ans.get(i) + (i == ans.size() - 1? "\n" : " "));
    	}
    	
    	
    }
    public static void main(String[] args) {      
        runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	n = inp.nextInt(); maxDist = inp.nextDouble();
        	arr = new Floe[n + 1];
        	for (int i = 1; i <= n; i++) arr[i] = new Floe(inp.nextInt(), inp.nextInt(), inp.nextInt(), inp.nextInt());
        	solve();
        }
        
        //out.close();
    }
}