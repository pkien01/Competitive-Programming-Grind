/*input
3
2 1 0 1
0 1 100
3 3 2 0
0 1 100
0 2 200
1 2 50
2 0 0 1
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
    static class Edge implements Comparable<Edge> {
    	int vertex, weight;
    	Edge(int vertex, int weight) {
    		this.vertex = vertex; this.weight = weight;
    	}
    	@Override
    	public int compareTo(Edge other) {
    		return Integer.compare(weight, other.weight);
    	}
    }
    static final int inf = (int)2e8;
    static int n, m, src, dest;
    static ArrayList<Edge>[] adj;

    static int solve() {
    	int[] dist = new int[n];
    	Arrays.fill(dist, inf);
    	PriorityQueue<Edge> pq = new PriorityQueue<>();
    	dist[src] = 0; pq.add(new Edge(src, dist[src]));
    	while (!pq.isEmpty()) {
    		int u = pq.poll().vertex;
    		for (Edge e: adj[u]) {
    			int v = e.vertex, w = e.weight;
    			if (dist[u] + w < dist[v]) {
    				dist[v] = dist[u] + w;
    				pq.add(new Edge(v, dist[v]));
    			}
    		}
    	}
    	return dist[dest];
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
       	for (int test = 1; test <= numTests; test++) {
        	n = inp.nextInt(); m = inp.nextInt(); src = inp.nextInt(); dest = inp.nextInt();
        	adj = new ArrayList[n];
        	for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        	while (m-- > 0) {
        		int u = inp.nextInt(), v = inp.nextInt(), w = inp.nextInt();
        		adj[u].add(new Edge(v, w)); adj[v].add(new Edge(u, w));
        	}
        	int ans = solve();
        	System.out.println("Case #" + test + ": " + (ans < inf? ans : "unreachable"));
        }
        
        //out.close();
    }
}