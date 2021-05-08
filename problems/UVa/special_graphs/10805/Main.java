/*input
1
6 7
0 1
0 3
0 4
1 2
2 3
2 5
3 4
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
    static int[] dist;
    static ArrayList<Integer>[] adj, tree;

    static int endpoint, maxDist;
    static void dfs(int u, int p, int depth) {
        if (depth > maxDist) {
            maxDist = depth;
            endpoint = u;
        }
        for (Integer v: tree[u]) 
            if (v != p) dfs(v, u, depth + 1);
    }
    static int bfs(int root) {
        tree = new ArrayList[n];
        for (int i = 0; i < n; i++) tree[i] = new ArrayList<>();

        dist = new int[n]; Arrays.fill(dist, inf);
        dist[root] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(root);

        endpoint = -1; maxDist = -1;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (Integer v: adj[u]) {
                if (dist[v] == inf) {
                    dist[v] = dist[u] + 1;
                    if (dist[v] > maxDist) {
                        maxDist = dist[v];
                        endpoint = v;
                    }

                    tree[u].add(v);
                    tree[v].add(u);
                    queue.add(v);
                }
            }
        }

        dfs(endpoint, -1, 0);
        return maxDist;
    }
    static int solve() {
        int ans = inf;
        for (int i = 0; i < n; i++) ans = Math.min(ans, bfs(i));
        return ans;
    }
    	
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
       	for (int test = 1; test <= numTests; test++) {
        	n = inp.nextInt(); int m = inp.nextInt();
        	adj = new ArrayList[n];
            for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        	while (m-- > 0) {
        		int u = inp.nextInt(), v = inp.nextInt();
        		adj[u].add(v); adj[v].add(u);
        	}
        	System.out.println("Case #" + test + ":\n" + solve() + "\n");
        }
        
        //out.close();
    }
}