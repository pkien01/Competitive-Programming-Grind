/*input
2
3 3
0 1 1000
1 2 15
2 1 -42
4 4
0 1 10
1 2 20
2 3 30
3 0 -60
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
    static class Edge {
    	int vertex, weight;
    	Edge(int vertex, int weight) {
    		this.vertex = vertex; this.weight = weight;
    	}
    }

    static int inf = (int)2e8;
    static int n, m;
    static ArrayList<Edge>[] adj;

    static boolean solve() {
    	int[] dist = new int[n];
    	Arrays.fill(dist, inf); dist[0] = 0;
    	for (int i = 0; i < n - 1; i++) {
    		for (int u = 0; u < n; u++) {
    			for (Edge e: adj[u]) {
    				int v = e.vertex, uv = e.weight;
    				if (dist[u] + uv < dist[v]) dist[v] = dist[u] + uv;
    			}
    		}
    	}
    	for (int u = 0; u < n; u++) {
    		for (Edge e: adj[u]) {
    			int v = e.vertex, uv = e.weight;
    			if (dist[u] + uv < dist[v]) return true;
    		}
    	}
    	return false;
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
        		int u = inp.nextInt(), v = inp.nextInt(), w = inp.nextInt();
        		adj[u].add(new Edge(v, w));
        	}
        	System.out.println(solve()? "possible" : "not possible");
        }
        
        //out.close();
    }
}