/*input
8 4
0 4
1 2
2 3
2 4
3 5
3 6
3 7
6 7
-1 -1
0 0
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
    static class Station implements Comparable<Station> {
    	int vertex, value;
    	Station(int vertex, int value) {
    		this.vertex = vertex;
    		this.value = value;
    	}
    	public String toString() {
    		return vertex + " " + value;
    	}
    	@Override
    	public int compareTo(Station other) {
    		return this.value != other.value? other.value - this.value : this.vertex - other.vertex;
    	}
    }

    static int n, m;
    static ArrayList<Integer>[] adj;

    static int cntNum;
    static int[] dfs_num, dfs_low;
  	static boolean[] cutVertex; 
  	static Station[] stations;
    static void dfs(int u, int p) {
    	dfs_num[u] = ++cntNum;
    	dfs_low[u] = dfs_num[u];
    	for (Integer v: adj[u]) {
    		if (v == p) continue;
    		if (dfs_num[v] == 0) {
    			dfs(v, u);
    			if (dfs_num[u] <= dfs_low[v]) {
    				stations[u].value++;
    				cutVertex[u] = true;
    			}
    			dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
    		}
    		else dfs_low[u] = Math.min(dfs_low[u], dfs_num[v]);
    	}
    }

    static void solve() {
		cntNum = 0;
        dfs_num = new int[n]; dfs_low = new int[n];
        cutVertex = new boolean[n];
        stations = new Station[n];
        for (int i = 0; i < n; i++) stations[i] = new Station(i, (i == 0? 0 : 1));
        dfs(0, -1);
        
    	Arrays.sort(stations);
    	//for (int i = 0; i < n; i++) System.out.format("dfs_low[%d] = %d\n", i, dfs_low[i]);
    	for (int i = 0; i < m; i++) System.out.println(stations[i].toString());
    	System.out.println();
    }
    public static void main(String[] args) {      
        runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (true) {
        	n = inp.nextInt(); m = inp.nextInt();
        	if (n == 0 && m == 0) break;
        	adj = new ArrayList[n];
        	for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        	while (true) {
        		int u = inp.nextInt(), v = inp.nextInt();
        		if (u < 0 && v < 0) break;
        		adj[u].add(v); adj[v].add(u);
        	}
        	solve();
        }
        
        //out.close();
    }
}