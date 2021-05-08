/*input
1
2 4
0 100
0 300
0 600
150 750
*/
import java.util.*;
import java.io.*;

class DisjointSets {
	int nsets;
	int[] parent, rank;
	DisjointSets(int nsets) {
		this.nsets = nsets;
		parent = new int[nsets]; rank = new int[nsets];
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
    static class Point {
    	int x, y;
    	Point(int x, int y) {
    		this.x = x; this.y = y;
    	}
    	double distance(Point other) {
    		int diff_x = x - other.x, diff_y = y - other.y;
    		return Math.sqrt((double)diff_x * diff_x + diff_y * diff_y);
    	}
    }
    static class Edge implements Comparable<Edge> {
    	int first, second;
    	double weight;
    	Edge(int first, int second, double weight) {
    		this.first = first; this.second = second; this.weight = weight;
    	}
    	@Override
    	public int compareTo(Edge other) {
    		return Double.compare(weight, other.weight);
    	}
    }
    static int n, s;
    static Point[] arr;

    static double solve() {
    	ArrayList<Edge> edges = new ArrayList<>();
    	for (int i = 0; i < n; i++)
    		for (int j = i + 1; j < n; j++) edges.add(new Edge(i, j, arr[i].distance(arr[j])));
    	Collections.sort(edges);

    	DisjointSets dsu = new DisjointSets(n);
    	for (Edge e: edges) {
    		int u = e.first, v = e.second; double w = e.weight;
    		if (!dsu.sameSet(u, v)) {
    			dsu.unionSets(u, v);
    			if (dsu.nsets == s) return w;
    		}
    	}
    	return 0;
    }

    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	s = inp.nextInt(); n = inp.nextInt();
        	arr = new Point[n];
        	for (int i = 0; i < n; i++)  arr[i] = new Point(inp.nextInt(), inp.nextInt());
        	System.out.format("%.2f\n", solve());
        }
        
        //out.close();
    }
}