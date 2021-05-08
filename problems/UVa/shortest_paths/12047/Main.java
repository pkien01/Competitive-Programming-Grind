/*input
1
10 19 8 1 101
1 5 19
2 1 20
2 3 23
2 9 18
2 10 24
3 7 27
3 8 23
4 1 28
4 3 27
4 8 20
6 2 21
6 5 32
8 2 27
9 5 37
10 3 23
10 4 17
10 6 22
10 7 34
10 9 24
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
    static int n, src, dest, maxToll;
    static ArrayList<Edge>[] adj, rev_adj;

    static void dijkstra(int from, int[] dist) {
    	Arrays.fill(dist, inf);
    	PriorityQueue<Edge> pq = new PriorityQueue<>();
    	dist[from] = 0; pq.add(new Edge(from, dist[from]));	
    	while (!pq.isEmpty()) {
    		Edge top = pq.poll();
    		int u = top.vertex;
    		if (dist[u] < top.weight) continue;
    		for (Edge e: adj[u]) {
    			int v = e.vertex, w = e.weight;
    			if (dist[u] + w < dist[v]) {
    				dist[v] = dist[u] + w;
    				pq.add(new Edge(v, dist[v]));
    			}
    		}
    	}
    }
    static int solve() {
    	int[] dist_src = new int[n];
    	Arrays.fill(dist_src, inf); dist_src[src] = 0;
    	PriorityQueue<Edge> pq = new PriorityQueue<>();
    	pq.add(new Edge(src, dist_src[src]));
    	while (!pq.isEmpty()) {
    		Edge top = pq.poll();
    		int u = top.vertex;
    		if (dist_src[u] < top.weight) continue;
    		for (Edge e: adj[u]) {
    			int v = e.vertex, w = e.weight;
    			if (dist_src[u] + w < dist_src[v]) {
    				dist_src[v] = dist_src[u] + w;
    				pq.add(new Edge(v, dist_src[v]));
    			}
    		}
    	}

    	int[] dist_dest = new int[n];
    	Arrays.fill(dist_dest, inf); dist_dest[dest] = 0;
    	pq = new PriorityQueue<>();
    	pq.add(new Edge(dest, dist_dest[dest]));
    	while (!pq.isEmpty()) {
    		Edge top = pq.poll();
    		int u = top.vertex;
    		if (dist_dest[u] < top.weight) continue;
    		for (Edge e: rev_adj[u]) {
    			int v = e.vertex, w = e.weight;
    			if (dist_dest[u] + w < dist_dest[v]) {
    				dist_dest[v] = dist_dest[u] + w;
    				pq.add(new Edge(v, dist_dest[v]));
    			}
    		}
    	}

    	int ans = -1;
    	for (int u = 0; u < n; u++) {
    		if (dist_src[u] == inf) continue;
    		for (Edge e: adj[u]) {
    			int v = e.vertex, w = e.weight;
    			if (dist_dest[v] == inf) continue;
    			//System.out.println(dist_src[u] + " " + w + " " + dist_dest[v]);
    			if (dist_src[u] + w + dist_dest[v] <= maxToll) 
    				ans = Math.max(ans, w);
    		}
    	}
    	//System.out.println(dist_src[8]);
    	return ans;
    }
    public static void main(String[] args) {      
        //runFromFile();
        FastReader inp = new FastReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	n = inp.nextInt();
        	adj = new ArrayList[n];
        	rev_adj = new ArrayList[n];
        	for (int i = 0; i < n; i++) {
        		adj[i] = new ArrayList<>();
        		rev_adj[i] = new ArrayList<>();
        	}
        	int m = inp.nextInt();
        	src = inp.nextInt() - 1; dest = inp.nextInt() - 1; 
        	maxToll = inp.nextInt();

        	while (m-- > 0) {
        		int u = inp.nextInt() - 1, v = inp.nextInt() - 1, w = inp.nextInt();
        		adj[u].add(new Edge(v, w)); 
        		rev_adj[v].add(new Edge(u, w));
        	}
        	out.println(solve());
        }
        
        out.close();
    }
}

class FastReader {
    BufferedReader reader;
    StringTokenizer tokenizer;
    FastReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream));
        tokenizer = null;
    }
    FastReader() {
        this(System.in);
    }
    String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tokenizer.nextToken();
    }
    int nextInt() {
        return Integer.parseInt(next());
    }
    long nextLong() {
        return Long.parseLong(next());
    }        
    double nextDouble() {
        return Double.parseDouble(next());
    }
    String nextLine() {
        String line = new String();
        try {
            line = reader.readLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }
}