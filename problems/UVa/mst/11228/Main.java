/*input
3
3 100
0 0
1 0
2 0
3 1
0 0
100 0
200 0
4 20
0 0
40 30
30 30
10 10
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
	void unionSet(int i, int j) {
		int root_i = findSet(i), root_j = findSet(j);
		if (root_i == root_j) return;
		nsets--;
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
    static class Coord {
    	int x, y;
    	Coord(int x, int y) {
    		this.x = x; this.y = y;
    	}
    	double distance(Coord other) {
    		int diff_x = this.x - other.x, diff_y  = this.y - other.y;
    		return Math.sqrt((double)diff_x * diff_x + diff_y * diff_y); 
    	}
    }
    static class Edge implements Comparable<Edge> {
    	int first, second;
    	double weight;
    	Edge(int first, int second, double weight) {
    		this.first = first; this.second = second;
    		this.weight = weight;
    	}
    	public String toString() {
    		return String.format("(%d, %d, %f)", first, second, weight);
    	}
    	@Override
    	public int compareTo(Edge other) {
    		return Double.compare(this.weight, other.weight);
    	}
    }
    static final double inf = 1e9;
    static int n, r;
    static Coord[] cities;

    static int kruskal(double[][] graph) {
    	ArrayList<Edge> edges = new ArrayList<>();
    	for (int i = 0; i < n; i++)
    		for (int j = i + 1; j < n; j++) 
    			if (graph[i][j] < inf) edges.add(new Edge(i, j, graph[i][j]));

    	Collections.sort(edges);
    	//System.out.println(edges.toString());
    	DisjointSets dsu = new DisjointSets(n);
    	double res = 0.0;
    	for (Edge e: edges) {
    		int u = e.first, v = e.second; double w = e.weight;
    		if (!dsu.sameSet(u, v)) {
    			res += w;
    			dsu.unionSet(u, v);
    		}
    	}
    	return (int)Math.round(res);
    }
    static void solve() {
    	DisjointSets state = new DisjointSets(n);
    	for (int i = 0; i < n - 1; i++) 
    		for (int j = i + 1; j < n; j++) 
    			if (cities[i].distance(cities[j]) <= (double)r) state.unionSet(i, j);
    			
    	double[][] roads = new double[n][n], rails = new double[n][n];
    	for (int i = 0; i < n; i++) {
    		Arrays.fill(roads[i], inf);
    		Arrays.fill(rails[i], inf);
    	}
    	for (int i = 0; i < n; i++) {
    		for (int j = i + 1; j < n; j++) {
    			if (state.sameSet(i, j)) {
    				roads[i][j] = roads[j][i] = Math.min(roads[i][j], cities[i].distance(cities[j]));
    				rails[i][j] = rails[j][i] = 0;
    			}
    			else {
    				roads[i][j] = roads[j][i] = inf;
    				rails[i][j] = rails[j][i] = Math.min(rails[i][j], cities[i].distance(cities[j]));
    			}
    		}
    	}
    	System.out.println(state.nsets + " " + kruskal(roads) + " " + kruskal(rails));
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        for (int test = 1; test <= numTests; test++) {
        	n = inp.nextInt(); r = inp.nextInt();
        	cities = new Coord[n];
        	for (int i = 0; i < n; i++) cities[i] = new Coord(inp.nextInt(), inp.nextInt());
        	System.out.print("Case #" + test + ": "); solve();	
        }
        
        //out.close();
    }
}