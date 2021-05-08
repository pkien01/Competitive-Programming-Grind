/*input
3 3
0 1 1
1 2 2
2 0 3
4 5
0 1 1
1 2 2
2 3 3
3 1 4
0 2 0
3 1
0 1 1
0 0
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
		nsets--;
		if (rank[root_i] > rank[root_j]) parent[root_j] = root_i;
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
    	@Override
    	public int compareTo(Edge other) {
    		return Integer.compare(weight, other.weight);
    	}
    }

    static int n, m;
    static Edge[] edges;

    static void solve() {
    	Arrays.sort(edges);
    	ArrayList<Integer> ans = new ArrayList<>();
    	DisjointSets dsu = new DisjointSets(n);
    	for (Edge e: edges) {
    		if (!dsu.sameSet(e.first, e.second)) dsu.unionSets(e.first, e.second);
    		else ans.add(e.weight);
    	}
    	if (ans.size() == 0) System.out.println("forest");
    	else {
    		for (int i = 0; i < ans.size(); i++) System.out.print(ans.get(i) + (i == ans.size() - 1? "\n": " "));
    	}
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (true) {
        	n = inp.nextInt(); m = inp.nextInt();
        	if (n == 0 && m == 0) break;
        	edges = new Edge[m];
        	for (int i = 0; i < m; i++) edges[i] = new Edge(inp.nextInt(), inp.nextInt(), inp.nextInt());
        	solve();
        }
        
        //out.close();
    }
}