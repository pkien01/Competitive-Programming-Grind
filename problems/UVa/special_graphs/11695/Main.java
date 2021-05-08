/*input
2
4
1 2
2 3
3 4
14
1 2
1 8
2 3
2 4
8 9
8 10
8 11
4 5
4 6
4 7
10 12
10 13
13 14
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
    	int first, second;
    	Edge(int first, int second) {
    		this.first = first; this.second = second;
    	}
    	Edge() {
    		this.first = -1; this.second = -1;
    	}
    	boolean equals(Edge other) {
    		return (first == other.first && second == other.second) || (first == other.second && second == other.first);
    	}
    	@Override
    	public String toString() {
    		return (this.first + 1) + " " + (this.second + 1);
    	}
    }

    static final int inf = (int)1e9;

    static int n;
    static ArrayList<Integer>[] adj;

    static int maxDepth, endpoint;
    static int[] par;
    static void dfs(int u, int depth, Edge rmv, Edge add) {
    	if (depth > maxDepth) {
    		maxDepth = depth;
    		endpoint = u;
    	}
    	for (Integer v: adj[u]) {
    		if (v == par[u] || rmv.equals(new Edge(u, v))) continue;
    		par[v] = u;
    		dfs(v, depth + 1, rmv, add);
    	}
    	if (u == add.first) {
    		if (add.second != par[u]) {
    			par[add.second] = u;
    			dfs(add.second, depth + 1, rmv, add);
    		} 
    	}
    	else if (u == add.second) {
    		if (add.first != par[u]) {
    			par[add.first] = u;
    			dfs(add.first, depth + 1, rmv, add); 
    		}
    	}
    }
    static ArrayList<Integer> diameter(int root, Edge rmv, Edge add) {
    	par = new int[n]; 
    	Arrays.fill(par, -1); maxDepth = -1; endpoint = -1;
    	dfs(root, 0, rmv, add);
    	//System.out.println(endpoint+ " " + rmv.first + " " + rmv.second);
    	Arrays.fill(par, -1); maxDepth = -1; 
    	dfs(endpoint, 0, rmv, add);
    	ArrayList<Integer> res = new ArrayList<>();
    	for (int v = endpoint; v != -1; v = par[v]) res.add(v);
    	return res;
    }

    static void solve() {
    	ArrayList<Integer> diam = diameter(0, new Edge(), new Edge());
    	//System.out.println(diam);
    	int ans = inf;
    	Edge ansRmv = new Edge(), ansAdd = new Edge();
    	for (int i = 1; i < diam.size(); i++) {
    		int u = diam.get(i - 1), v = diam.get(i);
    		Edge curRmv = new Edge(u, v);
    		ArrayList<Integer> path1 = diameter(u, new Edge(u, v), new Edge());
    		ArrayList<Integer> path2 = diameter(v, new Edge(u, v), new Edge());
    		Edge curAdd = new Edge(path1.get(path1.size() / 2), path2.get(path2.size() / 2));
    		//System.out.println("curAdd: " + curAdd);
    		//System.out.println("curRmv: " + curRmv);
    		int newDiam = diameter(0, curRmv, curAdd).size() - 1;
    		if (newDiam < ans) {
    			ans = newDiam;
    			ansRmv = curRmv;
    			ansAdd = curAdd;
    		}
    	}
    	System.out.println(ans + "\n" + ansRmv + "\n" + ansAdd);
    }

    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
    	  
    	int numTests = inp.nextInt();

    	while (numTests-- > 0) {
    		n = inp.nextInt();
    		adj = new ArrayList[n];
    		for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
    		for (int i = 0; i < n - 1; i++) {
    			int u = inp.nextInt() - 1, v = inp.nextInt() - 1;
    			adj[u].add(v); adj[v].add(u);
    		}
    		solve();
    	}
        
        //out.close();
    }
}