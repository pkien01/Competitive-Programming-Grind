/*input
7 9 3
1 2 50
1 3 60
2 4 120
2 5 90
3 6 50
4 6 80
4 7 70
5 7 40
6 7 140
1 7
2 6
6 2
7 6 3
1 2 50
1 3 60
2 4 120
3 6 50
4 6 80
5 7 40
7 5
1 7
2 4
0 0 0
*/
import java.util.*;
import java.io.*;


class DisjointSets {
	int nsets;
	int[] parent, rank;
	DisjointSets(int nsets) {
		this.nsets = nsets;
		parent = new int[nsets];
		rank = new int[nsets];
		for (int i = 0; i < nsets; i++) parent[i] = i;
	}
	int findSet(int i) {
		if (parent[i] == i) return i;
		parent[i] = findSet(parent[i]);
		return parent[i];
	}
	boolean sameSet(int i, int j) {
		return findSet(i) == findSet(j);
	}
	void unionSets(int i, int j) {
		int root_i = findSet(i), root_j = findSet(j);
		if (root_i == root_j) return;
		if (rank[root_i] > rank[root_j]) {
			parent[root_j] = root_i;
		}
		else {
			parent[root_i] = root_j;
			if (rank[root_i] == rank[root_j]) rank[root_j]++;
		}
	}
}
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
    	int first, second, weight;
    	Edge(int first, int second, int weight) {
    		this.first = first; this.second = second; this.weight = weight;
    	}
    	Edge(int first, int weight) {
    		this.first = first; this.weight = weight; 
    		this.second = -1;
    	}
    	@Override
    	public int compareTo(Edge other) {
    		return Integer.compare(weight, other.weight);
    	}
    }

    static int n, m;
    static Edge[] edges;
    static ArrayList<Edge>[] mst;

    static void kruskal() {
    	mst = new ArrayList[n];
    	for (int i = 0; i < n; i++) mst[i] = new ArrayList<>();
    	Arrays.sort(edges);
    	DisjointSets dsu = new DisjointSets(n);
    	for (Edge e: edges) {
    		int u = e.first, v = e.second, w = e.weight;
    		if (!dsu.sameSet(u, v)) {
    			mst[u].add(new Edge(v, w));
    			mst[v].add(new Edge(u, w));
    			dsu.unionSets(u, v);
    		}
    	}
    }

    final static int inf = (int)1e9;
    static void dfs(int src, int u, int p) {
    	for (Edge e: mst[u]) {
    		int v = e.first, w = e.weight;
    		if (v != p) {
    			maxEdge[src][v] = Math.max(maxEdge[src][u], w);
    			dfs(src, v, u);
    		}
    	}
    }

    static int[][] maxEdge;
    static void preprocess() {
    	kruskal();

    	maxEdge = new int[n][n];
    	for (int i = 0; i < n; i++) Arrays.fill(maxEdge[i], -1); 
    	for (int i = 0; i < n; i++) {
    		maxEdge[i][i] = 0;
    		dfs(i, i, -1);
    	}
    }
	
	
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        for (int test = 1;; test++) {
        	n = inp.nextInt(); m = inp.nextInt(); int q = inp.nextInt();
        	if (n == 0 && m == 0 && q == 0) break;
        	edges = new Edge[m];
        	for (int i = 0; i < m; i++) edges[i] = new Edge(inp.nextInt() - 1, inp.nextInt() - 1, inp.nextInt());
        	preprocess();
        	if (test != 1) System.out.println();
        	System.out.println("Case #" + test);
        	while (q-- > 0) {
        		int u = inp.nextInt() - 1, v = inp.nextInt() - 1;
        		int ans = maxEdge[u][v];
        		if (ans >= 0) System.out.println(ans);
        		else System.out.println("no path");
        	}
        }
        
        //out.close();
    }
}