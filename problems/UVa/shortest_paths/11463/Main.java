/*input
2
4
3
0 1
2 1
1 3
0 3
2
1
0 1
1 0
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
    static final int inf = (int)2e8;
    static int n;
    //static int[][] dist;
    static ArrayList<Integer>[] adj;
    static int src, dest;

    /*
    static int solve_floyd() {
    	for (int k = 0; k < n; k++)
    		for (int i = 0; i < n; i++)
    			for (int j = 0; j < n; j++) dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);

    	int ans = 0;
    	for (int i = 0; i < n; i++) ans = Math.max(ans, dist[src][i] + dist[i][dest]);
    	return ans;
    }*/

    static int[] bfs(int from) {
    	int[] dist = new int[n];
    	Arrays.fill(dist, inf);
    	dist[from] = 0;
    	Queue<Integer> queue = new LinkedList<>(); 
    	queue.add(from);
    	while (!queue.isEmpty()) {
    		int u = queue.poll();
    		for (Integer v: adj[u]) {
    			if (dist[v] == inf) {
    				dist[v] = dist[u] + 1;
    				queue.add(v);
    			}
    		}
    	}
    	return dist;
    }
    static int solve_bfs() {	
    	int[] dist_src = bfs(src), dist_dest = bfs(dest);
    	int ans = 0;
    	for (int i = 0; i < n; i++) ans = Math.max(ans, dist_src[i] + dist_dest[i]);
    	return ans;
    }	
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
       	for (int test = 1; test <= numTests; test++) {
        	n = inp.nextInt(); 
        	/*
        	dist = new int[n][n];
        	for (int i = 0; i < n; i++) {
        		Arrays.fill(dist[i], inf);
        		dist[i][i] = 0;
        	}*/
        	int m = inp.nextInt();
        	/*
        	while (m-- > 0) {
        		int u = inp.nextInt(), v = inp.nextInt();
        		dist[u][v] = dist[v][u] = 1;
        	}*/

        	adj = new ArrayList[n];
        	for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        	while (m-- > 0) {
        		int u = inp.nextInt(), v = inp.nextInt();
        		adj[u].add(v); adj[v].add(u);
        	}
        	src = inp.nextInt(); dest = inp.nextInt();
        	System.out.println("Case " + test + ": " + solve_bfs());
        }
        
        //out.close();
    }
}