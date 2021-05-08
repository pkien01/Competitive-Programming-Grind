/*input
1
22 22
1 2 265
2 3 936
3 4 110
4 5 421
5 6 966
6 7 140
7 8 83
8 9 784
9 10 925
10 11 110
11 12 248
12 13 809
13 14 578
14 15 187
15 16 661
16 17 806
17 18 940
18 19 846
19 20 456
20 21 742
21 22 769
8 2 54
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
    static final int inf = (int)1e9;

    static int n, m;
    static Edge[] edges;
    static boolean[] inMST; 

    static int kruskal(int rmv) {
    	int res = 0;
    	DisjointSets dsu = new DisjointSets(n);
    	for (int i = 0; i < m; i++) {
    		if (i == rmv) continue;
    		int u = edges[i].first, v = edges[i].second, w = edges[i].weight;
    		if (!dsu.sameSet(u, v)) {
    			if (rmv == -1) inMST[i] = true;
    			res += w;
    			dsu.unionSets(u, v);
    		}
    	}
    	return dsu.nsets == 1? res: inf;
    }
    static void solve() {
    	Arrays.sort(edges);
    	inMST = new boolean[m];
    	int ans1 = kruskal(-1), ans2 = inf;
    	//System.out.println(Arrays.toString(inMST));
    	for (int i = 0; i < m; i++) 
    		if (inMST[i]) ans2 = Math.min(ans2, kruskal(i));
    	System.out.println(ans1 + " " + ans2);
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	n = inp.nextInt(); m = inp.nextInt();
        	edges = new Edge[m];
        	for (int i = 0; i < m; i++) edges[i] = new Edge(inp.nextInt() - 1, inp.nextInt() - 1, inp.nextInt());
        	solve();
        }
        
        //out.close();
    }
}