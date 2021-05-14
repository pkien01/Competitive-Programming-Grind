/*input
2
4 4
0 1
1 2
2 3
3 0
5 7
0 1
1 4
0 2
1 2
1 3
4 3
2 3
*/

//https://codeforces.com/blog/entry/82538
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
    static class Pair {
        int first, second;
        Pair(int first, int second) {
            this.first = first; this.second = second;
        }
    }

    static final int inf = (int)2e8;

    static int n;
    static int[][] dist;
    static Pair[] edges;

    static void floyd() {
        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
    }
    static int solve() {
        floyd();
        int ans = inf;

        // Case 1: the center of MDST in vertex
        for (int u = 0; u < n; u++) {
            int maxDist = 0;
            for (int v = 0; v < n; v++) maxDist = Math.max(maxDist, dist[u][v]);
            ans = Math.min(ans, maxDist * 2);
        }

        // Case 2: the center of MDST in edge
        for (Pair e: edges) {
            int maxDist = 0;
            for (int v = 0; v < n; v++) maxDist = Math.max(maxDist, Math.min(dist[e.first][v], dist[e.second][v]));
            ans = Math.min(ans, maxDist * 2 + 1);
        }

        return ans;
    }
    	
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
       	for (int test = 1; test <= numTests; test++) {
        	n = inp.nextInt(); int m = inp.nextInt();
        	dist = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(dist[i], inf);
                dist[i][i] = 0;
            }
            edges = new Pair[m];
        	for (int i = 0; i < m; i++) {
        		int u = inp.nextInt(), v = inp.nextInt();
        		dist[u][v] = 1; dist[v][u] = 1;
                edges[i] = new Pair(u, v);
        	}
        	System.out.println("Case #" + test + ":\n" + solve() + "\n");
        }
        
        //out.close();
    }
}