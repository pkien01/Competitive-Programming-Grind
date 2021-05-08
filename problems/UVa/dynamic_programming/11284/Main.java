/*input
1
4 10
0 1 4.33
0 2 4.41
0 3 4.25
0 4 2.65
1 2 5.19
1 3 2.99
1 4 2.96
2 3 3.28
2 4 5.06
3 4 4.49
4
1 4.50
2 7.93
3 1.95
4 2.90
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
    static int n, p;
    static final int inf = (int)2e8;
    static int[][] dist;
    static int[] stores;
    static int[] save;

    static void initDist() {
    	dist = new int[n][n];
    	//dist[0][0] = 0;
    	for (int i = 0; i < n; i++) {
    		dist[i][i] = 0;
        	Arrays.fill(dist[i], inf);
        }
    }
    static void computeDist() {
    	for (int k = 0; k < n; k++) 
        	for (int i = 0; i < n; i++) 
        		for (int j = 0; j < n; j++) 
        			dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
    }
        		

    static int[][] dp;
    static boolean[][] vis;
    static int solve(int u, int mask) {
    	if (vis[u][mask]) return dp[u][mask];
    	int res = -dist[stores[u]][0];
    	for (int v = 0; v < p; v++)
    		if ((mask >> v & 1) == 0) res = Math.max(res, solve(v, mask | (1 << v)) - dist[stores[u]][stores[v]] + save[v]);

    	vis[u][mask] = true;
    	dp[u][mask] = res;
    	return res;
    }

    public static void main(String[] args) {      
        runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	n = inp.nextInt() + 1;
        	int m = inp.nextInt();

        	initDist();
        	while (m-- > 0) {
        		int u = inp.nextInt(), v = inp.nextInt(), w = (int)Math.round(inp.nextDouble() * 100.); 
        		dist[u][v] = Math.min(dist[u][v], w); 
        		dist[v][u] = Math.min(dist[v][u], w);
        	}
      		computeDist();  	

        	p = inp.nextInt();
        	stores = new int[p];
        	save = new int[p];
        	for (int i = 0; i < p; i++) {
        		stores[i] = inp.nextInt();
        		save[i] = (int)Math.round(inp.nextDouble() * 100.);
        	}
        	dp = new int[p][1 << p];
        	vis = new boolean[p][1 << p];
        	int ans = 0;
        	for (int i = 0; i < p; i++) 
        		ans = Math.max(ans, -dist[stores[i]][0] + save[i] + solve(i, 1 << i));

        	//for (int i = 0; i < n; i++) System.out.println(Arrays.toString(dist[i]));
        	/*
        	for (int i = 0; i < p; i++) {
        		System.out.print(dist[stores[i]][0]);
        		for (int j = 0; j < p; j++) System.out.print(" " + dist[stores[i]][stores[j]]);
        		System.out.println();
        	}
        	System.out.println(Arrays.toString(save));
        	*/
        	if (ans > 0) System.out.format("Daniel can save $%d.%02d\n", ans / 100, ans % 100);
        	else System.out.println("Don\'t leave the house");
        }
        
        //out.close();
    }
}