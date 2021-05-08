/*input
8
0 (1) 1
1 (3) 2 0 3
2 (2) 1 3
3 (3) 1 2 4
4 (1) 3
7 (1) 6
6 (1) 7
5 (0)
0
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
    	int first, second;
    	Edge(int first, int second) {
    		if (first > second) {
    			int tmp = first; first = second; second = tmp;
    		}
    		this.first = first; this.second = second;
    	}
    	public String toString() {
    		return String.valueOf(first) + " - " + String.valueOf(second);
    	}
    	@Override
    	public int compareTo(Edge other) {
    		return this.first != other.first? this.first - other.first : this.second - other.second;
    	}
    }
    static int n;
    static ArrayList<Integer>[] adj;

    static int cntNum;
    static int[] dfs_num, dfs_low;
    static ArrayList<Edge> bridges;
    static void dfs(int u, int p) {
    	dfs_num[u] = ++cntNum;
    	dfs_low[u] = dfs_num[u];
    	for (Integer v: adj[u]) {
    		if (v == p) continue;
    		if (dfs_num[v] == 0) {
    			dfs(v, u);
    			if (dfs_num[u] < dfs_low[v]) bridges.add(new Edge(u, v));
    			dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
    		}
    		else dfs_low[u] = Math.min(dfs_low[u], dfs_num[v]);
    	}
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (inp.hasNext()) {
        	n = inp.nextInt();
        	adj = new ArrayList[n];
        	for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        	for (int i = 0; i < n; i++) {
        		int u = inp.nextInt();
        		String s = inp.next();
        		int n_adjs = Integer.parseInt(s.substring(1, s.length() - 1));
        		while (n_adjs-- > 0) {
        			int v = inp.nextInt();
        			adj[u].add(v); adj[v].add(u);
        		}
        		//System.out.println(adj[u].toString());
        	}

        	cntNum = 0;
        	dfs_num = new int[n]; dfs_low = new int[n];
        	bridges = new ArrayList<>();
        	for (int i = 0; i < n; i++)
        		if (dfs_num[i] == 0) dfs(i, -1);
        	Collections.sort(bridges);
  
        	System.out.println(bridges.size() + " critical links");
        	for (Edge e: bridges) System.out.println(e.toString());
        	System.out.println();
        }
        
        //out.close();
    }
}